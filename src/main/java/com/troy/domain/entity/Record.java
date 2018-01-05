package com.troy.domain.entity;

import com.troy.domain.base.BaseEntity;
import com.troy.enums.RecordType;

import javax.persistence.*;
import java.util.Date;

/**
 * @author caipiaoping
 * @version V1.0
 * @Description: TODO
 * @date 2017-12-19
 */
@Entity
@Table(name = "t_record")
public class Record extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private RecordType type;

    private Date openTime;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="visitor_id")
    private Visitor visitor;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="device_id")
    private Device device;

    public RecordType getType() {
        return type;
    }

    public void setType(RecordType type) {
        this.type = type;
    }

    public Date getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public Visitor getVisitor() {
        return visitor;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }
}
