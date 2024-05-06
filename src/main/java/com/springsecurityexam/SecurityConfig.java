package com.springsecurityexam;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
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

        http
                .authorizeRequests(auth -> auth.anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                .rememberMe(rememberMe -> rememberMe
                        //기억하기 매개변수가 설정되지 않았을때에도 쿠키가 항상 생성되어야 하는지 여부
                        .alwaysRemember(true)
                        //토큰이 유요한 시간을 지정할수있다.
                        .tokenValiditySeconds(3600)
                        //UserDetails를 조회하기위해 사용되는 UserDetailsService를 지정한다.
                        .userDetailsService(userDetailsService())
                        //로그인시 사용자를 기억하기위해 사용되는 매개변수이며 기본값은 remember-me 이다
                        .rememberMeCookieName("remember")
                        //토큰을 저장하는 이름이며 기본값은 remember-me 이다
                        .rememberMeParameter("remember")
                        //기억하기인증을 위해 토큰을 식별하는 키 설정
                        .key("security")

                )
        ;


        return http.build();
    }


    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User
                .withUsername("user")
                .password("{noop}1111")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}
