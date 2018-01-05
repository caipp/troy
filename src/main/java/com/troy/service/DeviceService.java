package com.troy.service;

import com.lingling.http.LingService;
import com.troy.domain.entity.Device;
import com.troy.domain.entity.Order;
import com.troy.domain.entity.Record;
import com.troy.domain.entity.User;
import com.troy.enums.RecordType;
import com.troy.repository.DeviceRepository;
import com.troy.repository.OrderRepository;
import com.troy.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by 12546 on 2016/10/22.
 */
@Service
public class DeviceService extends BaseService<Device> {

    @Autowired
    private DeviceRepository deviceRepo;

    @Autowired
    private LingService lingService;

    @Autowired
    private RecordService recordService;

    @Override
    protected DeviceRepository getRepository() {
        return deviceRepo;
    }

    public Device findByCode(String code) {
        return deviceRepo.findByCode(code);
    }

    @Override
    public Device save(Device model, User currentUser) {
        String code  = lingService.addDevice(model.getName(),model.getSn());
        String sdkKey = lingService.makeSdkKey(code);
        model.setCode(code);
        model.setSdkKey(sdkKey);
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DAY_OF_MONTH, 180);
        model.setKeyEffecTime(c.getTime());
        return super.save(model, currentUser);
    }

    @Override
    public Device update(Device model, User currentUser) {
        lingService.updateDevice(model.getCode(),model.getName(),model.getSn());
        return super.update(model, currentUser);
    }

    @Override
    public void delete(Long id, User currentUser) {
        Device model = get(id,currentUser);
        lingService.delDevice(model.getCode());
        super.delete(id, currentUser);
    }

    public Boolean remoteOpenDoor(Long id, User currentUser) {
        Device device = get(id,currentUser);
        Boolean result = false;
        if(device != null){
            result = lingService.remoteOpenDoor(device.getSdkKey());
            if(result){
                Record record = new Record();
                record.setType(RecordType.REMOTEOPEN);
                record.setUser(currentUser);
                record.setDevice(device);
                record.setOpenTime(new Date());
                recordService.save(record,currentUser);
            }
        }
        return result;

    }
}
