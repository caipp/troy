package com.troy.web.rest;

import com.lingling.http.LingService;
import com.troy.domain.dto.CardDTO;
import com.troy.domain.entity.Card;
import com.troy.service.CardService;
import com.troy.service.base.BaseService;
import com.troy.web.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author caipiaoping
 * @date 2016/10/22
 */
@RestController
@RequestMapping(value="/api/cards")
public class CardController extends BaseController<Card,CardDTO> {

    @Autowired
    private CardService cardService;

    @Override
    protected BaseService<Card> getService() {
        return cardService;
    }

    @Override
    protected Card newModel() {
        return new Card();
    }

    @Override
    protected CardDTO newDTO() {
        return new CardDTO();
    }

}
