package com.nov.checkyourconsumablesapi.controllers;

import com.nov.checkyourconsumablesapi.models.Consumables;
import com.nov.checkyourconsumablesapi.models.UserInfo;
import com.nov.checkyourconsumablesapi.security.UserInfoDetails;
import com.nov.checkyourconsumablesapi.services.AdminService;
import com.nov.checkyourconsumablesapi.services.ConsumablesService;
import com.nov.checkyourconsumablesapi.services.UserInfoDetailsService;
import com.nov.checkyourconsumablesapi.util.ConsumablesErrorResponse;
import com.nov.checkyourconsumablesapi.util.ConsumablesNotSavedException;
import com.nov.checkyourconsumablesapi.util.UserInfoErrorResponse;
import com.nov.checkyourconsumablesapi.util.UserInfoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController // Теперь будто все методы имеют аннотацию @ResponseBody
@RequestMapping("/api")
public class ApiController {

    private final UserInfoDetailsService userInfoDetailsService;

    private final ConsumablesService consumablesService;

    private final AdminService adminService;

    @Autowired
    public ApiController(UserInfoDetailsService userInfoDetailsService, ConsumablesService consumablesService, AdminService adminService) {
        this.userInfoDetailsService = userInfoDetailsService;
        this.consumablesService = consumablesService;
        this.adminService = adminService;
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

    @PostMapping("/cons/save_cons")
    public ResponseEntity<HttpStatus> saveConsumables(@RequestBody @Valid Consumables consumables,
                                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();

            for (FieldError error : errors) {
                errorMessage
                        .append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append(";");
            }
            throw new ConsumablesNotSavedException(errorMessage.toString());
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserInfoDetails userInfoDetails = (UserInfoDetails) authentication.getPrincipal();

        consumables.setOwnerUser(userInfoDetails.getUserInfo());
        consumablesService.addConsumables(consumables);

        return ResponseEntity.ok(HttpStatus.OK); // 200
    }

    @GetMapping("/admin/all_users")
    public List<UserInfo> getAllUsersInfoForAdmin() {
        // Метод будет работать только для роли Админ
        return adminService.loadAllUsersInfoForAdmin();
    }

    @ExceptionHandler
    private ResponseEntity<UserInfoErrorResponse> handleException(UserInfoNotFoundException exception) {
        UserInfoErrorResponse errorResponse = new UserInfoErrorResponse(
                "User with this id wasn't found",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND); // 404
    }

    @ExceptionHandler
    private ResponseEntity<ConsumablesErrorResponse> handleException(ConsumablesNotSavedException exception) {
        ConsumablesErrorResponse errorResponse = new ConsumablesErrorResponse(
                exception.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
