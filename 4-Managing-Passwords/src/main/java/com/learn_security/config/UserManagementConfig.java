package com.learn_security.config;

import com.learn_security.config.services.InMemoryUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class UserManagementConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        User.UserBuilder userBuilder = User.builder()
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .authorities("READ")
                .passwordEncoder(p -> passwordEncoder().encode(p));

        UserDetails u1 = userBuilder.username("phuong")
                .password("123456")
                .build();

        UserDetails u2 = userBuilder.username("john")
                .password("12345")
                .build();

        return new InMemoryUserDetailsService(List.of(u1, u2));

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        Map<String, PasswordEncoder> encoders = new HashMap<>();

        encoders.put("noop", NoOpPasswordEncoder.getInstance());
        encoders.put("bcrypt", new BCryptPasswordEncoder(10)) ;
        encoders.put("scrypt", new SCryptPasswordEncoder(16384, 8, 1, 32, 64));

        return new DelegatingPasswordEncoder("bcrypt", encoders);
    }
}
