package com.learn_security.repositories;

import com.learn_security.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaTokenRepository extends JpaRepository<Token, Integer> {
    Optional<Token> findTokenByIdentifier(String identifier);
}
