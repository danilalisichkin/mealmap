package com.mealmap.authservice.client.config;

import com.mealmap.authservice.client.decoder.CustomErrorDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;

public class OrganizationApiClientConfig {
    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomErrorDecoder();
    }
}
