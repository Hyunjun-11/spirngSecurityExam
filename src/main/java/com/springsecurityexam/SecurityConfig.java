package com.springsecurityexam;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;

@EnableWebSecurity
@Configuration
@Slf4j
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth ->auth
                        .requestMatchers("/logoutSuccess").permitAll()
                        .anyRequest()
                        .authenticated())
                .formLogin(Customizer.withDefaults())
//                .csrf(AbstractHttpConfigurer::disable)
                .logout(logout ->logout
                        .logoutUrl("/logout")
                        //POST 방식으로하면 로그아웃을 요청을 하면 404로 로그아웃이되지않는다
                        //.logoutRequestMatcher(new AntPathRequestMatcher("/logoutProc","POST"))
                        //GET 방식으로 바꿔주거나 csrf 를 비활성화 해줘야한다.
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout","POST"))
                        .logoutSuccessUrl("/logoutSuccess")
                        //logoutSuccessUrl 보다 logoutSuccessHandler 가 우선순위가 더 높다
                        .logoutSuccessHandler(new LogoutSuccessHandler() {
                            @Override
                            public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                                response.sendRedirect("/logoutSuccess");
                            }
                        })
                        //쿠키삭제
                        .deleteCookies("JESSTIONID","remember-me")
                        //세션무효화
                        .invalidateHttpSession(true)
                        //Authentication 객체 삭제
                        .clearAuthentication(true)
                        .addLogoutHandler((request, response, authentication) -> {
                            //세션을가져온다
                            HttpSession session = request.getSession();
                            session.invalidate();
                            //Authentication 제거
                            SecurityContextHolder.getContextHolderStrategy().getContext().setAuthentication(null);
                            //securityContext 클리어 (제거)
                            SecurityContextHolder.getContextHolderStrategy().clearContext();
                        })
                        .permitAll()

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
