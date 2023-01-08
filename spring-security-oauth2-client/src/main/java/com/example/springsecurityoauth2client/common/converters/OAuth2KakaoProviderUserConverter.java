package com.example.springsecurityoauth2client.common.converters;

import com.example.springsecurityoauth2client.common.enums.OAuth2Config;
import com.example.springsecurityoauth2client.common.util.OAuth2Utils;
import com.example.springsecurityoauth2client.model.ProviderUser;
import com.example.springsecurityoauth2client.model.social.KakaoUser;
import com.example.springsecurityoauth2client.model.social.NaverUser;

public class OAuth2KakaoProviderUserConverter implements ProviderUserConverter<ProviderUserRequest, ProviderUser> {

    @Override
    public ProviderUser converter(ProviderUserRequest providerUserRequest) {
        if (!providerUserRequest.clientRegistration().getRegistrationId().equals(OAuth2Config.SocialType.NAVER.getSocialName())) {
            return null;
        }

        return new KakaoUser(OAuth2Utils.getOtherAttributes(providerUserRequest.oAuth2User(), "kakao_account", "profile"),
                providerUserRequest.oAuth2User(),
                providerUserRequest.clientRegistration()
        );
    }
}
