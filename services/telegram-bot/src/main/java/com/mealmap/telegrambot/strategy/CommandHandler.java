package com.mealmap.telegrambot.strategy;

import com.mealmap.telegrambot.core.enums.CommandType;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface CommandHandler {
    void handle(Update update);

    CommandType getSupportedCommandType();
}
