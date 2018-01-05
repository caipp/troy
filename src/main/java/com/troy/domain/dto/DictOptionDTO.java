package com.troy.domain.dto;

import com.troy.domain.base.BaseDTO;
import com.troy.domain.entity.Dict;

/**
 * @author caipiaoping
 * @version V1.0
 * @Description: TODO
 * @date 2017-12-08
 */
public class DictOptionDTO extends BaseDTO{

    private Dict dict;

    private String code;

    private String label;

    private String remark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Dict getDict() {
        return dict;
    }

    public void setDict(Dict dict) {
        this.dict = dict;
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
