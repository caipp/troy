package com.troy.service.base;

import com.troy.domain.base.BaseEntity;
import com.troy.domain.entity.User;
import com.troy.persistence.DynamicSpecifications;
import com.troy.persistence.SearchFilter;
import com.troy.persistence.SearchRequest;
import com.troy.repository.base.BaseRepository;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Map;

/**
 * Created by 12546 on 2016/10/22.
 */
public abstract class BaseService<M extends BaseEntity> {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    public M get(Long id, User currentUser) {
        return getRepository().getOne(id);
    }

    public List<M> getAll(User currentUser) {
        return getRepository().findAll();
    }

    public M save(M model, User currentUser) {
        return getRepository().save(model);
    }

    public M update(M model, User currentUser) {
        return getRepository().save(model);
    }

    public void delete(Long id, User currentUser) {
        getRepository().delete(id);
    }

    public Page<M> query(SearchRequest searchRequest, User currentUser) {
        return getRepository().findAll(buildSpecification(currentUser.getId(), searchRequest.getSearchParams()), buildPageRequest(searchRequest.getPage(),searchRequest.getSize(),searchRequest.getSortType()));
    }

    protected abstract BaseRepository<M> getRepository();

    /**
     * 创建分页请求.
     */
    protected PageRequest buildPageRequest(int pageNumber, int pagzSize, String sortType) {
        Sort sort = null;
        if (StringUtils.isEmpty(sortType) || "auto".equals(sortType)) {
            sort = new Sort(Direction.DESC, "id");
        } else{
            String[] splits = sortType.split("_");

            if(splits.length == 2){
                if(splits[1].equalsIgnoreCase(Direction.ASC.toString())){
                    sort = new Sort(Direction.ASC, splits[0]);
                }else{
                    sort = new Sort(Direction.DESC, splits[0]);
                }
            }
        }
        return new PageRequest(pageNumber - 1, pagzSize, sort);
    }

    /**
     * 创建动态查询条件组合.
     */
    protected Specification<M> buildSpecification(Long userId, Map<String, Object> searchParams) {
        if(searchParams == null){
            return null;
        }
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        Specification<M> spec = DynamicSpecifications.bySearchFilter(filters.values());
        return spec;
    }

}
