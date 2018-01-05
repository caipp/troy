package com.troy.web.rest;

import com.troy.domain.dto.DictOptionDTO;
import com.troy.domain.entity.DictOption;
import com.troy.service.DictOptionService;
import com.troy.service.base.BaseService;
import com.troy.web.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author caipiaoping
 * @date 2016/10/22
 */
@RestController
@RequestMapping(value="/api/dictOptions")
public class DictOptionController extends BaseController<DictOption,DictOptionDTO> {

    @Autowired
    private DictOptionService dictOptionService;

    @Override
    protected BaseService<DictOption> getService() {
        return dictOptionService;
    }

    @Override
    protected DictOption newModel() {
        return new DictOption();
    }

    @Override
    protected DictOptionDTO newDTO() {
        return new DictOptionDTO();
    }

}
