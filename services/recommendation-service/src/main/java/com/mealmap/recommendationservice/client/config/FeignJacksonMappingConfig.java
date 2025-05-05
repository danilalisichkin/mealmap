package com.mealmap.recommendationservice.client.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mealmap.starters.securitystarter.security.service.TokenProvider;
import feign.codec.Decoder;
import feign.jackson.JacksonDecoder;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.context.annotation.Bean;

@RequiredArgsConstructor
public class FeignJacksonMappingConfig {

    private final TokenProvider tokenProvider;

    @Bean
    public Decoder customFeignDecoder() {
        ObjectMapper customMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return new ResponseEntityDecoder(new JacksonDecoder(customMapper));
    }
}