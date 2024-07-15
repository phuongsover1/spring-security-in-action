package com.learn_security.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public ResponseEntity<String> getHello(CsrfToken token, HttpServletRequest request) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-CSRF-TOKEN", token.getToken());
        headers.add("X-IDENTIFIER", request.getHeader("X-IDENTIFIER"));
        return new ResponseEntity<>("GET Hello!", headers, HttpStatus.OK);
    }

    @PostMapping("/hello")
    public String postHello() {
        return "Post Hello!";
    }

    @PostMapping("/ciao")
    public String postCiao() {
        return "Post Ciao!";
    }
}
