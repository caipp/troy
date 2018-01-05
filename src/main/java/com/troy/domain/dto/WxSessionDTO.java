package com.troy.domain.dto;

/**
 * @author caipiaoping
 * @version V1.0
 * @Description: TODO
 * @date 2018-01-04
 */
public class WxSessionDTO {

    private String session;
    private Boolean needInit;

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public boolean isNeedInit() {
        return needInit;
    }

    public void setNeedInit(boolean needInit) {
        this.needInit = needInit;
    }
}
