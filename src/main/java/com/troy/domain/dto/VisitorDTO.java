package com.troy.domain.dto;

import com.troy.domain.base.BaseDTO;
import com.troy.domain.entity.DictOption;
import com.troy.domain.entity.User;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author caipiaoping
 * @version V1.0
 * @Description: TODO
 * @date 2017-12-08
 */
public class VisitorDTO extends BaseDTO{

    private String code;

    private String name;

    private Date startTime;

    private Date endTime;

    private Integer Count;

    private String qrCodeKey;

    private DictOption reason;

    private User user;

    private Set<Long>  deviceIds = new HashSet<>();

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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getCount() {
        return Count;
    }

    public void setCount(Integer count) {
        Count = count;
    }

    public String getQrCodeKey() {
        return qrCodeKey;
    }

    public void setQrCodeKey(String qrCodeKey) {
        this.qrCodeKey = qrCodeKey;
    }

    public DictOption getReason() {
        return reason;
    }

    public void setReason(DictOption reason) {
        this.reason = reason;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Long> getDeviceIds() {
        return deviceIds;
    }

    public void setDeviceIds(Set<Long> deviceIds) {
        this.deviceIds = deviceIds;
    }
}
