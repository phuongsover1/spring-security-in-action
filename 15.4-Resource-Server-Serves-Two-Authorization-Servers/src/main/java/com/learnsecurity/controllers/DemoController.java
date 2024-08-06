package com.learnsecurity.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

@RestController
public class DemoController {
    private Logger logger = Logger.getLogger(DemoController.class.getName());

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/demo")
    public Authentication demo(Authentication authentication) {
        List<String> convertedAuthorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        logger.info("Converted Authorities: " + convertedAuthorities.toString());
        return authentication;
    }
}