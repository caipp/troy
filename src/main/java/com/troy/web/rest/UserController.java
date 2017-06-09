package com.troy.web.rest;

import com.troy.domain.entity.User;
import com.troy.enums.ResultCode;
import com.troy.service.base.BaseService;
import com.troy.service.UserService;
import com.troy.utils.ApiResult;
import com.troy.utils.HbUtils;
import com.troy.web.base.BaseController;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 12546 on 2016/10/22.
 */
@RestController
@RequestMapping(value="/api/users")
public class UserController extends BaseController<User> {

    @Autowired
    private UserService userService;

    @Override
    protected BaseService<User> getService() {
        return userService;
    }

    @ApiOperation(value="获取对象", notes="根据url的username来获取指定对象")
    @RequestMapping(value = "/name/{username}", method = { RequestMethod.GET })
    public ApiResult getByUsername(@PathVariable("username") String username) {
        User model = userService.loadUserByUsername(username);
        return new ApiResult(ResultCode.SUCCESS,null, HbUtils.deproxy(model));
    }
}
