package com.mealmap.telegrambot.strategy.impl;

import com.mealmap.telegrambot.core.enums.CommandType;
import com.mealmap.telegrambot.exception.InternalErrorException;
import com.mealmap.telegrambot.strategy.BaseCommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.message.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.util.Base64;
import java.util.UUID;

import static com.mealmap.telegrambot.core.message.ApplicationMessages.MUST_CONNECT_PROFILE;
import static com.mealmap.telegrambot.core.message.ApplicationMessages.USER_LINKED;

@Component
public class StartCommandHandler extends BaseCommandHandler {
    @Autowired
    protected StartCommandHandler(TelegramClient tgClient) {
        super(tgClient);
    }

    @Override
    public void handle(Update update) {
        Message message = update.getMessage();
        Long chatId = update.getMessage().getChatId();

        String[] parts = message.getText().split(" ");

        if (parts.length > 1) {
            try {
                String payload = parts[1];
                String decodedUserId = new String(Base64.getUrlDecoder().decode(payload));

                UUID userId = UUID.fromString(decodedUserId);
                // TODO: send kafka event to update user's contacts: tgChatId

                SendMessage response = SendMessage.builder()
                        .chatId(chatId)
                        .text(USER_LINKED.formatted(userId.toString()))
                        .build();

                tgClient.execute(response);

            } catch (TelegramApiException e) {
                throw new InternalErrorException(e);
            }
        } else {
            try {
                SendMessage response = SendMessage.builder()
                        .chatId(chatId)
                        .text(MUST_CONNECT_PROFILE)
                        .build();

                tgClient.execute(response);
            } catch (TelegramApiException e) {
                throw new InternalErrorException(e);
            }
        }
    }

    @Override
    public CommandType getSupportedCommandType() {
        return CommandType.START;
    }
}
