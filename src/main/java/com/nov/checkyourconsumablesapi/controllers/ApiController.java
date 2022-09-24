package com.nov.checkyourconsumablesapi.controllers;

import com.nov.checkyourconsumablesapi.models.Consumables;
import com.nov.checkyourconsumablesapi.security.UserInfoDetails;
import com.nov.checkyourconsumablesapi.services.ConsumablesService;
import com.nov.checkyourconsumablesapi.services.UserInfoDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final UserInfoDetailsService userInfoDetailsService;

    private final ConsumablesService consumablesService;

    @Autowired
    public ApiController(UserInfoDetailsService userInfoDetailsService, ConsumablesService consumablesService) {
        this.userInfoDetailsService = userInfoDetailsService;
        this.consumablesService = consumablesService;
    }

    @GetMapping("/user_info")
    public String getUserInfo() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserInfoDetails userInfoDetails = (UserInfoDetails) authentication.getPrincipal();
        return userInfoDetails.getUserInfo().toString();
    }

    @GetMapping("/cons")
    public String getTestOneConsumable() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserInfoDetails userInfoDetails = (UserInfoDetails) authentication.getPrincipal();
        List<Consumables> list = consumablesService.findAllByPersonId(userInfoDetails.getUserInfo().getId());

        return list.toString();
    }
}
