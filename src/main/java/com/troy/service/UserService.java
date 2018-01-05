package com.troy.service;

import com.lingling.http.LingService;
import com.troy.domain.entity.User;
import com.troy.repository.UserRepository;
import com.troy.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by 12546 on 2016/10/22.
 */
@Service
public class UserService extends BaseService<User> implements UserDetailsService{

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private LingService lingService;

    @Override
    protected UserRepository getRepository() {
        return userRepo;
    }

    private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();

    @Override
    public final User loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("user not found");
        }
        detailsChecker.check(user);
//        Set<UserGroup> userGroup = user.getUserGroup();
//        for (UserGroup group : userGroup) {
//            Set<Role> roles = group.getRoles();
//            user.setAuthorities(roles);
//        }
        user.getAuthorities();

        return user;
    }

    public User getUserByWxOpenId(String wxOpenId) {
        User user = userRepo.findByWxOpenId(wxOpenId);
        return user;
    }

    public User getUserByLinglingId(String linglingId) {
        User user = userRepo.findByLinglingId(linglingId);
        return user;
    }


    public User getUserByUsername(String username) {
        User user = userRepo.findByUsername(username);
        return user;
    }

    @Override
    public User save(User model, User currentUser) {

        String lingLingId  = lingService.getLingLingId();
        model.setLinglingId(lingLingId);
        return super.save(model, currentUser);
    }

    @Override
    public User update(User model, User currentUser) {
        User oldModel = get(model.getId(),currentUser);
        oldModel.setNikename(model.getNikename());
        oldModel.setGender(model.getGender());
        oldModel.setUsername(model.getUsername());
        oldModel.setEnable(model.getEnable());
        return super.update(oldModel, currentUser);
    }
}
