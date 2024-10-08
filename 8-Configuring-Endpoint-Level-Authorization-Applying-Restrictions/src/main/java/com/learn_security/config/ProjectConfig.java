package com.learn_security.config;

import com.learn_security.CsrfTokenLogger;
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
import org.springframework.security.web.csrf.CsrfFilter;

import java.util.List;

@Configuration
public class ProjectConfig {
    @Bean
    public UserDetailsService userDetailsService() {
        User.UserBuilder builder = User.builder()
                .disabled(false);
        UserDetails u1 = builder.username("john")
                .password("12345")
                .authorities("READ")
                .build();

        UserDetails u2 = builder.username("jane")
                .password("123456")
                .authorities("READ", "PREMIUM")
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

        http.addFilterAfter(new CsrfTokenLogger(), CsrfFilter.class);

        http.authorizeHttpRequests(c -> c.anyRequest().permitAll());


        return http.build();
    }

}
