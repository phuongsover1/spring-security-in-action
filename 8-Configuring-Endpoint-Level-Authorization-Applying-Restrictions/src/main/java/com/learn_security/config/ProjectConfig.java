package com.learn_security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
public class ProjectConfig {
    @Bean
    public UserDetailsService userDetailsService() {
        User.UserBuilder builder = User.builder()
                .disabled(false);
        UserDetails u1 = builder.username("john")
                .password("12345")
                .roles("ADMIN")
                .build();

        UserDetails u2 = builder.username("jane")
                .password("123456")
                .roles("MANAGER")
                .build();
        return new InMemoryUserDetailsManager(List.of(u1, u2));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)
            throws Exception {

        http.httpBasic(Customizer.withDefaults());

        http.authorizeHttpRequests(c -> {
            c.requestMatchers("/a/b/**")
                    .authenticated();
            c.anyRequest()
                    .permitAll();
        });

        http.csrf(c -> c.disable());

        return http.build();
    }

}
