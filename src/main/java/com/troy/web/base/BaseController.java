package com.troy.web.base;

import com.troy.domain.base.BaseDTO;
import com.troy.domain.base.BaseEntity;
import com.troy.domain.entity.User;
import com.troy.enums.ResultCode;
import com.troy.persistence.SearchRequest;
import com.troy.service.base.BaseService;
import com.troy.utils.ApiResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by 12546 on 2016/10/22.
 */

public abstract class BaseController<M extends BaseEntity,DTO extends BaseDTO> {

    @ApiOperation(value="列表", notes="")
    @RequestMapping(value={""}, method=RequestMethod.GET)
    public ApiResult list() {
        List<M> list= getService().getAll(getCurrentUser());
        return new ApiResult(ResultCode.SUCCESS,null,list);
    }


    @ApiOperation(value="获取对象", notes="根据url的id来获取指定对象")
    @RequestMapping(value = "/{id}", method = { RequestMethod.GET })
    public ApiResult get(@PathVariable("id") Long id) {
        M model = getService().get(id,getCurrentUser());
        return new ApiResult(ResultCode.SUCCESS,null, converterDTO(model,null));
    }

    @ApiOperation(value="创建对象", notes="根据对象创建实体")
    @ApiImplicitParam(value = "对象",required = true)
    @RequestMapping(value="", method=RequestMethod.POST)
    public ApiResult post(@RequestBody DTO dto) {
        M model = converterModel(dto,null);
        model = getService().save(model,getCurrentUser());
        return new ApiResult(ResultCode.SUCCESS,null, converterDTO(model,null));
    }

    @ApiOperation(value="更新对象", notes="根据url的id来指定更新对象，并根据传过来的对象信息来更新")
    @ApiImplicitParam(value = "对象",required = true)
    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ApiResult put(@PathVariable Long id,@RequestBody DTO dto) {
        M model = getService().get(id,getCurrentUser());
        model = converterModel(dto,model);
        model = getService().update(model,getCurrentUser());
        return new ApiResult(ResultCode.SUCCESS,null, converterDTO(model,null));
    }

    @ApiOperation(value="删除对象", notes="根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Long")
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ApiResult delete(@PathVariable Long id) {
        getService().delete(id,getCurrentUser());
        return new ApiResult();
    }

    @ApiOperation(value="分页查询", notes="根据分页信息查询数据")
    @ApiImplicitParam(value = "对象",required = true)
    @RequestMapping(value="/query", method=RequestMethod.POST)
    public ApiResult query(@RequestBody SearchRequest searchRequest) {
        Page<M> pageInfo = getService().query(searchRequest,getCurrentUser());
        return new ApiResult(ResultCode.SUCCESS,null,pageInfo);
    }

    protected abstract BaseService<M> getService();

    protected abstract M newModel();

    protected abstract DTO newDTO();

    protected M converterModel(DTO dto, M m){
        if(m == null){
            m  = newModel();
        }
        BeanUtils.copyProperties(dto,m);
        return m;
    };

    protected DTO converterDTO(M m,DTO dto){
        if(dto == null){
            dto = newDTO();
        }
        BeanUtils.copyProperties(m,dto);
        return dto;

    };

    protected User getCurrentUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User)authentication.getPrincipal();
    }

}
