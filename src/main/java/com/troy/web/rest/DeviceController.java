package com.troy.web.rest;

import com.lingling.http.LingService;
import com.troy.domain.dto.DeviceDTO;
import com.troy.domain.entity.Device;
import com.troy.enums.ResultCode;
import com.troy.service.DeviceService;
import com.troy.service.base.BaseService;
import com.troy.utils.ApiResult;
import com.troy.web.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author caipiaoping
 * @date 2016/10/22
 */
@RestController
@RequestMapping(value="/api/devices")
public class DeviceController extends BaseController<Device,DeviceDTO> {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private LingService lingService;

    @Override
    protected BaseService<Device> getService() {
        return deviceService;
    }

    @Override
    protected Device newModel() {
        return new Device();
    }

    @Override
    protected DeviceDTO newDTO() {
        return new DeviceDTO();
    }

    @RequestMapping(value = "/{id}/open", method = {  RequestMethod.GET })
    public ApiResult remoteOpenDoor(@PathVariable("id") Long id){
        Boolean result = deviceService.remoteOpenDoor(id,getCurrentUser());
        return new ApiResult(ResultCode.SUCCESS,null,result);
    }

}
