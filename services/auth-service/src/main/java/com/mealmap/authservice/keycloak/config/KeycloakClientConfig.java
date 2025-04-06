package com.mealmap.authservice.keycloak.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@AllArgsConstructor
@ConfigurationProperties(prefix = "business.keycloak.client")
public class KeycloakClientConfig {
    private final String clientId;
    private final String clientSecret;
}
