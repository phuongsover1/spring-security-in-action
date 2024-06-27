package com.learn_security.config;

import com.learn_security.config.services.InMemoryUserDetailsService;
import com.learn_security.config.services.PlainTextPasswordEncoder;
import lombok.extern.slf4j.Slf4j;
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
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Configuration
public class UserManagementConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        User.UserBuilder userBuilder = User.builder()
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .authorities("READ");


        UserDetails u1 = userBuilder.username("NoOpUser")
                .password(new PlainTextPasswordEncoder().encode("{noop}noop_password"))
                .build();


        UserDetails u3 = userBuilder.username("DefaultBcryptUser")
                .password(passwordEncoder().encode("default_bcrypt_password"))
                .build();

        UserDetails u4 = userBuilder.username("ScryptUser")
                .password("{scrypt}" + new SCryptPasswordEncoder(16384, 8, 1, 32, 64).encode("scrypt_password"))
                .build();

        log.info("NoOpUser's password: {}", u1.getPassword());
        log.info("DefaultBcryptUser's password: {}", u3.getPassword());
        log.info("ScryptUser's password: {}", u4.getPassword());

        return new InMemoryUserDetailsManager(List.of(u1, u3, u4));

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        Map<String, PasswordEncoder> encoders = new HashMap<>();

        encoders.put("noop", NoOpPasswordEncoder.getInstance());
        encoders.put("bcrypt", new BCryptPasswordEncoder(10));
        encoders.put("scrypt", new SCryptPasswordEncoder(16384, 8, 1, 32, 64));

        return new DelegatingPasswordEncoder("bcrypt", encoders);
    }
}
