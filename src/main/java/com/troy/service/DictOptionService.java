package com.troy.service;

import com.troy.domain.entity.DictOption;
import com.troy.repository.DictOptionRepository;
import com.troy.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author caipiaoping
 */
@Service
public class DictOptionService extends BaseService<DictOption> {

    @Autowired
    private DictOptionRepository dictOptionRepo;

    @Override
    protected DictOptionRepository getRepository() {
        return dictOptionRepo;
    }
}
