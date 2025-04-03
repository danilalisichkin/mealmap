package com.mealmap.telegrambot.service.impl;

import com.mealmap.telegrambot.config.TgBotProperties;
import com.mealmap.telegrambot.service.TgLinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.UUID;

import static com.mealmap.telegrambot.core.constant.LinkTemplates.START_USER_LINK;

@Service
@RequiredArgsConstructor
public class TgLinkServiceImpl implements TgLinkService {
    private final TgBotProperties tgBotProperties;

    @Override
    public String generateStartLinkForUser(UUID userId) {
        String encodedUserId = Base64.getUrlEncoder().encodeToString(userId.toString().getBytes());

        return START_USER_LINK.formatted(tgBotProperties.getUsername(), encodedUserId);
    }
}
