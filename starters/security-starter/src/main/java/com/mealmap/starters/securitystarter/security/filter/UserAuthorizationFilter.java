package com.mealmap.starters.securitystarter.security.filter;

import com.mealmap.starters.securitystarter.security.service.SecurityService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class UserAuthorizationFilter extends OncePerRequestFilter {
    private final SecurityService securityService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof JwtAuthenticationToken jwtAuth) {
            if (!securityService.isActive(jwtAuth)) {
                throw new AccessDeniedException("User is not active");
            } else if (securityService.isBlocked(jwtAuth)) {
                throw new AccessDeniedException("User is blocked");
            } else if (securityService.isTemporaryBlocked(jwtAuth)) {
                throw new AccessDeniedException("User is temporary blocked");
            }
        }

        filterChain.doFilter(request, response);
    }
}
