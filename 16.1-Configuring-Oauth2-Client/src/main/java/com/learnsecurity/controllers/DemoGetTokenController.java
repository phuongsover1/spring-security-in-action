package com.learnsecurity.controllers;

import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoGetTokenController {

    private final OAuth2AuthorizedClientManager clientManager;

    public DemoGetTokenController(OAuth2AuthorizedClientManager clientManager) {
        this.clientManager = clientManager;
    }

    @GetMapping("/token")
    public String token() {
        OAuth2AuthorizeRequest request = OAuth2AuthorizeRequest
                .withClientRegistrationId("1")
                .principal("client")
                .build();

        var client = clientManager.authorize(request);

        return client.getAccessToken().getTokenValue();
    }
}
