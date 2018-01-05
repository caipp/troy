package com.troy.web;

import com.alibaba.fastjson.JSON;
import com.lingling.http.LingService;
import com.troy.domain.NoticeMsg;
import com.troy.enums.ResultCode;
import com.troy.utils.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author caipiaoping
 * @version V1.0
 * @Description: TODO
 * @date 2017-11-29
 */
@RestController
public class LogController {

    @Autowired
    private LingService lingService;
    @RequestMapping(value = "/openDoorRecord", method = { RequestMethod.POST, RequestMethod.GET })
    public ApiResult openDoorRecord(@RequestParam String noticeMsg){
        NoticeMsg msg = JSON.parseObject(noticeMsg,NoticeMsg.class);
        return new ApiResult(ResultCode.SUCCESS,null,msg);
    }



}
