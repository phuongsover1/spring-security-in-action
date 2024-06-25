package com.learn_security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectConfig {

    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.httpBasic(Customizer.withDefaults());

        http.authorizeHttpRequests(c -> c.anyRequest().authenticated());

        // Implement UserDetailsService interface
        var user = User.withUsername("phuong")
                .password("12345")
                .authorities("read")
                .build();

        var userDetailsService = new InMemoryUserDetailsManager(user);

        http.userDetailsService(userDetailsService);

        return http.build();
    }

    /* If you* need to add define those beans to the context, lets you inject the values in another class where
    * you might potentially need them.
    * */
//    @Bean
//    UserDetailsService userDetailsService() {
//        var user = User.withUsername("phuong")
//                .password("12345")
//                .authorities("read")
//                .build();
//        return new InMemoryUserDetailsManager(user);
//    }
//
    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
