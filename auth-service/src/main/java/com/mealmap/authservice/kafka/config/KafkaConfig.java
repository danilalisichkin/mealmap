package com.mealmap.authservice.kafka.config;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(KafkaTopicConfig.class)
public class KafkaConfig {
    private final KafkaTopicConfig kafkaTopicConfig;

    @Bean
    public NewTopic userCreationTopic() {
        var userCreationTopic = kafkaTopicConfig.getUserCreation();

        return new NewTopic(
                userCreationTopic.getName(),
                userCreationTopic.getPartitions(),
                userCreationTopic.getReplicas());
    }
}
