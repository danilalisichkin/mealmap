package com.mealmap.notificationservice.client.config;

import com.mealmap.notificationservice.client.decoder.CustomErrorDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;

public class TelegramBotApiClientConfig {
    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomErrorDecoder();
    }
}
