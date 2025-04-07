package com.mealmap.starters.securitystarter.security.service;

import org.springframework.security.core.Authentication;
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
            return extractIsActive(getTokenValue(authentication));
        }
        return false;
    }

    public boolean isBlocked(Authentication authentication) {
        if (authentication instanceof JwtAuthenticationToken) {
            return extractIsBlocked(getTokenValue(authentication));
        }
        return false;
    }

    public boolean isTemporaryBlocked(Authentication authentication) {
        if (authentication instanceof JwtAuthenticationToken) {
            return extractIsTemporaryBlocked(getTokenValue(authentication));
        }
        return false;
    }

    public boolean isOrganizationMember(Authentication authentication, Long organizationId) {
        if (authentication instanceof JwtAuthenticationToken) {
            return organizationId.equals(extractOrganizationId(getTokenValue(authentication)));
        }
        return false;
    }

    public boolean hasUserId(Authentication authentication, UUID userId) {
        if (authentication instanceof JwtAuthenticationToken) {
            return userId.equals(extractUserId(getTokenValue(authentication)));
        }
        return false;
    }

    private String getTokenValue(Authentication authentication) {
        return ((JwtAuthenticationToken) authentication).getToken().getTokenValue();
    }
}