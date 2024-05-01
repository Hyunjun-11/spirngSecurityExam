package com.springsecurityexam;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexContorller {

    @GetMapping("/")
    public String index() {
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

    @GetMapping("/logoutSuccess")
    public String logoutSuccess() {
        return "logoutSuccess";
    }
}
