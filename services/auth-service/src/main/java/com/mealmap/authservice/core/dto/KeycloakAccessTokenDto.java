package com.mealmap.authservice.core.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class KeycloakAccessTokenDto {
    String accessToken;

    long expiresIn;

    long refreshExpiresIn;

    String refreshToken;

    String tokenType;
}
