//package com.springsecurityexam;
//
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//@EnableWebSecurity
//@Configuration
//@Slf4j
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//
//        //커스텀 CustomAuthenticationProvider 사용하는 방법 1
//        AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
//        builder.authenticationProvider(new CustomAuthenticationProvider());
//        builder.authenticationProvider(new CustomAuthenticationProvider2());
//
//
//
//        http
//                .authorizeHttpRequests(auth ->auth
//                        .requestMatchers("/").permitAll()
//                        .anyRequest()
//                        .authenticated())
//                .formLogin(Customizer.withDefaults())
////                커스텀 CustomAuthenticationProvider 사용하는 방법 2
////                .authenticationProvider(new CustomAuthenticationProvider())
////                .authenticationProvider(new CustomAuthenticationProvider2());
//        ;
//
//
//        return http.build();
//    }
//
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user = User
//                .withUsername("user")
//                .password("{noop}1111")
//                .build();
//        return new InMemoryUserDetailsManager(user);
//    }
//}
