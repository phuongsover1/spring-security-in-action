package com.learnsecurity.configurations;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component
public class JwtAuthenticationConverter implements Converter<Jwt, JwtAuthenticationToken> {
    private final Logger log = Logger.getLogger(this.getClass().getName());

    @Override
    public JwtAuthenticationToken convert(Jwt source) {
        log.info("authorities: " + source.getClaim("authorities"));
        Collection<GrantedAuthority> customGrantedAuthorities = source.getClaimAsStringList("authorities").stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        log.info("authorities after conversion: " + customGrantedAuthorities);
        return new JwtAuthenticationToken(source, customGrantedAuthorities);
    }
}
