package com.learnsecurity.configurations;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.logging.Logger;

@Component
public class GeneratingVerifierAndChallengeCode implements ApplicationRunner {
    private Logger logger = Logger.getLogger(GeneratingVerifierAndChallengeCode.class.getName());

    @Override
    public void run(ApplicationArguments args) throws Exception {
        SecureRandom secureRandom = new SecureRandom();
        byte[] code = new byte[32];
        secureRandom.nextBytes(code);
        String codeVerifier = Base64.getUrlEncoder()
                .withoutPadding()
                .encodeToString(code);

        logger.info("Authorization Server 2 Verifier code: " + codeVerifier);

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] digest = messageDigest.digest(codeVerifier.getBytes());
        String codeChallenge = Base64.getUrlEncoder()
                .withoutPadding()
                .encodeToString(digest);
        logger.info("Authorization Server 2 Challenge code: " + codeChallenge);

    }
}
