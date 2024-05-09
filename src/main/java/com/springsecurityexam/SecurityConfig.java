package com.springsecurityexam;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@Slf4j
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        AuthenticationManagerBuilder sharedObject = http.getSharedObject(AuthenticationManagerBuilder.class);
        AuthenticationManager authenticationManager = sharedObject.build();

        http
                .authorizeHttpRequests(auth ->auth
                        .anyRequest()
                        .authenticated()
                )
                .formLogin(Customizer.withDefaults())
                .authenticationManager(authenticationManager)
                .addFilterBefore(customAuthenticationFilter(http,authenticationManager), UsernamePasswordAuthenticationFilter.class);

        ;
        return http.build();
    }


    public CustomAuthenticationFilter customAuthenticationFilter(HttpSecurity http,AuthenticationManager authenticationManager){

        return null;
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
