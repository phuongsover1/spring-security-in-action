package com.learn_security.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Configuration
public class ProjectConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors(c -> {
            CorsConfigurationSource source = request -> {
                CorsConfiguration config = new CorsConfiguration();
                config.setAllowedOrigins(
                        List.of("http://localhost:8080", "example.org")
                );
                config.setAllowedMethods(
                        List.of("GET", "POST", "PUT", "DELETE")
                );
                config.setAllowedHeaders(List.of("*"));
                return config;
            };
            c.configurationSource(source);
        });
        http.csrf(c -> c.disable());

        http.authorizeHttpRequests(c -> c.anyRequest().permitAll());

        return http.build();
    }
}
