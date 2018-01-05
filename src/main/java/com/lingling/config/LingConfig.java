package com.lingling.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author caipiaoping
 * @version V1.0
 * @Description: TODO
 * @date 2017-11-29
 */

@ConfigurationProperties(prefix="lingling")
public class LingConfig {

    private String openToken;

    private String token;

    private String signature;

    public String getOpenToken() {
        return openToken;
    }

    public void setOpenToken(String openToken) {
        this.openToken = openToken;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
