package com.troy.web.rest;

import com.troy.domain.dto.OrderDTO;
import com.troy.domain.entity.Order;
import com.troy.service.OrderService;
import com.troy.service.base.BaseService;
import com.troy.web.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 12546 on 2016/10/22.
 */
@RestController
@RequestMapping(value="/api/orders")
public class OrderController extends BaseController<Order,OrderDTO> {

    @Autowired
    private OrderService orderService;

    @Override
    protected BaseService<Order> getService() {
        return orderService;
    }

    @Override
    protected Order newModel() {
        return new Order();
    }

    @Override
    protected OrderDTO newDTO() {
        return new OrderDTO();
    }
}
