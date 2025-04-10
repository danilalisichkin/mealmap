package com.mealmap.starters.securitystarter.security.util;

import io.micrometer.common.util.StringUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Optional;
import java.util.UUID;

import static com.mealmap.starters.securitystarter.security.util.JwtClaimsExtractor.extractClientId;
import static com.mealmap.starters.securitystarter.security.util.JwtClaimsExtractor.extractOrganizationId;
import static com.mealmap.starters.securitystarter.security.util.JwtClaimsExtractor.extractUserId;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SecurityUtils {
    public static boolean isActive(Authentication authentication) {
        return secureGetJwt(authentication).map(JwtClaimsExtractor::extractIsActive).orElse(false);
    }

    public static boolean isBlocked(Authentication authentication) {
        return secureGetJwt(authentication).map(JwtClaimsExtractor::extractIsBlocked).orElse(false);
    }

    public static boolean isTemporaryBlocked(Authentication authentication) {
        return secureGetJwt(authentication).map(JwtClaimsExtractor::extractIsTemporaryBlocked).orElse(false);
    }

    public static boolean isOrganizationMember(Authentication authentication, Long organizationId) {
        return secureGetJwt(authentication).map(jwt -> extractOrganizationId(jwt).equals(organizationId)).orElse(false);
    }

    public static boolean hasUserId(Authentication authentication, UUID userId) {
        return secureGetJwt(authentication).map(jwt -> extractUserId(jwt).equals(userId)).orElse(false);
    }

    public static boolean isApplicationService(Authentication authentication) {
        try {
            return secureGetJwt(authentication)
                    .map(jwt -> StringUtils.isNotBlank(extractClientId(jwt)))
                    .orElse(false);
        } catch (IllegalArgumentException e) {
            return false;
        }
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
}
