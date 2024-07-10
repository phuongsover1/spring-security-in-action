package com.learn_security.authorization.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;

import java.time.Duration;
import java.util.UUID;

@Configuration
public class ClientManagementConfig {

    @Bean
    public RegisteredClientRepository registeredClientRepository() {
        RegisteredClient registeredClient =
                RegisteredClient.withId(UUID.randomUUID().toString())
                        .clientId("client")
                        .clientSecret("secret")
                        .clientAuthenticationMethod(
                                ClientAuthenticationMethod.CLIENT_SECRET_BASIC
                        )
                        .authorizationGrantType(
                                AuthorizationGrantType.CLIENT_CREDENTIALS
                        )
                        .tokenSettings(TokenSettings.builder()
                                .accessTokenFormat(OAuth2TokenFormat.REFERENCE)
                                .accessTokenTimeToLive(Duration.ofHours(1))
                                .build())
                        .scope("CUSTOM")
                        .build();

        RegisteredClient resourceServer =
                RegisteredClient.withId(UUID.randomUUID().toString())
                        .clientId("resource_server")
                        .clientSecret("resource_server_secret")
                        .clientAuthenticationMethod(
                                ClientAuthenticationMethod.CLIENT_SECRET_BASIC
                        )
                        .authorizationGrantType(
                                AuthorizationGrantType.CLIENT_CREDENTIALS
                        )
                        .build();

        return new InMemoryRegisteredClientRepository(registeredClient, resourceServer);

    }
}
