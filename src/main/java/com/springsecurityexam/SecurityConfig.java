package com.springsecurityexam;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
@Slf4j
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        AuthenticationManagerBuilder managerBuilder =http.getSharedObject(AuthenticationManagerBuilder.class);
        //첫번째 방법
        managerBuilder.userDetailsService(userDetailsService());
        //두번째 방법
        http.userDetailsService(userDetailsService());
        //일반 객채로 사용하는방법
        managerBuilder.userDetailsService(new CustomUserDetailsService());

        http
                .authorizeHttpRequests(auth ->auth
                                .anyRequest()
                                .authenticated()
                )
                .formLogin(Customizer.withDefaults())
                ;


        return http.build();
    }


    @Bean
    public UserDetailsService userDetailsService() {

        return new CustomUserDetailsService();
    }
}
