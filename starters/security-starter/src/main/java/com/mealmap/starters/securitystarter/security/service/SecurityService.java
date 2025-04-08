package com.mealmap.starters.securitystarter.security.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.mealmap.starters.securitystarter.security.util.JwtClaimsExtractor.extractIsActive;
import static com.mealmap.starters.securitystarter.security.util.JwtClaimsExtractor.extractIsBlocked;
import static com.mealmap.starters.securitystarter.security.util.JwtClaimsExtractor.extractIsTemporaryBlocked;
import static com.mealmap.starters.securitystarter.security.util.JwtClaimsExtractor.extractOrganizationId;
import static com.mealmap.starters.securitystarter.security.util.JwtClaimsExtractor.extractUserId;

@Service
public class SecurityService {
    public boolean isActive(Authentication authentication) {
        if (authentication instanceof JwtAuthenticationToken) {
            return extractIsActive(getJwt(authentication));
        }
        return false;
    }

    public boolean isBlocked(Authentication authentication) {
        if (authentication instanceof JwtAuthenticationToken) {
            return extractIsBlocked(getJwt(authentication));
        }
        return false;
    }

    public boolean isTemporaryBlocked(Authentication authentication) {
        if (authentication instanceof JwtAuthenticationToken) {
            return extractIsTemporaryBlocked(getJwt(authentication));
        }
        return false;
    }

    public boolean isOrganizationMember(Authentication authentication, Long organizationId) {
        if (authentication instanceof JwtAuthenticationToken) {
            return organizationId.equals(extractOrganizationId(getJwt(authentication)));
        }
        return false;
    }

    public boolean hasUserId(Authentication authentication, UUID userId) {
        if (authentication instanceof JwtAuthenticationToken) {
            return userId.equals(extractUserId(getJwt(authentication)));
        }
        return false;
    }

    private Jwt getJwt(Authentication authentication) {
        return ((JwtAuthenticationToken) authentication).getToken();
    }
}