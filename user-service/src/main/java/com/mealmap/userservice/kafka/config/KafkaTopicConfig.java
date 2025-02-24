package com.mealmap.userservice.kafka.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@AllArgsConstructor
@ConfigurationProperties(prefix = "business.kafka.topics")
public class KafkaTopicConfig {
    private final Topic userUpdate;
    private final Topic userRoleUpdate;
    private final Topic userStatusUpdate;

    @Getter
    @AllArgsConstructor
    public static class Topic {
        private final String name;
        private final int partitions;
        private final short replicas;
    }
}
