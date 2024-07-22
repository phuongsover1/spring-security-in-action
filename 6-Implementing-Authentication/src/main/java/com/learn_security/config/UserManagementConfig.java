package com.learn_security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserManagementConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        var userDetailServices = new InMemoryUserDetailsManager();

        User.UserBuilder builder = User.builder().authorities("READ")
                .disabled(false);

        UserDetails u1 = builder
                .username("phuong")
                .password(passwordEncoder().encode("12345"))
                .build();

        UserDetails u2 = builder
                .username("john")
                .password(passwordEncoder().encode("123456"))
                .build();

        userDetailServices.createUser(u1);
        userDetailServices.createUser(u2);
        return userDetailServices;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
