package com.troy.service;

import com.troy.domain.entity.Device;
import com.troy.domain.entity.User;
import com.troy.domain.entity.UserGroup;
import com.troy.repository.UserGroupRepository;
import com.troy.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author caipiaoping
 */
@Service
public class UserGroupService extends BaseService<UserGroup> {

    @Autowired
    private UserGroupRepository userGroupRepo;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private UserService userService;

    @Override
    protected UserGroupRepository getRepository() {
        return userGroupRepo;
    }

    public Set<Long> getDeviceIds(Long id) {
        UserGroup userGroup = userGroupRepo.getOne(id);
        Set<Device> deviceSet= userGroup.getDevices();
        Set<Long> ids = new HashSet<>();
        for(Device device:deviceSet){
            ids.add(device.getId());
        }
        return ids;

    }

    public UserGroup setDevice(Long id, Long[] devices, User currentUser) {
        UserGroup userGroup = userGroupRepo.getOne(id);
        HashSet<Device> deviceSet = new HashSet<>();

        for(Long deviceId : devices){
            deviceSet.add(deviceService.get(deviceId,currentUser));

        }
        userGroup.setDevices(deviceSet);
        return update(userGroup,currentUser);
    }

    public Set<Long> getUserIds(Long id) {
        UserGroup userGroup = userGroupRepo.getOne(id);
        Set<User> userSet= userGroup.getUsers();
        Set<Long> ids = new HashSet<>();
        for(User user:userSet){
            ids.add(user.getId());
        }
        return ids;

    }

    public UserGroup setUser(Long id, Long[] users, User currentUser) {
        UserGroup userGroup = userGroupRepo.getOne(id);
        HashSet<User> userSet = new HashSet<>();

        for(Long userId : users){
            userSet.add(userService.get(userId,currentUser));

        }
        userGroup.setUsers(userSet);
        return update(userGroup,currentUser);
    }
}
