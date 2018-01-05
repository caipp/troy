package com.troy.web.rest;

import com.troy.domain.dto.RoleDTO;
import com.troy.domain.entity.Role;
import com.troy.enums.ResultCode;
import com.troy.service.RoleService;
import com.troy.service.base.BaseService;
import com.troy.utils.ApiResult;
import com.troy.web.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Created by 12546 on 2016/10/22.
 */
@RestController
@RequestMapping(value="/api/roles")
public class RoleController extends BaseController<Role,RoleDTO> {

    @Autowired
    private RoleService roleService;

    @Override
    protected BaseService<Role> getService() {
        return roleService;
    }

    @Override
    protected Role newModel() {
        return new Role();
    }

    @Override
    protected RoleDTO newDTO() {
        return new RoleDTO();
    }

    @RequestMapping(value="/{id}/users", method= RequestMethod.POST)
    public ApiResult setUsers(@RequestBody Long[] users,@PathVariable("id") Long id) {
        Role role = roleService.setUser(id,users,getCurrentUser());
        return new ApiResult(ResultCode.SUCCESS,null,converterDTO(role,null));
    }

    @RequestMapping(value="/{id}/users", method= RequestMethod.GET)
    public ApiResult getUsers(@PathVariable("id") Long id) {
        Set<Long> userIds = roleService.getUserIds(id);
        return new ApiResult(ResultCode.SUCCESS,null,userIds);
    }
}
