package com.nov.checkyourconsumablesapi.security;

import com.nov.checkyourconsumablesapi.services.UserInfoDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class AuthProviderImplementation implements AuthenticationProvider {

    private final UserInfoDetailsService userInfoDetailsService;

    @Autowired
    public AuthProviderImplementation(UserInfoDetailsService userInfoDetailsService) {
        this.userInfoDetailsService = userInfoDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();
        UserDetails userDetails = userInfoDetailsService.loadUserByUsername(login);
        String password = authentication.getCredentials().toString();
        if (!password.equals(userDetails.getPassword())) {
            throw new BadCredentialsException("Incorrect password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, password, Collections.emptyList());//TODO Пока поставил заглушку для прав
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
