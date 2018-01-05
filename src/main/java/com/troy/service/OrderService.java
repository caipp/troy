package com.troy.service;

import com.troy.domain.entity.Order;
import com.troy.repository.OrderRepository;
import com.troy.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author caipiaoping
 */
@Service
public class OrderService extends BaseService<Order> {

    @Autowired
    private OrderRepository orderRepo;

    @Override
    protected OrderRepository getRepository() {
        return orderRepo;
    }

    public Order findByCode(String code) {
        return orderRepo.findByCode(code);
    }
}
