package com.example.springsecurityoauth2client.service;

import com.example.springsecurityoauth2client.model.*;
import com.example.springsecurityoauth2client.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Getter
@Service
@RequiredArgsConstructor
public class AbstractOAuth2UserService {

    private final UserRepository userRepository;
    private final UserService userService;
    public void register(ProviderUser providerUser, OAuth2UserRequest userRequest) {

        User user = userRepository.findByUsername(providerUser.getUsername());

        if (user == null) {
            String registrationId = userRequest.getClientRegistration().getRegistrationId();
            userService.register(registrationId, providerUser);
        } else {
            System.out.println("user = " + user);
        }
    }

    public ProviderUser providerUser(ClientRegistration clientRegistration, OAuth2User oAuth2User) {

        String registrationId = clientRegistration.getRegistrationId();
        return switch (registrationId) {
            case "keycloak" -> new KeycloakUser(oAuth2User, clientRegistration);
            case "google" -> new GoogleUser(oAuth2User, clientRegistration);
            case "naver" -> new NaverUser(oAuth2User, clientRegistration);
            default -> null;
        };

    }
}
