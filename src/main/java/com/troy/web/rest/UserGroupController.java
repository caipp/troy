package com.troy.web.rest;

import com.troy.domain.dto.UserGroupDTO;
import com.troy.domain.entity.Role;
import com.troy.domain.entity.UserGroup;
import com.troy.enums.ResultCode;
import com.troy.service.UserGroupService;
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
@RequestMapping(value="/api/groups")
public class UserGroupController extends BaseController<UserGroup,UserGroupDTO> {

    @Autowired
    private UserGroupService userGroupService;

    @Override
    protected BaseService<UserGroup> getService() {
        return userGroupService;
    }

    @Override
    protected UserGroup newModel() {
        return new UserGroup();
    }

    @Override
    protected UserGroupDTO newDTO() {
        return new UserGroupDTO();
    }

    @RequestMapping(value="/{id}/devices", method= RequestMethod.POST)
    public ApiResult setDevices(@RequestBody Long[] devices, @PathVariable("id") Long id) {
        UserGroup userGroup = userGroupService.setDevice(id,devices,getCurrentUser());
        return new ApiResult(ResultCode.SUCCESS,null,converterDTO(userGroup,null));
    }

    @RequestMapping(value="/{id}/devices", method= RequestMethod.GET)
    public ApiResult getDevices(@PathVariable("id") Long id) {
        Set<Long> ids = userGroupService.getDeviceIds(id);
        return new ApiResult(ResultCode.SUCCESS,null,ids);
    }

    @RequestMapping(value="/{id}/users", method= RequestMethod.POST)
    public ApiResult setUsers(@RequestBody Long[] devices, @PathVariable("id") Long id) {
        UserGroup userGroup = userGroupService.setUser(id,devices,getCurrentUser());
        return new ApiResult(ResultCode.SUCCESS,null,converterDTO(userGroup,null));
    }

    @RequestMapping(value="/{id}/users", method= RequestMethod.GET)
    public ApiResult getUsers(@PathVariable("id") Long id) {
        Set<Long> ids = userGroupService.getUserIds(id);
        return new ApiResult(ResultCode.SUCCESS,null,ids);
    }
}
