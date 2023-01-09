package com.example.springsecurityoauth2client.service;

import com.example.springsecurityoauth2client.model.ProviderUser;
import com.example.springsecurityoauth2client.model.users.User;
import com.example.springsecurityoauth2client.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void register(String registrationId, ProviderUser providerUser) {
        User user = User.builder()
                .registrationId(registrationId)
                .id(providerUser.getId())
                .username(providerUser.getUsername())
                .provider(providerUser.getPassword())
                .email(providerUser.getEmail())
                .picture(providerUser.getPicture())
                .authorities(providerUser.getAuthorities())
                .build();
        userRepository.register(user);
    }
}
