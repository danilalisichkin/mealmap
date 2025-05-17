package com.mealmap.starters.securitystarter.security.web.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mealmap.starters.securitystarter.security.web.properties.WebSecurityProperties;
import com.mealmap.starters.securitystarter.security.common.util.JwtClaimsExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {
    private final ObjectMapper objectMapper;

    private final OAuth2ResourceServerProperties oAuth2ResourceServerProperties;

    private final WebSecurityProperties webSecurityProperties;

    public WebSecurityConfig(
            ObjectMapper objectMapper,
            OAuth2ResourceServerProperties oAuth2ResourceServerProperties,
            WebSecurityProperties webSecurityProperties) {

        this.objectMapper = objectMapper;
        this.oAuth2ResourceServerProperties = oAuth2ResourceServerProperties;
        this.webSecurityProperties = webSecurityProperties;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .sessionManagement(c -> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(Customizer.withDefaults())
                .csrf(CsrfConfigurer::disable)
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .jwtAuthenticationConverter(jwtAuthenticationConverter()))
                        .accessDeniedHandler(accessDeniedHandler())
                        .authenticationEntryPoint(authenticationEntryPoint()))
                .authorizeHttpRequests(r -> r
                        .anyRequest()
                        .authenticated())
                .exceptionHandling(h -> h
                        .accessDeniedHandler(accessDeniedHandler())
                        .authenticationEntryPoint(authenticationEntryPoint()))
                .build();
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(webSecurityProperties.getCors().getAllowedOrigins());
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withJwkSetUri(
                oAuth2ResourceServerProperties.getJwt().getJwkSetUri()
        ).build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(JwtClaimsExtractor::extractUserRoles);
        converter.setPrincipalClaimName("sub");

        return converter;
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            ProblemDetail detail;

            if (accessDeniedException instanceof AuthorizationDeniedException) {
                detail = ProblemDetail.forStatusAndDetail(
                        HttpStatus.FORBIDDEN,
                        "Authorization failed: " + accessDeniedException.getMessage()
                );
                detail.setProperty("errorCode", "AUTHZ_DENIED");
            } else {
                detail = ProblemDetail.forStatusAndDetail(
                        HttpStatus.FORBIDDEN,
                        "Access denied: " + accessDeniedException.getMessage()
                );
                detail.setProperty("errorCode", "ACCESS_DENIED");
            }

            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.setContentType(MediaType.APPLICATION_PROBLEM_JSON_VALUE);
            response.getWriter().write(objectMapper.writeValueAsString(detail));
        };
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, authException) -> {
            ProblemDetail detail = ProblemDetail.forStatusAndDetail(
                    HttpStatus.UNAUTHORIZED,
                    "Authentication failed: " + authException.getMessage()
            );
            detail.setProperty("errorCode", "AUTH_REQUIRED");

            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(MediaType.APPLICATION_PROBLEM_JSON_VALUE);
            response.getWriter().write(objectMapper.writeValueAsString(detail));
        };
    }
}
