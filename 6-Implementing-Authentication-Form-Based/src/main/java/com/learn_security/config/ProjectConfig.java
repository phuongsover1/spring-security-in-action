package com.learn_security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
    private final CustomAuthenticationSuccessHandler authenticationSuccessHandler;

    public ProjectConfig(CustomAuthenticationSuccessHandler authenticationSuccessHandler) {
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http.formLogin(c -> {
            c.defaultSuccessUrl("/home", true);
            c.successHandler(authenticationSuccessHandler);
        });

        http.authorizeHttpRequests(c -> c.anyRequest().authenticated());
        return http.build();
    }

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
