package com.troy.domain.entity;

import com.troy.domain.base.BaseEntity;
import com.troy.enums.OrderStatus;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author troy
 * @version V1.0
 * @Description: TODO
 * @date 2017-10-27
 */
@Entity
@Table(name = "t_order")
public class Order extends BaseEntity {

    /**
     * 订单编号
     */
    @Column(nullable = false, length = 30)
    private String code;

    /**
     * 订单金额
     */
    @Column(name = "fee", precision = 5, scale = 2 )
    private BigDecimal fee;

    /**
     * 订单状态
     */
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    /**
     * 备注
     */
    private String remark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
