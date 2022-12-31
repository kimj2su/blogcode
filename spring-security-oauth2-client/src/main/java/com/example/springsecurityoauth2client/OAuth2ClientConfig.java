package com.example.springsecurityoauth2client;

import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.ClientRegistrations;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;


import org.springframework.context.annotation.Configuration;


@Configuration
public class OAuth2ClientConfig {

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(keycloakClientRegistration());
    }

    private ClientRegistration keycloakClientRegistration() {

        return ClientRegistrations.fromIssuerLocation("http://localhost:8080/realms/oauth2")
                .registrationId("keycloak")
                .clientId("oauth2-client-app")
                .clientSecret("qQ7cYPYopQBhaP7eTPbZzKnBVBcYkkNj")
                .redirectUri("http://localhost:8081/login/oauth2/code/keycloak")
//                .issuerUri("")
                .build();
    }
}
