package com.example.springsecurityoauth2client.common.converters;

import com.example.springsecurityoauth2client.common.enums.OAuth2Config;
import com.example.springsecurityoauth2client.model.ProviderUser;
import com.example.springsecurityoauth2client.model.social.GoogleUser;
import com.example.springsecurityoauth2client.common.util.OAuth2Utils;

public class OAuth2GoogleProviderUserConverter implements ProviderUserConverter<ProviderUserRequest, ProviderUser> {

    @Override
    public ProviderUser converter(ProviderUserRequest providerUserRequest) {

        if (!providerUserRequest.clientRegistration().getRegistrationId().equals(OAuth2Config.SocialType.GOOGLE.getSocialName())) {
            return null;
        }

        return new GoogleUser(OAuth2Utils.getMainAttributes(providerUserRequest.oAuth2User()),
                providerUserRequest.oAuth2User(),
                providerUserRequest.clientRegistration()
        );
    }
}
