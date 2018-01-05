package com.troy.web.rest;

import com.troy.domain.dto.VisitorDTO;
import com.troy.domain.entity.Device;
import com.troy.domain.entity.Visitor;
import com.troy.service.DeviceService;
import com.troy.service.VisitorService;
import com.troy.service.base.BaseService;
import com.troy.web.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author caipiaoping
 * @date 2016/10/22
 */
@RestController
@RequestMapping(value="/api/visitors")
public class VisitorController extends BaseController<Visitor,VisitorDTO> {

    @Autowired
    private VisitorService visitorService;

    @Autowired
    private DeviceService deviceService;

    @Override
    protected BaseService<Visitor> getService() {
        return visitorService;
    }

    @Override
    protected Visitor newModel() {
        return new Visitor();
    }

    @Override
    protected VisitorDTO newDTO() {
        return new VisitorDTO();
    }

    @Override
    protected Visitor converterModel(VisitorDTO dto, Visitor visitor) {
        visitor = super.converterModel(dto, visitor);
        Set<Device> devices = new HashSet<>();
        for(Long deviceId : dto.getDeviceIds()){
            Device device = deviceService.get(deviceId,getCurrentUser());
            devices.add(device);
        }
        visitor.setDevices(devices);
        return visitor;
    }

    @Override
    protected VisitorDTO converterDTO(Visitor visitor, VisitorDTO dto) {
        dto = super.converterDTO(visitor, dto);
        Set<Long> deviceIds = new HashSet<>();
        for(Device device : visitor.getDevices()){
            deviceIds.add(device.getId());
        }
        dto.setDeviceIds(deviceIds);
        return dto;
    }
}
