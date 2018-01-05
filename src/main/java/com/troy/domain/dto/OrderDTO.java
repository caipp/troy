package com.troy.domain.dto;

import com.troy.domain.base.BaseDTO;
import com.troy.enums.OrderStatus;

import java.math.BigDecimal;

/**
 * @author caipiaoping
 * @version V1.0
 * @Description: TODO
 * @date 2017-12-08
 */
public class OrderDTO extends BaseDTO{

    private String code;

    private BigDecimal fee;


    private OrderStatus status;

    private String remark;
}
