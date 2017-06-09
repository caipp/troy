package com.troy.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.troy.domain.base.BaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 12546 on 2016/11/14.
 */
@Entity
@Table(name = "t_permission")
public class Permission extends BaseEntity {

    @Column(nullable = false, length = 30)
    private String code;

    @Column(nullable = false, length = 30)
    private String name;

    private String comment;

    @JsonIgnore
    @ManyToMany(targetEntity = Role.class, mappedBy = "permissions")
    private Set<Role> roles = new HashSet<>();

    @OneToOne(optional = true, cascade = CascadeType.ALL, mappedBy = "permission")
    private Resource resource;

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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }
}
