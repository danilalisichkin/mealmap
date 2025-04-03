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

@Component
public class EchoCommandHandler extends BaseCommandHandler {
    @Autowired
    protected EchoCommandHandler(TelegramClient tgClient) {
        super(tgClient);
    }

    @Override
    public void handle(Update update) {
        Message message = update.getMessage();
        Long chatId = update.getMessage().getChatId();

        try {
            SendMessage response = SendMessage.builder()
                    .chatId(chatId)
                    .text(message.getText())
                    .build();

            tgClient.execute(response);
        } catch (TelegramApiException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public CommandType getSupportedCommandType() {
        return CommandType.ECHO;
    }
}
