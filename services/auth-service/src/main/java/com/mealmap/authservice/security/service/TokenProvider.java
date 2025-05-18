package com.mealmap.authservice.security.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Component
@RequiredArgsConstructor
public class TokenProvider {
    private final AtomicReference<String> serviceToken = new AtomicReference<>();

    private final Keycloak keycloakClient;

    public String getServiceToken() {
        if (serviceToken.get() == null) {
            refreshToken();
        }
        return serviceToken.get();
    }

    @Scheduled(fixedRateString = "${keycloak.token-refresh-interval}000")
    public void refreshToken() {
        try {
            String newToken = keycloakClient.tokenManager().getAccessTokenString();
            serviceToken.set(newToken);
            log.debug("Successfully refreshed Keycloak service token");
        } catch (Exception e) {
            log.error("Failed to refresh Keycloak service token", e);
        }
    }
}
