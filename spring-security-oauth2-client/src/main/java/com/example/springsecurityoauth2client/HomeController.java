package com.example.springsecurityoauth2client;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/CustomOAuth2AuthorizationRequestsResolver")
    public String home() {
        return "oauth2.0client/CustomOAuth2AuthorizationRequestsResolver";
    }

    @GetMapping("/home")
    public String home2() {
        return "oauth2.0client/home";
    }

    @GetMapping("/api/user")
    public String user(Authentication authentication, @AuthenticationPrincipal OAuth2User oAuth2User) {
        System.out.println("authentication = " + authentication + ", oAuth2User = " + oAuth2User);

        return "authentication";
    }

    @GetMapping("/api/oidf")
    public String odic(Authentication authentication, @AuthenticationPrincipal OidcUser oidcUser) {
        System.out.println("authentication = " + authentication + ", oidcUser = " + oidcUser);

        return "authentication";
    }
}
