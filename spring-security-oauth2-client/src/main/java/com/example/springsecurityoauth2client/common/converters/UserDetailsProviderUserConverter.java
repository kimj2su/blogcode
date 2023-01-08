package com.example.springsecurityoauth2client.common.converters;

import com.example.springsecurityoauth2client.common.enums.OAuth2Config;
import com.example.springsecurityoauth2client.common.util.OAuth2Utils;
import com.example.springsecurityoauth2client.model.ProviderUser;
import com.example.springsecurityoauth2client.model.social.NaverUser;
import com.example.springsecurityoauth2client.model.users.FormUser;
import com.example.springsecurityoauth2client.model.users.User;

public class UserDetailsProviderUserConverter implements ProviderUserConverter<ProviderUserRequest, ProviderUser> {

    @Override
    public ProviderUser converter(ProviderUserRequest providerUserRequest) {
        if (providerUserRequest.user() == null) {
            return null;
        }

        User user = providerUserRequest.user();
        return FormUser.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .provider("none")
                .authorities(user.getAuthorities())
                .build();
    }
}
