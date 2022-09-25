package com.nov.checkyourconsumablesapi.controllers;

import com.nov.checkyourconsumablesapi.models.UserInfo;
import com.nov.checkyourconsumablesapi.services.RegistrationService;
import com.nov.checkyourconsumablesapi.util.UserInfoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final RegistrationService registrationService;
    private final UserInfoValidator userInfoValidator;

    @Autowired
    public AuthController(RegistrationService registrationService, UserInfoValidator userInfoValidator) {
        this.registrationService = registrationService;
        this.userInfoValidator = userInfoValidator;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("userInfo") UserInfo userInfo) {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("userInfo") @Valid UserInfo userInfo,
                                      BindingResult bindingResult) {
        userInfoValidator.validate(userInfo, bindingResult);

        if (bindingResult.hasErrors()) {
            return "auth/registration";
        }

        registrationService.register(userInfo);

        return "redirect:/auth/login";
    }

}
