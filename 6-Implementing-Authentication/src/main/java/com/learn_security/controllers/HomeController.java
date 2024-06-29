package com.learn_security.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@Slf4j
public class HomeController {

    @GetMapping("/hello")
    public String hello(Authentication a) {
        return "Hello!" + a.getName() + "!";
    }

    @GetMapping("/bye")
    @Async
    public void goodbye() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String username = securityContext.getAuthentication().getName();
        log.info("username in goodbye method: {}", username);
    }

    @GetMapping("/ciao")
    public String ciao() throws Exception {
        Callable<String> task = () -> {
            log.info("Inside ExecutorService running task");
            SecurityContext securityContext = SecurityContextHolder.getContext();
            return securityContext.getAuthentication().getName();
        };

        ExecutorService e = Executors.newCachedThreadPool();
        try {
            return "Ciao, " + e.submit(task).get() + "!";
        } finally {
            e.shutdown();
        }
    }

}
