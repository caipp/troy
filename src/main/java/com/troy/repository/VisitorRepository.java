package com.troy.repository;

import com.troy.domain.entity.Visitor;
import com.troy.repository.base.BaseRepository;

/**
 * @author caipiaoping
 */
public interface VisitorRepository extends BaseRepository<Visitor> {

    Visitor findByQrCodeKey(String qrcodeKey);
}
