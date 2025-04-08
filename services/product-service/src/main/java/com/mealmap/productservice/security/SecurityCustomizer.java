package com.mealmap.productservice.security;

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
                .requestMatchers(GET, "/api/v1/categories")
                .requestMatchers(GET, "/api/v1/categories/{id}")
                .requestMatchers(GET, "/api/v1/categories/{id}/tree")
                .requestMatchers(GET, "/api/v1/products")
                .requestMatchers(GET, "/api/v1/products/{id}");
    }
}