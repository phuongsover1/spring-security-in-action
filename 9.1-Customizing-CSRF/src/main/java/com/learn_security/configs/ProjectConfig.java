package com.learn_security.configs;

import com.learn_security.repositories.CustomCsrfTokenRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectConfig {

    private final CustomCsrfTokenRepository customCsrfTokenRepository;

    public ProjectConfig(CustomCsrfTokenRepository csrfTokenRepository) {
        this.customCsrfTokenRepository = csrfTokenRepository;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(c -> {
            c.csrfTokenRepository(customCsrfTokenRepository);
            c.ignoringRequestMatchers("/ciao");
        });

        http.authorizeHttpRequests(c -> c.anyRequest().permitAll());
        return http.build();
    }
}
