//package com.springsecurityexam;
//
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
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
//        http
//                .authorizeHttpRequests(auth ->auth.anyRequest().authenticated());
//
////                .formLogin(form->form
////                        //사용자 정의 로그인 페이지로 전환, 기본페이지 무시
//////                        .loginPage("/loginPage")
////                        //사용자 이름과 비밀번호를 검증할 URL지정(Form action)
////                        .loginProcessingUrl("/loginProc")
////                        //로그인 성공 이후 이동페이지, alwaysUse가 true이면 무조건 지정된위치로 이동(기본은false)
////                        .defaultSuccessUrl("/",false)
////
////                        //인증에 실패할 경우 사용자에게 보내질 URL을 지정. 기본값은 "/login?error"
////                        .failureUrl("/failed")
////                        //인증을 수행할때 사용자 이름(아이디)을 찾기 위해 확인하는 HTTP 매개변수 설정, 기본값은 username
////                        .usernameParameter("username")
////                        //인증을 수행할때 사용자 비밀번호를 찾기 위해 확인하는 HTTP 매개변수 설정, 기본값은 password
////                        .passwordParameter("password")
////
////
////                        //인증 성공시 사용할 AuthenticationSuccessHandler를 지정
////                        //기본값은 SavedRequestAwareAuthenticationSuccessHandler
////                        //성공했을시 defaultSuccessUrl 보다 우선시 작동한다
////                                .successHandler((request, response, authentication) -> {
////                                    response.sendRedirect("/home");
////                                    log.info("RequestPathInfo: {}", request.getPathInfo());
////                                    log.info("RequestRequestURI: {}", request.getRequestURI());
////                                    log.info("RequestSession: {}", request.getSession());
////                                    log.info("RequestSession: {}", request.getHeader("X-Requested-With"));
////
////                                    log.info("Request: {}", request.getPathInfo());
////                                    log.info("Response: {}", response);
////                                    log.info("Authentication: {}", authentication.getPrincipal());
////                                    log.info("Authentication: {}", authentication.getName());
////                                    log.info("Authentication: {}", authentication.getDetails());
////                                    log.info("Authentication: {}", authentication.getAuthorities());
////                                })
////
////                                //인증실패시 사용할 AuthenticationFailureHandler를 지정
////                        //기본값은 SimpleUrlAuthenticationFailureHandler를 사용하여 "login?error"로 리다이렉션
////                        //실패했을시 failureUrl 보다 우선시 작동한다.
////                        .failureHandler((request, response, exception) -> {
////                            System.out.println("exception : " + exception.getMessage());
////                            response.sendRedirect("/login");
////
////                        })
////                        .permitAll()
////                );
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
