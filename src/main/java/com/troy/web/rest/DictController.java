package com.troy.web.rest;

import com.troy.domain.dto.DictDTO;
import com.troy.domain.entity.Dict;
import com.troy.service.DictService;
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
@RequestMapping(value="/api/dicts")
public class DictController extends BaseController<Dict,DictDTO> {

    @Autowired
    private DictService dictService;

    @Override
    protected BaseService<Dict> getService() {
        return dictService;
    }

    @Override
    protected Dict newModel() {
        return new Dict();
    }

    @Override
    protected DictDTO newDTO() {
        return new DictDTO();
    }

}
