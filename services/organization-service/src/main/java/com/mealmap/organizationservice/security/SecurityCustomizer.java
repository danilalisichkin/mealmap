package com.mealmap.organizationservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;

import static org.springframework.http.HttpMethod.GET;

@Configuration
public class SecurityCustomizer {

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web
                .ignoring()
                .requestMatchers(GET, "/api/v1/organizations/suppliers")
                .requestMatchers(GET, "/api/v1/organizations/suppliers/{id:^[0-9]+$}");
    }
}