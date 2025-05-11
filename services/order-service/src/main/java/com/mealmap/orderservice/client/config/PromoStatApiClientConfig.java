package com.mealmap.orderservice.client.config;

import com.mealmap.orderservice.client.decoder.PromoStatApiErrorDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;

public class PromoStatApiClientConfig {
    @Bean
    public ErrorDecoder errorDecoder() {
        return new PromoStatApiErrorDecoder();
    }
}
