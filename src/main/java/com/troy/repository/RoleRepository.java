package com.troy.repository;

import com.troy.domain.entity.Role;
import com.troy.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface RoleRepository extends BaseRepository<Role> {

}