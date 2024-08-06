package com.learn_security.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.List;

@Configuration
public class UserManagementConfig {
  @Bean
  public UserDetailsService userDetailsService() {
    User.UserBuilder builder = User.builder()
        .disabled(false);
    UserDetails u1 = builder.username("phuong")
        .password("12345")
        .authorities(List.of(() -> "READ"))
        .build();

    UserDetails u2 = builder.username("john")
        .password("123456")
        .authorities("READ", "WRITE")
        .build();
    return new InMemoryUserDetailsManager(List.of(u1, u2));
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }
}
