package com.learn_security.config;

import com.learn_security.config.filter.AuthenticationLoggingFilter;
import com.learn_security.config.filter.StaticKeyAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class WebAuthorizationConfig {

    private final StaticKeyAuthenticationFilter filter;

    public WebAuthorizationConfig(StaticKeyAuthenticationFilter filter) {
        this.filter = filter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.addFilterAt(filter, BasicAuthenticationFilter.class);
        http.addFilterAfter(new AuthenticationLoggingFilter(), BasicAuthenticationFilter.class);

        http.authorizeHttpRequests(c -> c.anyRequest().permitAll());

        return http.build();
    }
}
