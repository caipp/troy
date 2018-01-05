package com.troy.domain.dto;

import com.troy.domain.base.BaseDTO;

import java.util.Date;

/**
 * @author caipiaoping
 * @version V1.0
 * @Description: TODO
 * @date 2017-12-08
 */
public class DeviceDTO extends BaseDTO{

    private String code;

    private String name;

    private String sn;

    private String sdkKey;

    private Date keyEffecTime;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getSdkKey() {
        return sdkKey;
    }

    public void setSdkKey(String sdkKey) {
        this.sdkKey = sdkKey;
    }

    public Date getKeyEffecTime() {
        return keyEffecTime;
    }

    public void setKeyEffecTime(Date keyEffecTime) {
        this.keyEffecTime = keyEffecTime;
    }
}
