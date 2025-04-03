package com.mealmap.telegrambot.strategy.manager;

import com.mealmap.telegrambot.core.enums.CommandType;
import com.mealmap.telegrambot.strategy.CommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CommandManager {
    private final Map<CommandType, CommandHandler> handlers;

    @Autowired
    public CommandManager(List<CommandHandler> handlerList) {
        this.handlers = handlerList.stream()
                .collect(Collectors.toMap(
                        CommandHandler::getSupportedCommandType,
                        handler -> handler));
    }

    public void processCommand(Update update) {
        String messageText = update.getMessage().getText();
        String startPrefix = messageText.split(" ")[0];
        CommandType suitableCommand = CommandType.fromPrefix(startPrefix);

        handlers.get(suitableCommand).handle(update);
    }
}
