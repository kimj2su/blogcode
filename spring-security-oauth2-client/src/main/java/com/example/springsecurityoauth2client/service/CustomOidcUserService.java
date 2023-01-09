package com.example.springsecurityoauth2client.service;

import com.example.springsecurityoauth2client.certification.SelfCertification;
import com.example.springsecurityoauth2client.common.converters.ProviderUserConverter;
import com.example.springsecurityoauth2client.common.converters.ProviderUserRequest;
import com.example.springsecurityoauth2client.model.PrincipalUser;
import com.example.springsecurityoauth2client.model.ProviderUser;
import com.example.springsecurityoauth2client.repository.UserRepository;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

@Service
public class CustomOidcUserService extends AbstractOAuth2UserService implements OAuth2UserService<OidcUserRequest, OidcUser> {


    public CustomOidcUserService(UserRepository userRepository, UserService userService, SelfCertification certification, ProviderUserConverter<ProviderUserRequest, ProviderUser> providerUserConverter) {
        super(userRepository, userService, certification, providerUserConverter);
    }

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
//        ClientRegistration clientRegistration = userRequest.getClientRegistration();

        //여기에는 이전 registrationID 가 저장 되어 있으므로 재정의 해줘야한다.
        ClientRegistration clientRegistration = ClientRegistration.withClientRegistration(userRequest.getClientRegistration())
                .userNameAttributeName("sub")
                .build();
        OidcUserRequest oidcUserRequest = new OidcUserRequest(clientRegistration,
                userRequest.getAccessToken(),
                userRequest.getIdToken(),
                userRequest.getAdditionalParameters());


        OAuth2UserService<OidcUserRequest, OidcUser> oidcUserService = new OidcUserService();
//        OidcUser oidcUser = oidcUserService.loadUser(userRequest);
        OidcUser oidcUser = oidcUserService.loadUser(oidcUserRequest);

        ProviderUserRequest providerUserRequest = new ProviderUserRequest(clientRegistration, oidcUser);

        ProviderUser providerUser = providerUser(providerUserRequest);
        //회원 가입 하기
        super.register(providerUser, userRequest);


        return new PrincipalUser(providerUser);
    }
}
