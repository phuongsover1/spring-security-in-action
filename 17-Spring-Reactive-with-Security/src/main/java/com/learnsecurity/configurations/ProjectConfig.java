package com.learnsecurity.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import reactor.core.publisher.Mono;

import java.time.LocalTime;
import java.util.function.Function;

@Configuration
@EnableWebFluxSecurity
public class ProjectConfig {

  @Bean
  public ReactiveUserDetailsService userDetailsService() {
    var u = User.withUsername("john")
        .password("12345")
        .roles("ADMIN")
        .build();

    var uds = new MapReactiveUserDetailsService(u);

    return uds;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }

  @Bean
  public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) throws Exception {

    http.httpBasic(Customizer.withDefaults());

    http.authorizeExchange(c -> c.anyExchange().access(this::getAuthorizationDecisionMono));


    return http.build();
  }

  private Mono<AuthorizationDecision>
  getAuthorizationDecisionMono(Mono<Authentication> a,
                               AuthorizationContext c) {
    String path = getRequestPath(c);

    boolean restrictedTime = LocalTime.now().isBefore(LocalTime.NOON);

    if (path.equals("/hello")) {
      return a.map(isAdmin())
          .map(auth -> auth && !restrictedTime)
//          .map(auth -> auth)
          .map(AuthorizationDecision::new);
    }

//    return Mono.just(new AuthorizationDecision(!restrictedTime));
    return Mono.just(new AuthorizationDecision(true));
  }

  private String getRequestPath(AuthorizationContext c) {
    return c.getExchange()
        .getRequest()
        .getPath()
        .toString();
  }

  private Function<Authentication, Boolean> isAdmin() {
    return p -> p.getAuthorities()
        .stream()
        .anyMatch(e -> e.getAuthority().equals("ROLE_ADMIN"));
  }
}
