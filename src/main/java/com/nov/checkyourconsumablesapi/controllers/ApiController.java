package com.nov.checkyourconsumablesapi.controllers;

import com.nov.checkyourconsumablesapi.security.UserInfoDetails;
import com.nov.checkyourconsumablesapi.services.UserInfoDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class ApiController {

    private final UserInfoDetailsService userInfoDetailsService;

    @Autowired
    public ApiController(UserInfoDetailsService userInfoDetailsService) {
        this.userInfoDetailsService = userInfoDetailsService;
    }

    @GetMapping("/user_info/{id}")
    public String getTestUserInfo(@PathVariable("id") int id, Model model) {
        // Тестовый метод для получения инфы об аутентифицированном юзере из контекста
        userInfoDetailsService.loadUserById(id);
        model.addAttribute("userInfo", userInfoDetailsService.loadUserById(id));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserInfoDetails userInfoDetails = (UserInfoDetails) authentication.getPrincipal();
        System.out.println(userInfoDetails.getUserInfo());
        return "show";
    }

    @GetMapping("/cons/{id}")
    public String getTestOneConsumable(@PathVariable("id") int id) {
        return "test";
    }
}
