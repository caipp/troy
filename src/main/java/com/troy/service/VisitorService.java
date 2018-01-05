package com.troy.service;

import com.lingling.http.LingService;
import com.troy.domain.entity.Device;
import com.troy.domain.entity.User;
import com.troy.domain.entity.Visitor;
import com.troy.repository.VisitorRepository;
import com.troy.service.base.BaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;

/**
 *
 * @author caipiaoping
 */
@Service
public class VisitorService extends BaseService<Visitor> {

    @Autowired
    private VisitorRepository visitorRepo;

    @Autowired
    private LingService lingService;

    @Override
    protected VisitorRepository getRepository() {
        return visitorRepo;
    }

    @Override
    public Visitor save(Visitor model, User currentUser) {
        HashSet<String> sdkKeys = new HashSet<>();
        for(Device device : model.getDevices()){
            sdkKeys.add(device.getSdkKey());
        }
        Map<String,Object> map = lingService.addVisitorQrCode(model.getUser().getLinglingId(),sdkKeys.toArray(new String[] {}),model.getCount(),model.getStartTime(),model.getEndTime());
        model.setQrCodeKey(map.get("qrcodeKey").toString());
        model.setCode(map.get("codeId").toString());
        return super.save(model, currentUser);
    }

    @Override
    public Visitor update(Visitor model, User currentUser) {
        return super.update(model, currentUser);
    }

    @Override
    public void delete(Long id, User currentUser) {
        Visitor visitor = get(id,currentUser);
        if(visitor != null && StringUtils.isNotEmpty(visitor.getCode())){
            lingService.delVisitorQrCode(Long.parseLong(visitor.getCode()));
        }
        super.delete(id, currentUser);
    }

    public Visitor getVisitorByQrCodeKey(String qrcodeKey) {
        return visitorRepo.findByQrCodeKey(qrcodeKey);
    }
}
