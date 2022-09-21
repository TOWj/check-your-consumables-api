package com.nov.checkyourconsumablesapi.services;

import com.nov.checkyourconsumablesapi.models.UserInfo;
import com.nov.checkyourconsumablesapi.repositories.UsersInfoRepository;
import com.nov.checkyourconsumablesapi.security.UserInfoDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoDetailsService implements UserDetailsService {

    private final UsersInfoRepository usersInfoRepository;

    @Autowired
    public UserInfoDetailsService(UsersInfoRepository usersInfoRepository) {
        this.usersInfoRepository = usersInfoRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userInfo = usersInfoRepository.findByLogin(username);

        if (userInfo.isEmpty()) {
            throw new UsernameNotFoundException("User with this login not found");
        }
        return new UserInfoDetails(userInfo.get());
    }

    public UserInfo loadUserById(int id) {
        Optional<UserInfo> userInfo = usersInfoRepository.findById(id);
        return userInfo.orElse(null);
    }
}
