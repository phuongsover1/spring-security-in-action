package com.learn_security.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
    @GetMapping("/email/{email}")
    public String email(@PathVariable String email) {
       return "Allowed for email " + email;
    }
}
