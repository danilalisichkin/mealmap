package com.mealmap.authservice.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@Schema(description = "Токены доступа, возвращаемые Keycloak")
public class KeycloakAccessTokenDto {
    String accessToken;

    long expiresIn;

    long refreshExpiresIn;

    String refreshToken;

    String tokenType;
}
