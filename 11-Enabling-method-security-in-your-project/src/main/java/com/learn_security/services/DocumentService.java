package com.learn_security.services;

import com.learn_security.models.Document;
import com.learn_security.repositories.DocumentRepository;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;

@Service
public class DocumentService {
    private final DocumentRepository documentRepository;

    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @PostAuthorize
            ("hasPermission(returnObject, 'ROLE_ADMIN')")
    public Document getDocument(String code) {
        return documentRepository.findDocument(code);
    }
}
