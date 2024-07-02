package com.learn_security.services;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class NameService {

    @PreAuthorize("hasAuthority('WRITE')")
    public String getName() {
        return "Fantastico";
    }
}
