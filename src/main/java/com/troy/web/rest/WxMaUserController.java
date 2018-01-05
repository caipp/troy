package com.troy.web.rest;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.lingling.http.LingService;
import com.troy.domain.dto.WxSessionDTO;
import com.troy.domain.entity.Device;
import com.troy.domain.entity.User;
import com.troy.domain.entity.UserGroup;
import com.troy.enums.ResultCode;
import com.troy.service.DeviceService;
import com.troy.service.UserService;
import com.troy.service.WxUserService;
import com.troy.utils.ApiResult;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author troy
 * @version V1.0
 * @Description: TODO
 * @date 2017-10-25
 */
@RestController
@RequestMapping("/api/wechat/user")
public class WxMaUserController {

    @Autowired
    private WxMaService wxService;

    @Autowired
    private WxUserService wxUserService;

    @Autowired
    private UserService userService;

    @Autowired
    private LingService lingService;

    @Autowired
    private DeviceService deviceService;


    /**
     * 登陆接口
     */
    @GetMapping("login")
    public ApiResult login(String code) {
        if (StringUtils.isBlank(code)) {
            return new ApiResult(ResultCode.PARAMS_ERROR,"empty jscode",null);
        }

        try {
            WxMaJscode2SessionResult session = this.wxService.getUserService().getSessionInfo(code);
            String thirdSession = wxUserService.create3rdSession(session.getOpenid(),session.getSessionKey(),Integer.toUnsignedLong(session.getExpiresin()));
            //TODO 可以增加自己的逻辑，关联业务相关数据,判断用户是否存在
            User user = userService.getUserByWxOpenId(session.getOpenid());
            WxSessionDTO wxSessionDTO = new WxSessionDTO();
            wxSessionDTO.setSession(thirdSession);
            if(user == null){
                user = new User();
                user.setUsername(session.getOpenid());
                user.setWxOpenId(session.getOpenid());
                user.setPassword(new BCryptPasswordEncoder().encode("123456"));
                userService.save(user,null);
                wxSessionDTO.setNeedInit(true);
            }else{
                wxSessionDTO.setNeedInit(false);
            }

            return new ApiResult(ResultCode.SUCCESS,null,wxSessionDTO);
        } catch (WxErrorException e) {

            return new ApiResult(ResultCode.EXCEPTION,e.toString(),null);
        }
    }

    /**
     *
     * 获取用户信息接口
     *
     */
    @GetMapping("info")
    public ApiResult info(String sessionId, String signature, String rawData, String encryptedData, String iv) {
        String  sessionKey = wxUserService.getWxSessionKeyBy3rdSession(sessionId);
        String  wxOpenId = wxUserService.getWxOpenIdBy3rdSession(sessionId);
        // 用户信息校验
        if (null != sessionKey && !this.wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
            return new ApiResult(ResultCode.PARAMS_ERROR,"user check failed",null);
        }
        User user = userService.getUserByWxOpenId(wxOpenId);
        // 解密用户信息
        WxMaUserInfo userInfo = this.wxService.getUserService().getUserInfo(sessionKey, encryptedData, iv);
        if(userInfo != null && StringUtils.isEmpty(user.getNikename())){
            user.setNikename(userInfo.getNickName());
            user.setAvatarUrl(userInfo.getAvatarUrl());
            user.setGender(userInfo.getGender());
            userService.update(user,null);
        }
        return new ApiResult(ResultCode.SUCCESS,null,user);
    }

    @GetMapping("qrcode")
    public ApiResult qrCode(String sessionId) {
        String qrCode = null;
        if(StringUtils.isNotEmpty(sessionId)){
            String  wxOpenId = wxUserService.getWxOpenIdBy3rdSession(sessionId);
            User user = userService.getUserByWxOpenId(wxOpenId);
            Set<UserGroup> groups = user.getUserGroups();
            HashSet<String> sdkKeys = new HashSet<>();
            for(UserGroup group :groups){
                Set<Device> deviceSet= group.getDevices();
                for(Device device :deviceSet){
                    sdkKeys.add(device.getSdkKey());
                }
            }
            if(!sdkKeys.isEmpty()){

                qrCode = lingService.addOwnerQrCode(user.getLinglingId(),sdkKeys.toArray(new String[] {}));
            }

        }

        return new ApiResult(ResultCode.SUCCESS,null,qrCode);
    }



    protected User getCurrentUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User)authentication.getPrincipal();
    }


}
