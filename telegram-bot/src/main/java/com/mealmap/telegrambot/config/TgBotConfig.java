package com.mealmap.telegrambot.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.meta.generics.TelegramClient;

@Configuration
@RequiredArgsConstructor
public class TgBotConfig {
    private final TgBotProperties tgBotProperties;

    @Bean
    public TelegramClient telegramClient() {
        return new OkHttpTelegramClient(tgBotProperties.getToken());
    }
}
