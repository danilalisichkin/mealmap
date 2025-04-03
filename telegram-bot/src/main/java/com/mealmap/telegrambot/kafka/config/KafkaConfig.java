package com.mealmap.telegrambot.kafka.config;

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
    public NewTopic userContactsUpdateTgChatTopic() {
        var userUpdateTopic = kafkaTopicConfig.getUserContactsUpdateTgChat();

        return new NewTopic(
                userUpdateTopic.getName(),
                userUpdateTopic.getPartitions(),
                userUpdateTopic.getReplicas());
    }
}
