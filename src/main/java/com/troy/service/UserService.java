package com.troy.service;

import com.troy.domain.entity.User;
import com.troy.repository.UserRepository;
import com.troy.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 12546 on 2016/10/22.
 */
@Service
public class UserService extends BaseService<User> implements UserDetailsService{

    @Autowired
    private UserRepository userRepo;

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

}
