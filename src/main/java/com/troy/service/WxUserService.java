package com.troy.service;

import com.troy.redis.RedisUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author troy
 * @version V1.0
 * @Description: TODO
 * @date 2017-10-26
 */
@Service
public class WxUserService {

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 缓存微信openId和session_key
     * @param wxOpenId		微信用户唯一标识
     * @param wxSessionKey	微信服务器会话密钥
     * @param expires		会话有效期, 以秒为单位, 例如2592000代表会话有效期为30天
     * @return
     */
    public String create3rdSession(String wxOpenId, String wxSessionKey, Long expires){
        String thirdSessionKey = RandomStringUtils.randomAlphanumeric(64);
        StringBuffer sb = new StringBuffer();
        sb.append(wxSessionKey).append("#").append(wxOpenId);
        redisUtil.add(thirdSessionKey, expires, sb.toString());
        return thirdSessionKey;
    }

    public String getWxSessionKeyBy3rdSession(String session){

        Object wxSessionObj = redisUtil.get(session);
        if(null != wxSessionObj){
            String wxSessionStr = (String) wxSessionObj;
            String sessionKey = wxSessionStr.split("#")[0];
            return sessionKey;
        }
        return null;
    }

    public String getWxOpenIdBy3rdSession(String session){

        Object wxSessionObj = redisUtil.get(session);
        if(null != wxSessionObj){
            String wxSessionStr = (String) wxSessionObj;
            String wxOpenId = wxSessionStr.split("#")[1];
            return wxOpenId;
        }
        return null;
    }



}
