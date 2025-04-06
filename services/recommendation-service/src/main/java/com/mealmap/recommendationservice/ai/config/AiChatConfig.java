package com.mealmap.recommendationservice.ai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class AiChatConfig {
    @Value("classpath:ai/system-prompt-v1.st")
    private Resource systemPrompt;

    @Bean
    public ChatClient chatClient(ChatClient.Builder builder) {
        return builder
                .defaultSystem(systemPrompt)
                .build();
    }
}
