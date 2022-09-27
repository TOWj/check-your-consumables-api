package com.nov.checkyourconsumablesapi.services;

import com.nov.checkyourconsumablesapi.models.UserInfo;
import com.nov.checkyourconsumablesapi.repositories.UsersInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final UsersInfoRepository usersInfoRepository;

    @Autowired
    public AdminService(UsersInfoRepository usersInfoRepository) {
        this.usersInfoRepository = usersInfoRepository;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<UserInfo> loadAllUsersInfoForAdmin() {
        return usersInfoRepository.findAll();
    }

}
