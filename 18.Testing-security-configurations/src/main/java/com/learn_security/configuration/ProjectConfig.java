package com.learn_security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class ProjectConfig {

   @Bean
   public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
      http.httpBasic(Customizer.withDefaults());
      http.authorizeHttpRequests(c -> c.anyRequest().authenticated());

      return http.build();
   }

   @Bean
   public UserDetailsService userDetailsService() {
      UserDetails u1 = User.builder()
          .username("john")
          .password("12345")
          .authorities("READ")
          .build();

      UserDetails u2 = User.builder()
          .username("mary")
          .password("12345")
          .authorities("WRITE")
          .build();
      return new InMemoryUserDetailsManager(u1,u2);
   }
}
