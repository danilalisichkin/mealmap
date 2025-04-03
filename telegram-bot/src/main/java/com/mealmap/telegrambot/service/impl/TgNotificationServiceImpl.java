package com.mealmap.telegrambot.service.impl;

import com.mealmap.telegrambot.exception.InternalErrorException;
import com.mealmap.telegrambot.service.TgNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

@Service
@RequiredArgsConstructor
public class TgNotificationServiceImpl implements TgNotificationService {
    private final TelegramClient tgClient;

    public void sendMessageToChat(Long chatId, String messageText) {
        SendMessage message = SendMessage.builder()
                .chatId(chatId.toString())
                .text(messageText)
                .build();

        try {
            tgClient.execute(message);
        } catch (TelegramApiException e) {
            throw new InternalErrorException(e);
        }
    }
}