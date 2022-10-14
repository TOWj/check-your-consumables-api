package com.nov.checkyourconsumablesapi.controllers;

import com.nov.checkyourconsumablesapi.models.UserInfo;
import com.nov.checkyourconsumablesapi.repositories.UsersInfoRepository;
import com.nov.checkyourconsumablesapi.services.UserInfoDetailsService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


//Тест проверки работы контроллера
@RunWith(SpringRunner.class)
@WebMvcTest(ApiController.class)
class ApiControllerTest {

//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private UsersInfoRepository repo;
//
//    @MockBean
//    private UserInfoDetailsService UserInfoDetailsService;




    @Test
    public void testGetUserInfoById() {
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