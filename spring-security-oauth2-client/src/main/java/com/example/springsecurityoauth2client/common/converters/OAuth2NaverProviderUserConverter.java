package com.example.springsecurityoauth2client.common.converters;

import com.example.springsecurityoauth2client.common.enums.OAuth2Config;
import com.example.springsecurityoauth2client.model.ProviderUser;
import com.example.springsecurityoauth2client.model.social.NaverUser;
import com.example.springsecurityoauth2client.common.util.OAuth2Utils;

public class OAuth2NaverProviderUserConverter implements ProviderUserConverter<ProviderUserRequest, ProviderUser> {

    @Override
    public ProviderUser converter(ProviderUserRequest providerUserRequest) {
        if (!providerUserRequest.clientRegistration().getRegistrationId().equals(OAuth2Config.SocialType.NAVER.getSocialName())) {
            return null;
        }

        return new NaverUser(OAuth2Utils.getSubAttributes(providerUserRequest.oAuth2User(), "response"),
                providerUserRequest.oAuth2User(),
                providerUserRequest.clientRegistration()
        );
    }
}
