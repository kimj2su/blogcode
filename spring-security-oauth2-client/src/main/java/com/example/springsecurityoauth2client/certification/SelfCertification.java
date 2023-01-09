package com.example.springsecurityoauth2client.certification;

import com.example.springsecurityoauth2client.model.ProviderUser;
import com.example.springsecurityoauth2client.model.users.User;
import com.example.springsecurityoauth2client.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SelfCertification {

    private final UserRepository userRepository;

    public void checkCertification(ProviderUser providerUser) {
        User user = userRepository.findByUsername(providerUser.getId());
//        if(user != null) {
        boolean bool = "none".equals(providerUser.getProvider()) || "naver".equals(providerUser.getProvider());
        providerUser.isCertificated(bool);
//        }
    }

    public void certificate(ProviderUser providerUser) {

    }
}
