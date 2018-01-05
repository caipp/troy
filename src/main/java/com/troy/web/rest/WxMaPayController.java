package com.troy.web.rest;

import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.lingling.http.LingService;
import com.troy.domain.entity.Order;
import com.troy.enums.OrderStatus;
import com.troy.enums.ResultCode;
import com.troy.service.OrderService;
import com.troy.service.WxUserService;
import com.troy.utils.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * @author troy
 * @version V1.0
 * @Description: TODO
 * @date 2017-10-25
 */
@RestController
@RequestMapping("/api/wechat/pay")
public class WxMaPayController {

    @Autowired
    private WxPayService wxPayService;

    @Autowired
    private WxUserService wxUserService;

    @Autowired
    private OrderService orderService;


    /**
     * 统一下单(详见https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_1)
     * 在发起微信支付前，需要调用统一下单接口，获取"预支付交易会话标识"
     * 接口地址：https://api.mch.weixin.qq.com/pay/unifiedorder
     *
     */
    @GetMapping("/unifiedOrder")
    public ApiResult unifiedOrder(String sessionId, String body, String totalFee) throws WxPayException {
        String  wxOpenId = wxUserService.getWxOpenIdBy3rdSession(sessionId);
        Order order = new Order();
        order.setCode(String.valueOf(System.currentTimeMillis()));
        Double yuan = Double.parseDouble(totalFee)/100;
        order.setFee(new BigDecimal(yuan));
        order.setRemark(body);
        order.setStatus(OrderStatus.UNPAY);
        orderService.save(order,null);

        WxPayUnifiedOrderRequest request = WxPayUnifiedOrderRequest.newBuilder()
                .body(order.getRemark())
                .totalFee(Integer.parseInt(totalFee))
                .spbillCreateIp("11.1.11.1")
                .notifyURL("http://caipp.free.ngrok.cc/api/wechat/pay/parseOrderNotifyResult")
                .tradeType(WxPayConstants.TradeType.JSAPI)
                .openid(wxOpenId)
                .outTradeNo(order.getCode())
                .build();
        return new ApiResult(ResultCode.SUCCESS,null,wxPayService.getPayInfo(request));
    }

    @PostMapping("/parseOrderNotifyResult")
    public WxPayNotifyResponse parseOrderNotifyResult(@RequestBody String xmlData) throws WxPayException {
        WxPayOrderNotifyResult wxPayOrderNotifyResult =  this.wxPayService.parseOrderNotifyResult(xmlData);

        Order order = orderService.findByCode(wxPayOrderNotifyResult.getOutTradeNo());
        order.setStatus(OrderStatus.PAY_SUCCESS);
        orderService.update(order,null);
        WxPayNotifyResponse response =  new WxPayNotifyResponse();
        response.setReturnCode(wxPayOrderNotifyResult.getReturnCode());
        response.setReturnMsg(wxPayOrderNotifyResult.getReturnMsg());
        return response;
    }



}
