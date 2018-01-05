package com.troy.domain.dto;

import com.troy.domain.base.BaseDTO;

import javax.persistence.Column;
import java.util.Date;

/**
 * @author caipiaoping
 * @version V1.0
 * @Description: TODO
 * @date 2017-12-08
 */
public class DictDTO extends BaseDTO{

    private String code;

    private String name;

    private String remark;

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
