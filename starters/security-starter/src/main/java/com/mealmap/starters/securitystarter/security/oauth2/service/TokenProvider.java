package com.mealmap.starters.securitystarter.security.oauth2.service;

import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.stereotype.Component;

@Component
public class TokenProvider {
    private final String registrationId;

    private final String clientId;

    private final OAuth2AuthorizedClientManager authorizedClientManager;

    public TokenProvider(
            String registrationId,
            String clientId,
            OAuth2AuthorizedClientManager authorizedClientManager) {

        this.registrationId = registrationId;
        this.clientId = clientId;
        this.authorizedClientManager = authorizedClientManager;
    }

    public String getAccessToken() {
        var request = OAuth2AuthorizeRequest.withClientRegistrationId(registrationId)
                .principal(clientId)
                .build();

        var client = authorizedClientManager.authorize(request);
        if (client == null || client.getAccessToken() == null) {
            throw new IllegalStateException("Failed to get access token");
        }
        return client.getAccessToken().getTokenValue();
    }
}
