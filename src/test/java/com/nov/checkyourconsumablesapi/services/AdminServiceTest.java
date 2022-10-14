package com.nov.checkyourconsumablesapi.services;

import com.nov.checkyourconsumablesapi.models.Consumables;
import com.nov.checkyourconsumablesapi.models.UserInfo;
import com.nov.checkyourconsumablesapi.repositories.UsersInfoRepository;
import com.nov.checkyourconsumablesapi.util.UserInfoNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.Null;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AdminServiceTest {

    @InjectMocks
    AdminService adminService;

    @Mock
    UsersInfoRepository usersInfoRepository;

    UserInfo userInfo;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userInfo = new UserInfo();
        userInfo.setId(1);
        userInfo.setLogin("login");
        userInfo.setPassword("password");
        userInfo.setConsumablesList(setConsumablesForTest());
    }

    @Test
    void loadUserById() {

        Optional<UserInfo> userInfoOpt = Optional.of(userInfo);

        when(usersInfoRepository.findById(1)).thenReturn(userInfoOpt);
        when(usersInfoRepository.findById(2)).thenReturn(Optional.empty());

        UserInfo userInfoFromTestMethod = adminService.loadUserById(1);
        assertNotNull(userInfoFromTestMethod);
        assertEquals("login", userInfoFromTestMethod.getLogin());

        Throwable thrown = assertThrows(UserInfoNotFoundException.class, () -> {
            adminService.loadUserById(2);
        });
        assertNotNull(thrown);
    }
    @Test
    void loadAllUsersInfoForAdmin() {
        when(usersInfoRepository.findAll()).thenReturn(setUserInfoList());

        List<UserInfo> list = adminService.loadAllUsersInfoForAdmin();
        assertNotNull(list);
        assertEquals("login", list.get(0).getLogin());
    }

    private List<Consumables> setConsumablesForTest() {
        List<Consumables> list = new ArrayList<>();
        Consumables consumables1 = new Consumables("test1", 10000, 10000);
        Consumables consumables2 = new Consumables("test2", 20000, 20000);
        Consumables consumables3 = new Consumables("test3", 30000, 30000);
        list.add(consumables1);
        list.add(consumables2);
        list.add(consumables3);
        return list;
    }

    private List<UserInfo> setUserInfoList() {
        List<UserInfo> list = new ArrayList<>();
        list.add(userInfo);
        list.add(userInfo);
        list.add(userInfo);
        list.add(userInfo);
        return list;
    }

}