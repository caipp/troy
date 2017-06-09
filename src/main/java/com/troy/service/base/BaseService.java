package com.troy.service.base;

import com.troy.domain.base.BaseEntity;
import com.troy.domain.entity.Resource;
import com.troy.repository.base.BaseRepository;
import com.troy.domain.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

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

    protected abstract BaseRepository<M> getRepository();

}
