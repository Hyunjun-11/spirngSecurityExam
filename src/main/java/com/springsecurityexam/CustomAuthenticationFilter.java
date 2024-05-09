package com.springsecurityexam;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.authentication.AuthenticationFilter;

public class CustomAuthenticationFilter extends AuthenticationFilter {
    public CustomAuthenticationFilter(AuthenticationManager authenticationManager, AuthenticationConverter authenticationConverter) {
        super(authenticationManager, authenticationConverter);
    }
}
