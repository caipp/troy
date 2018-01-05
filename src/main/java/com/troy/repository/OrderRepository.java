package com.troy.repository;

import com.troy.domain.entity.Order;
import com.troy.repository.base.BaseRepository;

public interface OrderRepository extends BaseRepository<Order> {

    Order findByCode(String code);
}
