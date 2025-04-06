package com.mealmap.authservice.keycloak.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class KeycloakErrorMessages {
    public static final String USER_WITH_SAME_EMAIL_EXISTS = "User exists with same email";
    public static final String USER_WITH_SAME_USERNAME_EXISTS = "User exists with same username";
    public static final String INVALID_REFRESH_TOKEN = "Invalid refresh token";
    public static final String REFRESH_TOKEN_EXPIRED = "Token is not active";
}
