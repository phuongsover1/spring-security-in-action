package com.learn_security.authorization;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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

    @Bean
    public CommandLineRunner setUpTempVerifierAndChallengeCode() {
        return args -> {
            SecureRandom secureRandom = new SecureRandom();
            byte[] code = new byte[32];
            secureRandom.nextBytes(code);
            String toBase64CodeVerifier = Base64.getUrlEncoder()
                    .withoutPadding()
                    .encodeToString(code);

            logger.info("Verifier code: " + toBase64CodeVerifier);

            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

            byte[] digested = messageDigest.digest(toBase64CodeVerifier.getBytes());
            String toBase64CodeChallenge = Base64.getUrlEncoder()
                    .withoutPadding()
                    .encodeToString(digested);

            logger.info("Challenge code: " + toBase64CodeChallenge);

        };
    }

}
