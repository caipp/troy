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
 * @date 2017-12-01
 */
@Entity
@Table(name = "t_device")
public class Device extends BaseEntity{

    /**
     * 编号
     */
    @Column(nullable = true, length = 30)
    private String code;

    /**
     * 设备名称
     */
    @Column(nullable = false, length = 50)
    private String name;

    /**
     * SN编号
     */
    @Column(nullable = false, length = 255)
    private String sn;

    private String sdkKey;

    private Date keyEffecTime;

    @ManyToMany(targetEntity = UserGroup.class, fetch = FetchType.LAZY)
    @JoinTable(name = "m_usergroup_device", joinColumns = {@JoinColumn(name = "device_id")}, inverseJoinColumns = {@JoinColumn(name = "usergroup_id")})
    private Set<UserGroup> userGroups = new HashSet<>();


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
