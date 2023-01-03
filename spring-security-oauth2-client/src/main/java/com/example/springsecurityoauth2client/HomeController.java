package com.example.springsecurityoauth2client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/CustomOAuth2AuthorizationRequestsResolver")
    public String home() {
        return "CustomOAuth2AuthorizationRequestsResolver";
    }

    @GetMapping("/home")
    public String home2() {
        return "home";
    }
}
