package com.example.springsecurityoauth2client.controller;

//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "socialLogin/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletResponse response, HttpServletRequest request, Authentication authentication) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, authentication);
        return "redirect:/login";
    }

//    @GetMapping("/oauth2Login")
//    public String oauth2Login(Model model, HttpServletRequest request, HttpServletResponse response) {
//        return "redirect:/";
//    }
//
//    @GetMapping("/logout")
//    public String logout(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
//        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
//        logoutHandler.logout(request, response, authentication);
//
//        return "redirect:/";
//    }
}
