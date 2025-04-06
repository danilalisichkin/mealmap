package com.mealmap.telegrambot.service;

public interface TgNotificationService {
    void sendMessageToChat(Long chatId, String messageText);
}
