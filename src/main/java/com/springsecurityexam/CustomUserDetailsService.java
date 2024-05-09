package com.springsecurityexam;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class CustomUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountDTO accountDTO =
                new AccountDTO("user", "{noop}1111", List.of(new SimpleGrantedAuthority("ROLE_USER")));

        return new CustomUserDetails(accountDTO);
    }
}
