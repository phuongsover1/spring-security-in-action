package com.learn_security.authorization.controllers;

import com.learn_security.authorization.configurations.CustomAuthentication;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/demo")
    public Authentication demo(Authentication a) {
        return a;
    }
}
