package com.nov.checkyourconsumablesapi.services;

import com.nov.checkyourconsumablesapi.models.UserInfo;
import com.nov.checkyourconsumablesapi.models.enums.Roles;
import com.nov.checkyourconsumablesapi.repositories.UsersInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationService {

    private final UsersInfoRepository usersInfoRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(UsersInfoRepository usersInfoRepository, PasswordEncoder passwordEncoder) {
        this.usersInfoRepository = usersInfoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));

        userInfo.setRole(Roles.ROLE_USER);
        usersInfoRepository.save(userInfo);
    }
}
