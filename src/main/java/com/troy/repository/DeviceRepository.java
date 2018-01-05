package com.troy.repository;

import com.troy.domain.entity.Device;
import com.troy.domain.entity.Order;
import com.troy.repository.base.BaseRepository;

/**
 * @author caipiaoping
 */
public interface DeviceRepository extends BaseRepository<Device> {

    Device findByCode(String code);
}
