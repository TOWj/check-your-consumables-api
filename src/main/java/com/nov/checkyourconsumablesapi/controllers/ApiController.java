package com.nov.checkyourconsumablesapi.controllers;

import com.nov.checkyourconsumablesapi.models.Consumables;
import com.nov.checkyourconsumablesapi.models.UserInfo;
import com.nov.checkyourconsumablesapi.security.UserInfoDetails;
import com.nov.checkyourconsumablesapi.services.ConsumablesService;
import com.nov.checkyourconsumablesapi.services.UserInfoDetailsService;
import com.nov.checkyourconsumablesapi.util.UserInfoErrorResponse;
import com.nov.checkyourconsumablesapi.util.UserInfoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Теперь будто все методы имеют аннотацию @ResponseBody
@RequestMapping("/api")
public class ApiController {

    private final UserInfoDetailsService userInfoDetailsService;

    private final ConsumablesService consumablesService;

    @Autowired
    public ApiController(UserInfoDetailsService userInfoDetailsService, ConsumablesService consumablesService) {
        this.userInfoDetailsService = userInfoDetailsService;
        this.consumablesService = consumablesService;
    }

    // Автоматическая конвертация возвращаемых значений c помощью Jackson в JSON
    @GetMapping("/user_info")
    public UserInfo getUserInfo() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserInfoDetails userInfoDetails = (UserInfoDetails) authentication.getPrincipal();
        return userInfoDetails.getUserInfo();
    }

    @GetMapping("/user_info/{id}")
    public UserInfo getUserInfo(@PathVariable("id") int id) {

        return userInfoDetailsService.loadUserById(id);
    }

    @GetMapping("/cons")
    public List<Consumables> getListConsumables() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserInfoDetails userInfoDetails = (UserInfoDetails) authentication.getPrincipal();
        List<Consumables> list = consumablesService.findAllByPersonId(userInfoDetails.getUserInfo().getId());

        return list;
    }

    @GetMapping("/admin/all_users")
    public List<UserInfo> getAllUsersInfoForAdmin() {
        return userInfoDetailsService.loadAllUsersInfoForAdmin();
    }

    @ExceptionHandler
    private ResponseEntity<UserInfoErrorResponse> handleException(UserInfoNotFoundException exception) {
        UserInfoErrorResponse errorResponse = new UserInfoErrorResponse(
                "User with this id wasn't found",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND); // 404
    }
}
