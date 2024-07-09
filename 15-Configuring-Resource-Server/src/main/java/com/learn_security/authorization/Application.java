package com.learn_security.authorization;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.logging.Logger;

@SpringBootApplication
public class Application {

    private Logger logger = Logger.getLogger(Application.class.getName());

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
