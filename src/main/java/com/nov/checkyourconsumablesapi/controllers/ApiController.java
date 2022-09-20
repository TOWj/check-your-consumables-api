package com.nov.checkyourconsumablesapi.controllers;

import com.nov.checkyourconsumablesapi.security.UserInfoDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/user_info")
    public String getTestUserInfo() {
        // Тестовый метод для получения инфы об аутентифицированном юзере из контекста
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserInfoDetails userInfoDetails = (UserInfoDetails) authentication.getPrincipal();
        System.out.println(userInfoDetails.getUserInfo());
        return "test";
    }
}
