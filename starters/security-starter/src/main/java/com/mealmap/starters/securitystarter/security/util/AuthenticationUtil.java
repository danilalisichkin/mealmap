package com.mealmap.starters.securitystarter.security.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Optional;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AuthenticationUtil {
    public static UUID getUserId() {
        return secureGetJwt(getAuthentication()).map(JwtClaimsExtractor::extractUserId).orElse(null);
    }

    private static Optional<Jwt> secureGetJwt(Authentication authentication) {
        if (authentication instanceof JwtAuthenticationToken) {
            return Optional.of(getJwt(authentication));
        } else {
            return Optional.empty();
        }
    }

    private static Jwt getJwt(Authentication authentication) {
        return ((JwtAuthenticationToken) authentication).getToken();
    }

    private static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
