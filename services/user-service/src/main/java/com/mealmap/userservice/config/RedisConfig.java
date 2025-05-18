package com.mealmap.userservice.config;

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
                .tracing(new MicrometerTracingAdapter(observationRegistry, "user-redis-server"))
                .build();
    }
}
