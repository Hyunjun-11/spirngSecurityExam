package com.springsecurityexam;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@AllArgsConstructor
public class AccountDTO {
    private String username;
    private String password;
    private Collection<GrantedAuthority> authorities;
}
