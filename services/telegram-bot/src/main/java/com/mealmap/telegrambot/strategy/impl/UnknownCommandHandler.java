package com.mealmap.telegrambot.strategy.impl;

import com.mealmap.telegrambot.core.enums.CommandType;
import com.mealmap.telegrambot.exception.InternalErrorException;
import com.mealmap.telegrambot.strategy.BaseCommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import static com.mealmap.telegrambot.core.message.ApplicationMessages.UNKNOWN_COMMAND;

@Component
public class UnknownCommandHandler extends BaseCommandHandler {
    @Autowired
    protected UnknownCommandHandler(TelegramClient tgClient) {
        super(tgClient);
    }

    @Override
    public void handle(Update update) {
        Long chatId = update.getMessage().getChatId();

        try {
            SendMessage response = SendMessage.builder()
                    .chatId(chatId)
                    .text(UNKNOWN_COMMAND)
                    .build();

            tgClient.execute(response);
        } catch (TelegramApiException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public CommandType getSupportedCommandType() {
        return null;
    }
}
