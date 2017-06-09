package com.troy.domain.entity;

import com.troy.domain.base.BaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 12546 on 2016/11/14.
 */
@Entity
@Table(name = "t_resource")
public class Resource extends BaseEntity {

    @Column(nullable = false, length = 30)
    private String address;

    @Column(nullable = false, length = 30)
    private String name;

    private String comment;

    @OneToOne(optional = false, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "permission_id", unique = true)
    private Permission permission;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }
}
