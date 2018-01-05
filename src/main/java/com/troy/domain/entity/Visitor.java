package com.troy.domain.entity;

import com.troy.domain.base.BaseEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author caipiaoping
 * @version V1.0
 * @Description: TODO
 * @date 2017-12-21
 */
@Entity
@Table(name = "t_visitor")
public class Visitor extends BaseEntity {

    /**
     * 二维码编号
     */
    @Column(nullable = true, length = 30)
    private String code;

    /**
     * 访客名称
     */
    @Column(nullable = false, length = 50)
    private String name;

    private Date startTime;

    private Date endTime;

    private Integer Count;


    private String qrCodeKey;


    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="reason_id")
    private DictOption reason;


    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToMany(targetEntity = Device.class, fetch = FetchType.LAZY)
    @JoinTable(name = "m_visitor_device", joinColumns = {@JoinColumn(name = "visitor_id")}, inverseJoinColumns = {@JoinColumn(name = "device_id")})
    private Set<Device> devices = new HashSet<>();

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

    public DictOption getReason() {
        return reason;
    }

    public void setReason(DictOption reason) {
        this.reason = reason;
    }

    public String getQrCodeKey() {
        return qrCodeKey;
    }

    public void setQrCodeKey(String qrCodeKey) {
        this.qrCodeKey = qrCodeKey;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Device> getDevices() {
        return devices;
    }

    public void setDevices(Set<Device> devices) {
        this.devices = devices;
    }
}
