package com.learnsecurity.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class DemoController {
    private Logger logger = Logger.getLogger(DemoController.class.getName());

    @GetMapping("/demo")
    public Authentication demo(Authentication authentication) {
        logger.info("authentication: " + authentication.getAuthorities());
        return authentication;
    }
}