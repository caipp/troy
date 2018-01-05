package com.troy.service;

import com.lingling.http.LingService;
import com.troy.domain.entity.Card;
import com.troy.domain.entity.Device;
import com.troy.domain.entity.User;
import com.troy.domain.entity.UserGroup;
import com.troy.repository.CardRepository;
import com.troy.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by 12546 on 2016/10/22.
 */
@Service
public class CardService extends BaseService<Card> {

    @Autowired
    private CardRepository cardRepo;

    @Autowired
    private LingService lingService;

    @Autowired
    private UserService userService;

    @Override
    protected CardRepository getRepository() {
        return cardRepo;
    }

    public Card findByCode(String code) {
        return cardRepo.findByCode(code);
    }


    @Override
    public Card save(Card model, User currentUser) {
        //TODO 添加卡
        User user = userService.get(model.getUser().getId(),currentUser);
        Set<UserGroup> groups = user.getUserGroups();
        HashSet<String> deviceIds = new HashSet<>();
        for(UserGroup group :groups){
            Set<Device> deviceSet= group.getDevices();
            for(Device device :deviceSet){
                deviceIds.add(device.getCode());
            }
        }
        lingService.addCard(model.getCode(),deviceIds.toArray(new String[] {}));
        return super.save(model, currentUser);
    }

    @Override
    public Card update(Card model, User currentUser) {
        //TODO 修改卡
        User user = userService.get(model.getUser().getId(),currentUser);
        Set<UserGroup> groups = user.getUserGroups();
        HashSet<String> deviceIds = new HashSet<>();
        for(UserGroup group :groups){
            Set<Device> deviceSet= group.getDevices();
            for(Device device :deviceSet){
                deviceIds.add(device.getCode());
            }
        }
        lingService.updateCard(model.getCode(),deviceIds.toArray(new String[] {}));
        return super.update(model, currentUser);
    }

    @Override
    public void delete(Long id, User currentUser) {
        //TODO 删除卡
        Card card = get(id,currentUser);
        if(card != null){
            lingService.delCard(card.getCode());
        }
        super.delete(id, currentUser);
    }
}
