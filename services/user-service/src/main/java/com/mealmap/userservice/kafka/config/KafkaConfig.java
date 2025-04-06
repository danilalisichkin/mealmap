package com.mealmap.userservice.kafka.config;

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
    public NewTopic userUpdateTopic() {
        var userUpdateTopic = kafkaTopicConfig.getUserUpdate();

        return new NewTopic(
                userUpdateTopic.getName(),
                userUpdateTopic.getPartitions(),
                userUpdateTopic.getReplicas());
    }

    @Bean
    public NewTopic userRoleUpdateTopic() {
        var userRoleUpdateTopic = kafkaTopicConfig.getUserRoleUpdate();

        return new NewTopic(
                userRoleUpdateTopic.getName(),
                userRoleUpdateTopic.getPartitions(),
                userRoleUpdateTopic.getReplicas());
    }

    @Bean
    public NewTopic userStatusUpdateTopic() {
        var userStatusUpdateTopic = kafkaTopicConfig.getUserStatusUpdate();

        return new NewTopic(
                userStatusUpdateTopic.getName(),
                userStatusUpdateTopic.getPartitions(),
                userStatusUpdateTopic.getReplicas());
    }


    @Bean
    public NewTopic cartCreationTopic() {
        var cartCreationTopic = kafkaTopicConfig.getCartCreation();

        return new NewTopic(
                cartCreationTopic.getName(),
                cartCreationTopic.getPartitions(),
                cartCreationTopic.getReplicas());
    }
}
