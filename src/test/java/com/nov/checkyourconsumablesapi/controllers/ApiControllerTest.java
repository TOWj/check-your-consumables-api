package com.nov.checkyourconsumablesapi.controllers;

import com.nov.checkyourconsumablesapi.models.UserInfo;
import com.nov.checkyourconsumablesapi.repositories.UsersInfoRepository;
import com.nov.checkyourconsumablesapi.security.AuthProviderImplementation;
import com.nov.checkyourconsumablesapi.services.UserInfoDetailsService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//Тест проверки работы контроллера
@RunWith(SpringRunner.class)
@WebMvcTest(ApiController.class)
class ApiControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UsersInfoRepository repo;

    @MockBean
    private AuthProviderImplementation auth;

    @MockBean
    private UserInfoDetailsService UserInfoDetailsService;

    @Test
    void getTestUserInfo() throws Exception {
        when(repo.findById(1)).thenReturn(Optional.of(
                new UserInfo(1,"tom")));

        mockMvc.perform(get("/api/user_info/1"))
                .andExpect(status().isUnauthorized());
//                .andExpect(jsonPath("$.login", equalTo("tom")));
    }

    @Test
    void getTestOneConsumable() {
    }
}