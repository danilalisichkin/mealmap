package com.mealmap.starters.securitystarter.security.web.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.mealmap.starters.securitystarter.security.common.util.SecurityUtils.isActive;
import static com.mealmap.starters.securitystarter.security.common.util.SecurityUtils.isApplicationService;
import static com.mealmap.starters.securitystarter.security.common.util.SecurityUtils.isBlocked;
import static com.mealmap.starters.securitystarter.security.common.util.SecurityUtils.isTemporaryBlocked;

@Component
public class UserAuthorizationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && !isApplicationService(authentication)) {
            if (!isActive(authentication)) {
                throw new AccessDeniedException("User is not active");
            } else if (isBlocked(authentication)) {
                throw new AccessDeniedException("User is blocked");
            } else if (isTemporaryBlocked(authentication)) {
                throw new AccessDeniedException("User is temporary blocked");
            }
        }

        filterChain.doFilter(request, response);
    }
}
