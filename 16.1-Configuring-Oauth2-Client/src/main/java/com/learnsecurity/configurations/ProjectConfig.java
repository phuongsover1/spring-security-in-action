package com.learnsecurity.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ProjectConfig {
    @Value("${GOOGLE_CLIENT_ID}")
    private String googleClientId;

    @Value("${GOOGLE_CLIENT_SECRET}")
    private String googleClientSecret;

    @Value("${GITHUB_CLIENT_ID}")
    private String githubClientId;

    @Value("${GITHUB_CLIENT_SECRET}")
    private String githubClientSecret;

    @Value("${enabled_oauth2_provider}")
    private String enabledOauth2Providers;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.oauth2Client(Customizer.withDefaults());

        http.authorizeHttpRequests(c -> c.anyRequest().permitAll());

        return http.build();
    }

    @Bean
    @Profile("!client-credentials")
    public ClientRegistrationRepository clientRegistrationRepository() {
        if (enabledOauth2Providers == null) {
            return new InMemoryClientRegistrationRepository();
        }
        List<ClientRegistration> clientRegistrationList = new ArrayList<>();
        for (String providerId : enabledOauth2Providers.split(",")) {
            if (providerId.equals("google")) {
                clientRegistrationList.add(googleClientRegistration());
            } else if (providerId.equals("github")) {
                clientRegistrationList.add(githubClientRegistration());
            }

        }
        return new InMemoryClientRegistrationRepository(clientRegistrationList);
    }

    @Bean
    @Profile("client-credentials")
    public ClientRegistrationRepository clientRegistrationRepository_ClientCredentials() {
        ClientRegistration c1 =
                ClientRegistration.withRegistrationId("1")
                        .clientId("client")
                        .clientSecret("secret")
                        .authorizationGrantType(
                                AuthorizationGrantType.CLIENT_CREDENTIALS
                        )
                        .tokenUri("http://127.0.0.1:7070/oauth2/token")
                        .scope(OidcScopes.OPENID)
                        .build();

        return new InMemoryClientRegistrationRepository(c1);
    }


    private ClientRegistration googleClientRegistration() {
        return CommonOAuth2Provider.GOOGLE.getBuilder("google")
                .clientId(googleClientId)
                .clientSecret(googleClientSecret)
                .build();
    }

    private ClientRegistration githubClientRegistration() {
        return CommonOAuth2Provider.GITHUB.getBuilder("github")
                .clientId(githubClientId)
                .clientSecret(githubClientSecret)
                .build();
    }
}
