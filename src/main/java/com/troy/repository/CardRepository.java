package com.troy.repository;

import com.troy.domain.entity.Card;
import com.troy.repository.base.BaseRepository;

/**
 * @author caipiaoping
 */
public interface CardRepository extends BaseRepository<Card> {

    Card findByCode(String code);
}
