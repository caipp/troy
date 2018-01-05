package com.troy.service;

import com.troy.domain.entity.Dict;
import com.troy.repository.DictRepository;
import com.troy.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author caipiaoping
 */
@Service
public class DictService extends BaseService<Dict> {

    @Autowired
    private DictRepository dictRepo;

    @Override
    protected DictRepository getRepository() {
        return dictRepo;
    }
}
