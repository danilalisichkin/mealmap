package com.mealmap.telegrambot.service;

import java.util.UUID;

public interface TgLinkService {
    String generateWriteLink();

    String generateStartLinkForUser(UUID userId);
}
