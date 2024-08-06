package com.learn_security.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectConfig {
  private final CustomAuthenticationSuccessHandler authenticationSuccessHandler;
  private final CustomAuthenticationFailureHandler authenticationFailureHandler;

  public ProjectConfig(CustomAuthenticationSuccessHandler authenticationSuccessHandler, CustomAuthenticationFailureHandler authenticationFailureHandler) {
    this.authenticationSuccessHandler = authenticationSuccessHandler;
    this.authenticationFailureHandler = authenticationFailureHandler;
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.formLogin(c -> {
      c.successHandler(authenticationSuccessHandler);
      c.failureHandler(authenticationFailureHandler);
    });

    http.authorizeHttpRequests(c -> c.anyRequest().authenticated());

    return http.build();
  }
}
