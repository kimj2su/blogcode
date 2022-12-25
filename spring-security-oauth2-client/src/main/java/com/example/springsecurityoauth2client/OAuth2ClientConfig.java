package com.example.springsecurityoauth2client;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.context.annotation.Configuration;

@Configuration
public class OAuth2ClientConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(authRequest -> authRequest
                        .antMatchers("/home").permitAll()
                        .anyRequest().authenticated());
        http
//                .oauth2Login();
                .oauth2Client(Customizer.withDefaults());
        http.logout().logoutSuccessUrl("/home");
        return http.build();
    }
}
