package com.learn_security.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class ProjectConfig {

   @Value("${keycloak.jwkSetURI}")
   private String jwkSetURI;

   @Bean
   public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
      http.authorizeExchange(c -> c.anyExchange().authenticated());

      http.oauth2ResourceServer(c -> {
         c.jwt(jwkc -> jwkc.jwkSetUri(jwkSetURI));
      });

      return http.build();
   }
}
