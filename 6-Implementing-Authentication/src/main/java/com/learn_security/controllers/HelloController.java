package com.learn_security.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {

        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication a = securityContext.getAuthentication();
        return "Hello!"  + a.getName() + "!";
    }
}
