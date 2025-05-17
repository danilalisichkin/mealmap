package com.mealmap.recommendationservice.client.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import feign.codec.Decoder;
import feign.jackson.JacksonDecoder;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.context.annotation.Bean;

public class FeignJacksonMappingConfig {
    @Bean
    public Decoder customFeignDecoder() {
        ObjectMapper customMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        customMapper.registerModule(new JavaTimeModule());

        return new ResponseEntityDecoder(new JacksonDecoder(customMapper));
    }
}