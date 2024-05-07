package com.springsecurityexam;


import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class IndexContorller {

    @GetMapping("/")
    public String index() {
        SecurityContext context = SecurityContextHolder.getContextHolderStrategy().getContext();
        Authentication authentication = context.getAuthentication();
        log.info("{}",authentication);
        return "Hello World";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }
    @GetMapping("/loginPage")
    public String loginPage() {
        return "loginPage";
    }
}
