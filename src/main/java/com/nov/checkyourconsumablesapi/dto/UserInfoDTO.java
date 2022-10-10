package com.nov.checkyourconsumablesapi.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserInfoDTO {

    @NotEmpty(message = "Login shouldn't be empty")
    @Size(min = 3, max = 100, message = "Login should be between 3 and 100 characters")
    private String login;

    @NotEmpty(message = "Password shouldn't be empty")
    @Size(min = 3, max = 100, message = "Password should be between 3 and 100 characters")
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
