package com.nov.checkyourconsumablesapi.config;

import com.nov.checkyourconsumablesapi.services.UserInfoDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//Класс для конфига аутентификации и авторизации
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserInfoDetailsService userInfoDetailsService;

    @Autowired
    public SecurityConfig(UserInfoDetailsService userInfoDetailsService) {
        this.userInfoDetailsService = userInfoDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/api/registration", "/error").permitAll()
                .anyRequest().hasAnyRole("USER", "ADMIN")
                .and().sessionManagement().disable()
                .formLogin().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {


        auth.userDetailsService(userInfoDetailsService)
                .passwordEncoder(getPasswordEncoder());
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
