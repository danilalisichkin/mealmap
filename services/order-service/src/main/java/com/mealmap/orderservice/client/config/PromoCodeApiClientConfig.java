package com.mealmap.orderservice.client.config;

import com.mealmap.orderservice.client.decoder.DefaultErrorDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;

public class PromoCodeApiClientConfig {
    @Bean
    public ErrorDecoder errorDecoder() {
        return new DefaultErrorDecoder();
    }
}
