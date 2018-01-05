package com.troy.domain.dto;

import com.troy.domain.base.BaseDTO;
import com.troy.domain.entity.User;
import com.troy.enums.RecordType;

import java.util.Date;

/**
 * @author caipiaoping
 * @version V1.0
 * @Description: TODO
 * @date 2017-12-19
 */
public class RecordDTO extends BaseDTO {

    private RecordType type;

    private Date openTime;

    private User user;

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
}