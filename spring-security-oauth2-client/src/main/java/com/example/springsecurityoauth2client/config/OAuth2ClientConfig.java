package com.example.springsecurityoauth2client.config;

import com.example.springsecurityoauth2client.common.authority.CustomAuthorityMapper;
import com.example.springsecurityoauth2client.service.CustomOAuth2UserService;
import com.example.springsecurityoauth2client.service.CustomOidcUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestResolver;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;


@Configuration
public class OAuth2ClientConfig {

    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;

    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;

    @Autowired
    private CustomOidcUserService customOidcUserSeervide;
    /*
     LoginPage 생성
     */
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeRequests(oauthRequest -> oauthRequest
//                .antMatchers("/loginPage")
//                .permitAll()
//                .anyRequest().authenticated());
//        http.oauth2Login(oauth2 -> oauth2.loginPage("/loginPage"));
//
//        return http.build();
//    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/static/js/**", "/static/images/**", "/static/css/**", "/static/scss/**");
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //social Login
        http.authorizeRequests(authRequest -> authRequest
                .antMatchers("/api/user").access("hasAnyRole('SCOPE_profile', 'SCOPE_email')")
                .antMatchers("/api/oidc").access("hasAnyRole('SCOPE_openid')")
                .antMatchers("/home", "/client", "/logout", "/oauth2Login", "/").permitAll()
                .anyRequest().authenticated());
//                .oauth2Login(Customizer.withDefaults())
//                .oauth2Client(Customizer.withDefaults());
        http.formLogin().loginPage("/login").loginProcessingUrl("/loginProc").defaultSuccessUrl("/").permitAll();
        http.exceptionHandling().authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"));
        http.oauth2Login(oauth2 ->
                oauth2.userInfoEndpoint(userInfoEndpointConfig ->
                        userInfoEndpointConfig
                                .userService(customOAuth2UserService) //OAuth2
                                .oidcUserService(customOidcUserSeervide))); //OpenID Connect
//        http.logout().logoutSuccessUrl("/");

//        http.authorizeRequests(oauthRequest -> oauthRequest.antMatchers("/login").permitAll()
//                .antMatchers("/CustomOAuth2AuthorizationRequestsResolver").permitAll()
//                .anyRequest().authenticated());
//        http.oauth2Login(oauthLogin -> oauthLogin.authorizationEndpoint(authEndpoint ->
//                authEndpoint.authorizationRequestResolver(customAuth2AuthorizationRequestResolver())));
        /*
         Authorization BaseUri & Redirection BaseUri 커스텀
         */
//        http.oauth2Login(oauth2 -> oauth2.loginPage("/login")
//                .authorizationEndpoint(authorizationEndpointConfig ->
//                        authorizationEndpointConfig.baseUri("/oauth2/v1/authorization"))
//                .redirectionEndpoint(redirectionEndpointConfig ->
//                        redirectionEndpointConfig.baseUri("/login/v1/oauth2/code/*"))
//        );
        //openid connect 로그아웃
//        http.logout()
//                .logoutSuccessHandler(oidcLogoutSuccessHandler())
//                .invalidateHttpSession(true)
//                .clearAuthentication(true)
//                .deleteCookies("JSESSIONID");
        return http.build();
    }

    @Bean
    public GrantedAuthoritiesMapper customAuthorityMapper() {
        return new CustomAuthorityMapper();
    }

    private OAuth2AuthorizationRequestResolver customAuth2AuthorizationRequestResolver() {
        return new CustomOAuth2AuthorizationRequestResolver(clientRegistrationRepository, "/oauth2/authorization");
    }

    //openid connect 로그아웃
    private LogoutSuccessHandler oidcLogoutSuccessHandler() {

        OidcClientInitiatedLogoutSuccessHandler successHandler = new OidcClientInitiatedLogoutSuccessHandler(clientRegistrationRepository);
        successHandler.setPostLogoutRedirectUri("http://localhost:8081/login");

        return successHandler;
    }
}
