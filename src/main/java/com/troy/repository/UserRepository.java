package com.troy.repository;

import com.troy.repository.base.BaseRepository;
import com.troy.domain.entity.User;

public interface UserRepository extends BaseRepository<User> {

    User findByUsername(String username);

    User findByWxOpenId(String wxOpenId);

    User findByLinglingId(String linglingId);
}
