package com.mealmap.authservice.keycloak.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@AllArgsConstructor
@ConfigurationProperties(prefix = "business.keycloak")
public class KeycloakServerConfig {
    private final String serverUrl;
    private final String realm;
    private final String grantType;
}
