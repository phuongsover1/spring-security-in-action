package com.learn_security.config;

import com.learn_security.config.services.InMemoryUserDetailsService;
import com.learn_security.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
        List<UserDetails> users = List.of(new User("phuong", "12345", "READ"));
        return new InMemoryUserDetailsService(users);

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
