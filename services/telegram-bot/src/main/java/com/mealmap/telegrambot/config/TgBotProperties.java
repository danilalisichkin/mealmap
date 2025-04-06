package com.mealmap.telegrambot.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@AllArgsConstructor
@ConfigurationProperties(prefix = "telegram.bot")
public class TgBotProperties {
    private final String username;
    private final String token;
}
