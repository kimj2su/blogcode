package com.example.springsecurityoauth2client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Controller
public class ClientController {

    /*
        OAuth2AuthorizedClientRepository, OAuth2AuthorizedClientService
        두 개의 클래스 모두 인증 받은 객체를 참조 할 수 있다.
     */
    @Autowired
    private OAuth2AuthorizedClientRepository oAuth2AuthorizedClientRepository;

    @Autowired
    private OAuth2AuthorizedClientService oAuth2AuthorizedClientService;

    /*
        이 URI까지 왔다는 것은 인가받은 클라이언트 Authentication객체가 생성되었음을 의미한다.
     */
    @GetMapping("/client")
    public String client(Authentication authentication, HttpServletRequest request, Model model) {

//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String clientRegistrationId = "keycloak";

        OAuth2AuthorizedClient authorizedClient1 = oAuth2AuthorizedClientRepository
                .loadAuthorizedClient(clientRegistrationId, authentication, request);

        OAuth2AuthorizedClient authorizedClient2 = oAuth2AuthorizedClientService
                .loadAuthorizedClient(clientRegistrationId, authentication.getName());

        /*
           authorizedClient1 여기에는 accessToken이 있다.
         */
        OAuth2AccessToken accessToken = authorizedClient1.getAccessToken();

        OAuth2UserService oAuth2UserService = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = oAuth2UserService
                .loadUser(new OAuth2UserRequest(authorizedClient1.getClientRegistration(), accessToken));

        OAuth2AuthenticationToken authenticationToken = new OAuth2AuthenticationToken
                (oAuth2User, Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")),
                        authorizedClient1.getClientRegistration().getRegistrationId());


        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        model.addAttribute("accessToken", accessToken.getTokenValue());
        model.addAttribute("refreshToken", authorizedClient1.getRefreshToken().getTokenValue());
        model.addAttribute("principalName", oAuth2User.getName());
        model.addAttribute("clientName", authorizedClient1.getClientRegistration().getClientName());

        return "/client";


    }
}
