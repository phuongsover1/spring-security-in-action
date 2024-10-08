package com.learn_security.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HomeController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello!";
    }


    @PostMapping("/hello")
    public String postHello() {
        return "Post Hello!";
    }
}
