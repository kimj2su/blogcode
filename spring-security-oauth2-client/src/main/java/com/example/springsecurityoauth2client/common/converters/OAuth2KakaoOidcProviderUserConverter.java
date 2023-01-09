package com.example.springsecurityoauth2client.common.converters;

import com.example.springsecurityoauth2client.common.enums.OAuth2Config;
import com.example.springsecurityoauth2client.common.util.OAuth2Utils;
import com.example.springsecurityoauth2client.model.ProviderUser;
import com.example.springsecurityoauth2client.model.social.KakaoOidcUser;
import com.example.springsecurityoauth2client.model.social.KakaoUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

public class OAuth2KakaoOidcProviderUserConverter implements ProviderUserConverter<ProviderUserRequest, ProviderUser> {

    @Override
    public ProviderUser converter(ProviderUserRequest providerUserRequest) {
        if (!providerUserRequest.clientRegistration().getRegistrationId().equals(OAuth2Config.SocialType.KAKAO.getSocialName())) {
            return null;
        }

        if (!(providerUserRequest.oAuth2User() instanceof OidcUser)) {
            return null;
        }

        return new KakaoOidcUser(OAuth2Utils.getMainAttributes(providerUserRequest.oAuth2User()),
                providerUserRequest.oAuth2User(),
                providerUserRequest.clientRegistration()
        );
    }
}
