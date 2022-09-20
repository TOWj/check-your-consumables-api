package com.nov.checkyourconsumablesapi.config;

import com.nov.checkyourconsumablesapi.security.AuthProviderImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//Класс для конфига аутентификации и авторизации
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthProviderImplementation authProvider;

    @Autowired
    public SecurityConfig(AuthProviderImplementation authProvider) {
        this.authProvider = authProvider;
    }

    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authProvider);//TODO
    }
}
