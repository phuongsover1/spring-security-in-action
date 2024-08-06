package com.learn_security.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserManagementConfig {
  @Bean
  public UserDetailsService userDetailsService() {
    User.UserBuilder builder = User.builder().authorities("READ")
        .password("12345")
        .disabled(false);

    UserDetails u1 = builder.username("john")
        .build();

    UserDetails u2 = builder.username("mary").build();

    return new InMemoryUserDetailsManager(u1,u2);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }
}
