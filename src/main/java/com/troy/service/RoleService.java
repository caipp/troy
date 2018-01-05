package com.troy.service;

import com.troy.domain.entity.Role;
import com.troy.domain.entity.User;
import com.troy.repository.RoleRepository;
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
public class RoleService extends BaseService<Role> {

    @Autowired
    private RoleRepository roleRepo;
    @Autowired
    private UserService userService;

    @Override
    protected RoleRepository getRepository() {
        return roleRepo;
    }

    public Role setUser(Long id, Long[] users, User currentUser) {
       Role role = roleRepo.getOne(id);
       Set<User> userSet= new HashSet<>();
       for(Long userId : users){
           userSet.add(userService.get(userId,currentUser));
       }
        role.setUsers(userSet);
       return update(role,currentUser);
    }

    public Set<Long> getUserIds(Long id) {
        Role role = roleRepo.getOne(id);
        Set<User> userSet= role.getUsers();
        Set<Long> ids = new HashSet<>();
        for(User user:userSet){
            ids.add(user.getId());
        }
        return ids;
    }
}
