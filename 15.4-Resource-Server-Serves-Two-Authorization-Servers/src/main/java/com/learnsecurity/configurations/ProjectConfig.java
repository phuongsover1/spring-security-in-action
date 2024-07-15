package com.learnsecurity.configurations;

import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.jwk.source.RemoteJWKSet;
import com.nimbusds.jose.proc.SecurityContext;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.security.oauth2.server.resource.authentication.JwtIssuerAuthenticationManagerResolver;
import org.springframework.security.web.SecurityFilterChain;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableMethodSecurity
public class ProjectConfig {

    private final JwtAuthenticationConverter jwtAuthenticationConverter;

    @Value("${authorization_server_1}")
    private String authorizationServer_1;
    @Value("${authorization_server_2}")
    private String authorizationServer_2;

    @Value("${server1_keySetURI}")
    private String server_1_ketSetURI;
   @Value("${server2_keySetURI}")
    private String server_2_ketSetURI;

    public ProjectConfig(JwtAuthenticationConverter jwtAuthenticationConverter) {
        this.jwtAuthenticationConverter = jwtAuthenticationConverter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.oauth2ResourceServer(
                j -> {
                    try {
                        j.authenticationManagerResolver(
                                authenticationManagerResolver()
                        );
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
        );
        http.authorizeHttpRequests(c -> c.anyRequest().authenticated());
        return http.build();
    }

    @Bean
    public JwtIssuerAuthenticationManagerResolver authenticationManagerResolver() throws Exception {
        Map<String, AuthenticationManager> authenticationManagers = new HashMap<>();
        authenticationManagers.put(authorizationServer_1, authenticationManager(server_1_ketSetURI));
        authenticationManagers.put(authorizationServer_2, authenticationManager(server_2_ketSetURI));

        return new JwtIssuerAuthenticationManagerResolver(authenticationManagers::get);
    }

    private AuthenticationManager authenticationManager(String jwkSetURI) throws Exception {
        JwtDecoder jwtDecoder = NimbusJwtDecoder.withJwkSetUri(jwkSetURI).build();

        JwtAuthenticationProvider jwtAuthenticationProvider = new JwtAuthenticationProvider(jwtDecoder);
        jwtAuthenticationProvider.setJwtAuthenticationConverter(jwtAuthenticationConverter);

        return jwtAuthenticationProvider::authenticate;
    }



}
