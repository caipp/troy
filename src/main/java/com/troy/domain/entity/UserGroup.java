package com.troy.domain.entity;

import com.troy.domain.base.BaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 12546 on 2016/11/14.
 */
@Entity
@Table(name = "t_usergroup")
public class UserGroup extends BaseEntity {

    @Column(nullable = false, length = 30)
    private String code;

    @Column(nullable = false, length = 30)
    private String name;

    private String comment;

    @ManyToMany(targetEntity = Role.class, fetch = FetchType.LAZY)
    @JoinTable(name = "m_usergroup_user", joinColumns = {@JoinColumn(name = "usergroup_id")}, inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private Set<Role> users = new HashSet<>();

    @ManyToMany(targetEntity = Role.class, fetch = FetchType.LAZY)
    @JoinTable(name = "m_usergroup_role", joinColumns = {@JoinColumn(name = "usergroup_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles = new HashSet<>();

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

    public Set<Role> getUsers() {
        return users;
    }

    public void setUsers(Set<Role> users) {
        this.users = users;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
