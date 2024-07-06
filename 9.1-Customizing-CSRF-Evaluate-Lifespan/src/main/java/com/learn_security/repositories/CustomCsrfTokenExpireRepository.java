package com.learn_security.repositories;

import com.learn_security.models.TokenExpire;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;


@Component
public class CustomCsrfTokenExpireRepository implements CsrfTokenRepository {
    private final Logger logger = Logger.getLogger(CustomCsrfTokenRepository.class.getName());
    private final JpaTokenExpireRepository tokenExpireRepository;

    public CustomCsrfTokenExpireRepository(JpaTokenExpireRepository tokenExpireRepository) {
        this.tokenExpireRepository = tokenExpireRepository;
    }

    @Override
    public CsrfToken generateToken(HttpServletRequest request) {
        String uuid = UUID.randomUUID().toString();
        return new DefaultCsrfToken("X-CSRF-TOKEN", "_csrf", uuid);
    }

    @Override
    public void saveToken(CsrfToken token, HttpServletRequest request, HttpServletResponse response) {
        logger.info("in saveToken");
        if (request.getMethod().equals(HttpMethod.GET.toString())) {
            TokenExpire tokenExpire = new TokenExpire();
            tokenExpire.setToken(token.getToken());
            tokenExpireRepository.save(tokenExpire);
        }
    }

    @Override
    public CsrfToken loadToken(HttpServletRequest request) {
        logger.info("in loadToken");
        String csrfTokenString = request.getHeader("X-CSRF-TOKEN");
        if (csrfTokenString != null) {
            Optional<TokenExpire> optionalTokenExpire = tokenExpireRepository.findTokenExpireByToken(csrfTokenString);
            if (optionalTokenExpire.isPresent()) {
                TokenExpire tokenExpire = optionalTokenExpire.get();
                logger.info("is expired : " + tokenExpire.getDateTimeCreated().plusMinutes(3).isBefore(LocalDateTime.now()));

                if (tokenExpire.getDateTimeCreated().plusMinutes(3).isBefore(LocalDateTime.now()))
                    return null;
                else
                    return new DefaultCsrfToken("X-CSRF-TOKEN", "_csrf", tokenExpire.getToken());
            }
        }
        return null;
    }
}
