package com.learn_security.configs;

import com.learn_security.repositories.CustomCsrfTokenRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;

@Configuration
@EnableWebSecurity
public class ProjectConfig {

    private final CustomCsrfTokenRepository customCsrfTokenRepository;

    public ProjectConfig(CustomCsrfTokenRepository csrfTokenRepository) {
        this.customCsrfTokenRepository = csrfTokenRepository;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(c -> {
            c.csrfTokenRepository(customCsrfTokenRepository);
            c.csrfTokenRequestHandler(
                    new CsrfTokenRequestAttributeHandler()
            );
        });

        http.authorizeHttpRequests(c -> c.anyRequest().permitAll());
        return http.build();
    }
}
