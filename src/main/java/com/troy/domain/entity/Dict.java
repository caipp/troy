package com.troy.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.troy.domain.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * @author caipiaoping
 * @version V1.0
 * @Description: TODO
 * @date 2017-12-21
 */
@Entity
@Table(name = "t_dict")
public class Dict extends BaseEntity {

    /**
     * 编号
     */
    @Column(nullable = true, length = 30)
    private String code;

    /**
     * 名称
     */
    @Column(nullable = false, length = 50)
    private String name;

    /**
     * 备注
     */
    private String remark;

    @JsonIgnore
    @OneToMany(mappedBy="dict")
    private Set<DictOption> dictOptions = new HashSet<>();

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

    public Set<DictOption> getDictOptions() {
        return dictOptions;
    }

    public void setDictOptions(Set<DictOption> dictOptions) {
        this.dictOptions = dictOptions;
    }
}
