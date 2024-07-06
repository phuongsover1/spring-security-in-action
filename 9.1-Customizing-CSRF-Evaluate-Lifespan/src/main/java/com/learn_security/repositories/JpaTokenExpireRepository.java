package com.learn_security.repositories;

import com.learn_security.models.TokenExpire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaTokenExpireRepository extends JpaRepository<TokenExpire, Integer> {
    Optional<TokenExpire> findTokenExpireByToken(String token);
}
