package com.learnsecurity.configurations;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManagerResolver;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtIssuerAuthenticationManagerResolver;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class ProjectConfig {

    @Value("${authorization_server_1}")
    private String authorizationServer_1;

    @Value("${authorization_server_2}")
    private String authorizationServer_2;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.oauth2ResourceServer(
                j -> j.authenticationManagerResolver(
                        authenticationManagerResolver()
                )
        );
        http.authorizeHttpRequests(c -> c.anyRequest().authenticated());
        return http.build();
    }

    ;

    @Bean
    public AuthenticationManagerResolver<HttpServletRequest> authenticationManagerResolver() {
        var a = new JwtIssuerAuthenticationManagerResolver(
                authorizationServer_1, authorizationServer_2
        );
        return a;
    }
}
