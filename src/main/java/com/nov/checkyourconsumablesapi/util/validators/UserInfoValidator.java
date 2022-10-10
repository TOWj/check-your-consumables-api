package com.nov.checkyourconsumablesapi.util.validators;


import com.nov.checkyourconsumablesapi.dto.UserInfoDTO;
import com.nov.checkyourconsumablesapi.models.UserInfo;
import com.nov.checkyourconsumablesapi.services.UserInfoDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class UserInfoValidator implements Validator {

    private final UserInfoDetailsService userInfoDetailsService;

    @Autowired
    public UserInfoValidator(UserInfoDetailsService userInfoDetailsService) {
        this.userInfoDetailsService = userInfoDetailsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return UserInfo.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserInfoDTO userInfoDTO = (UserInfoDTO) target;

        Optional<UserInfo> userInfoForCreation =
                userInfoDetailsService.loadUserByUsernameForCreation(userInfoDTO.getLogin());

        if (userInfoForCreation.isPresent()) {
            errors.rejectValue("login", "", "User with this login is already exist");
        }
    }
}
