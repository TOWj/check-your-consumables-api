package com.nov.checkyourconsumablesapi.controllers;

import com.nov.checkyourconsumablesapi.models.Consumables;
import com.nov.checkyourconsumablesapi.models.UserInfo;
import com.nov.checkyourconsumablesapi.models.enums.Roles;
import com.nov.checkyourconsumablesapi.repositories.UsersInfoRepository;
import com.nov.checkyourconsumablesapi.services.AdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;


//Тест проверки работы контроллера
//@RunWith(SpringRunner.class)
//@WebMvcTest(ApiController.class)

class ApiControllerTest {

//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private UsersInfoRepository repo;
//
//    @MockBean
//    private UserInfoDetailsService UserInfoDetailsService;

    @Mock
    AdminService adminService;

    UserInfo userInfo;

    final int USER_ID = 1;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        userInfo = new UserInfo();
        userInfo.setId(USER_ID);
        userInfo.setRole(Roles.ROLE_ADMIN);
        userInfo.setLogin("loginTest");
        userInfo.setPassword("passwordTest");
        userInfo.setConsumablesList(setConsumablesForTest());
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


    @Test
    public void testGetUserInfoById() {
        when(adminService.loadUserById(anyInt())).thenReturn(userInfo);
        UserInfo userInfo = adminService.loadUserById(USER_ID);
        assertNotNull(userInfo);
        assertEquals(USER_ID, userInfo.getId());
    }


//    @Test
//    void getTestOneConsumable() {
//    }
//
//    @Test
//    public void getListConsumables() {
//    }
//
//    @Test
//    public void saveConsumables() {
//    }
//
//    @Test
//    public void testGetUserInfo() {
//    }
//
//    @Test
//    public void getAllUsersInfoForAdmin() {
//    }
//
//    @Test
//    public void createNewUser() {
//    }
}