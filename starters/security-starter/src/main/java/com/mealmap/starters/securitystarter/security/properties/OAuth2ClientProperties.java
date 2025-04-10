package com.mealmap.starters.securitystarter.security.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "security.oauth2.client")
public class OAuth2ClientProperties {
    private String clientId;
    private String clientSecret;
    private String tokenUri = "http://localhost:8080/realms/mealmap-realm/protocol/openid-connect/token";
}
