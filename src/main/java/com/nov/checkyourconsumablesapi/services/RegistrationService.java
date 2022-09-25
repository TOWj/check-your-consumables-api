package com.nov.checkyourconsumablesapi.services;

import com.nov.checkyourconsumablesapi.models.UserInfo;
import com.nov.checkyourconsumablesapi.models.enums.Roles;
import com.nov.checkyourconsumablesapi.repositories.UsersInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationService {

    private final UsersInfoRepository usersInfoRepository;

    @Autowired
    public RegistrationService(UsersInfoRepository usersInfoRepository) {
        this.usersInfoRepository = usersInfoRepository;
    }

    @Transactional
    public void register(UserInfo userInfo) {
        userInfo.setRole(Roles.ROLE_USER);
        usersInfoRepository.save(userInfo);
    }
}
