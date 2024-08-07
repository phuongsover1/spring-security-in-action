package com.learn_security.repositories;

import com.learn_security.models.Token;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

@Component
public class CustomCsrfTokenRepository implements CsrfTokenRepository {
    private final JpaTokenRepository tokenRepository;

    private final Logger logger = Logger.getLogger(CustomCsrfTokenRepository.class.getName());

    public CustomCsrfTokenRepository(JpaTokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public CsrfToken generateToken(HttpServletRequest request) {
        logger.info("Inside generateToken");

        String uuid = UUID.randomUUID().toString();
        return new DefaultCsrfToken("X-CSRF-TOKEN", "_csrf", uuid);
    }

    @Override
    public void saveToken(CsrfToken csrfToken, HttpServletRequest request, HttpServletResponse response) {
        logger.info("Inside saveToken");
        String identifier = request.getHeader("X-IDENTIFIER");

        Optional<Token> existingToken = tokenRepository.findTokenByIdentifier(identifier);

        Token token;
        if (existingToken.isPresent()) {
            token = existingToken.get();
            token.setToken(csrfToken.getToken());
        } else {
            token = new Token();
            token.setToken(csrfToken.getToken());
            token.setIdentifier(identifier);
        }
        tokenRepository.save(token);

    }

    @Override
    public CsrfToken loadToken(HttpServletRequest request) {
        logger.info("Inside loadToken");
        String identifier = request.getHeader("X-IDENTIFIER");

        Optional<Token> existingToken = tokenRepository.findTokenByIdentifier(identifier);

        if (existingToken.isPresent()) {
            Token token = existingToken.get();
            return new DefaultCsrfToken("X-CSRF-TOKEN", "_csrf", token.getToken());
        }
        return null;
    }
}
