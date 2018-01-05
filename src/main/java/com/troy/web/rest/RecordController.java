package com.troy.web.rest;

import com.alibaba.fastjson.JSON;
import com.troy.domain.NoticeMsg;
import com.troy.domain.dto.RecordDTO;
import com.troy.domain.entity.Record;
import com.troy.enums.ResultCode;
import com.troy.service.RecordService;
import com.troy.service.base.BaseService;
import com.troy.utils.ApiResult;
import com.troy.web.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author caipiaoping
 * @date 2016/10/22
 */
@RestController
@RequestMapping(value="/api/records")
public class RecordController extends BaseController<Record,RecordDTO> {

    @Autowired
    private RecordService recordService;

    @Override
    protected BaseService<Record> getService() {
        return recordService;
    }

    @Override
    protected Record newModel() {
        return new Record();
    }

    @Override
    protected RecordDTO newDTO() {
        return new RecordDTO();
    }

    @RequestMapping(value = "/callback", method = { RequestMethod.POST, RequestMethod.GET })
    public void callback(@RequestParam String noticeMsg){
        NoticeMsg msg = JSON.parseObject(noticeMsg,NoticeMsg.class);
        recordService.callback(msg);
    }

}
