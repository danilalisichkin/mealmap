package com.mealmap.telegrambot.service;

import java.util.UUID;

public interface TgLinkService {
    String generateStartLinkForUser(UUID userId);
}
