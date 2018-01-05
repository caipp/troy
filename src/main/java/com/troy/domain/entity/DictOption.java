package com.troy.domain.entity;

import com.troy.domain.base.BaseEntity;

import javax.persistence.*;

/**
 * @author caipiaoping
 * @version V1.0
 * @Description: TODO
 * @date 2017-12-21
 */
@Entity
@Table(name = "t_dict_option")
public class DictOption extends BaseEntity {

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="dict_id")
    private Dict dict;

    /**
     * 编号
     */
    @Column(nullable = true, length = 30)
    private String code;

    /**
     * 名称
     */
    @Column(nullable = false, length = 50)
    private String label;

    /**
     * 备注
     */
    private String remark;


    public Dict getDict() {
        return dict;
    }

    public void setDict(Dict dict) {
        this.dict = dict;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
