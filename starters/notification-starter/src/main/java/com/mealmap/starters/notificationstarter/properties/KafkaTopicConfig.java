package com.mealmap.starters.notificationstarter.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "notifications.kafka-topic")
public class KafkaTopicConfig {
    private String name = "notification.creation.v1";
    private int partitions = 1;
    private short replicas = 1;
}
