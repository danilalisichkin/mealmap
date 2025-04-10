package com.mealmap.starters.securitystarter.security.util;

import com.mealmap.starters.securitystarter.security.constant.Prefix;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.text.ParseException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JwtClaimsExtractor {

    private static JWTClaimsSet getClaims(Jwt jwt) {
        try {
            return SignedJWT.parse(jwt.getTokenValue()).getJWTClaimsSet();
        } catch (ParseException e) {
            throw new IllegalArgumentException("Failed to parse JWT", e);
        }
    }

    public static UUID extractUserId(Jwt jwt) {
        if (jwt == null) {
            throw new IllegalArgumentException("JWT cannot be null");
        }
        return UUID.fromString(jwt.getSubject());
    }

    public static Long extractOrganizationId(Jwt jwt) {
        try {
            return getClaims(jwt).getLongClaim("organization_id");
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to extract organization_id from JWT", e);
        }
    }

    public static Boolean extractIsActive(Jwt jwt) {
        try {
            return getClaims(jwt).getBooleanClaim("is_active");
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to extract is_active from JWT", e);
        }
    }

    public static Boolean extractIsBlocked(Jwt jwt) {
        try {
            return getClaims(jwt).getBooleanClaim("is_blocked");
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to extract is_blocked from JWT", e);
        }
    }

    public static Boolean extractIsTemporaryBlocked(Jwt jwt) {
        try {
            return getClaims(jwt).getBooleanClaim("is_temporary_blocked");
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to extract is_temporary_blocked from JWT", e);
        }
    }

    public static String extractClientId(Jwt jwt) {
        try {
            return getClaims(jwt).getStringClaim("client_id");
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to extract client_id from JWT", e);
        }
    }

    public static List<GrantedAuthority> extractUserRoles(Jwt jwt) {
        try {
            Map<String, Object> realmAccess = getClaims(jwt).getJSONObjectClaim("realm_access");

            @SuppressWarnings("unchecked")
            List<String> roles = (List<String>) realmAccess.get("roles");

            if (roles == null) {
                return Collections.emptyList();
            }

            return roles.stream()
                    .map(Prefix.ROLE::concat)
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to extract roles from JWT", e);
        }
    }
}