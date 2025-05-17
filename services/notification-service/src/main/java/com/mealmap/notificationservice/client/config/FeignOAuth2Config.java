package com.mealmap.notificationservice.client.config;

import com.mealmap.starters.securitystarter.security.oauth2.service.TokenProvider;
import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;

@RequiredArgsConstructor
public class FeignOAuth2Config {

    private final TokenProvider tokenProvider;

    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor() {
        return requestTemplate -> {
            String accessToken = tokenProvider.getAccessToken();
            requestTemplate.header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);
        };
    }
}