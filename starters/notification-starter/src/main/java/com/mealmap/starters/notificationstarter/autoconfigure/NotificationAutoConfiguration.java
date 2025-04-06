package com.mealmap.starters.notificationstarter.autoconfigure;

import com.mealmap.starters.notificationstarter.client.NotificationClient;
import com.mealmap.starters.notificationstarter.dto.Notification;
import com.mealmap.starters.notificationstarter.kafka.KafkaConfiguration;
import com.mealmap.starters.notificationstarter.kafka.producer.NotificationCreationProducer;
import com.mealmap.starters.notificationstarter.properties.KafkaTopicConfig;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.core.KafkaTemplate;

@AutoConfiguration
@ConditionalOnClass(name = "org.springframework.kafka.core.KafkaTemplate")
@EnableConfigurationProperties(KafkaTopicConfig.class)
@Import({KafkaConfiguration.class})
public class NotificationAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public NotificationCreationProducer notificationCreationProducer(
            KafkaTopicConfig topicConfig,
            KafkaTemplate<String, Notification> kafkaTemplate) {
        return new NotificationCreationProducer(topicConfig, kafkaTemplate);
    }

    @Bean
    @ConditionalOnMissingBean
    public NotificationClient notificationClient(NotificationCreationProducer producer) {
        return new NotificationClient(producer);
    }
}
