package com.mealmap.starters.notificationstarter.kafka;

import com.mealmap.starters.notificationstarter.properties.KafkaTopicConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@EnableConfigurationProperties(KafkaTopicConfig.class)
public class KafkaConfiguration {

    @Bean
    public NewTopic notificationCreationTopic(KafkaTopicConfig config) {
        return TopicBuilder.name(config.getName())
                .partitions(config.getPartitions())
                .replicas(config.getReplicas())
                .build();
    }
}
