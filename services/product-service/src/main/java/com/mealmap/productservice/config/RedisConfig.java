package com.mealmap.productservice.config;

import io.lettuce.core.resource.ClientResources;
import io.micrometer.observation.ObservationRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.observability.MicrometerTracingAdapter;

@Configuration
public class RedisConfig {
    @Bean
    public ClientResources lettuceClientResources(ObservationRegistry observationRegistry) {
        return ClientResources.builder()
                .tracing(new MicrometerTracingAdapter(observationRegistry, "product-redis-server"))
                .build();
    }
}
