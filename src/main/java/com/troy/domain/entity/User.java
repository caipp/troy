package com.troy.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.troy.domain.base.BaseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 12546 on 2016/10/22.
 */
@Entity
@Table(name = "t_user")
public class User extends BaseEntity implements UserDetails {

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private Date lastPasswordReset;

    @Transient
    private String newPassword;

    @Transient
    private Set<Role> authorities = new HashSet<>();

    @JsonIgnore
    @ManyToMany(targetEntity = UserGroup.class, mappedBy = "users")
    private Set<UserGroup> userGroup = new HashSet();

    @JsonIgnore
    @ManyToMany(targetEntity = Role.class, fetch = FetchType.EAGER)
    @JoinTable(name = "m_user_role", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles = new HashSet<>();

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonIgnore
    public String getNewPassword() {
        return newPassword;
    }

    @JsonProperty
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 包裹用户单独赋予的角色
        authorities.addAll(getRoles());
        return authorities;
    }


    @Override
    public String toString() {
        return getClass().getSimpleName() + ": " + getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return getEnable();
    }

    @Override
    public boolean isAccountNonLocked() {
        return getEnable();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return getEnable();
    }

    @Override
    public boolean isEnabled() {
        return getEnable();
    }


    public Date getLastPasswordReset() {
        return lastPasswordReset;
    }

    public void setLastPasswordReset(Date lastPasswordReset) {
        this.lastPasswordReset = lastPasswordReset;
    }

    public void setAuthorities(Set<Role> authorities) {
        this.authorities = authorities;
    }

    public Set<UserGroup> getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(Set<UserGroup> userGroup) {
        this.userGroup = userGroup;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
