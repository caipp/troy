package com.troy.service;

import com.troy.domain.entity.Permission;
import com.troy.domain.entity.Resource;
import com.troy.repository.ResourceRepository;
import com.troy.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 12546 on 2016/10/22.
 */
@Service
public class ResourceService extends BaseService<Resource> {

    @Autowired
    private ResourceRepository resourceRepo;

    @Override
    protected ResourceRepository getRepository() {
        return resourceRepo;
    }


    public Resource findByAddress(String address) {
        return null;
    }

    public List<Resource> findAll() {
        List<Resource> resources = resourceRepo.findAll();
        for(Resource resource:resources){
            Permission permission = resource.getPermission();
            if(permission != null){
                permission.getRoles();
            }
        }
        return resources;
    }
}
