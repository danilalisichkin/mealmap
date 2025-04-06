package com.mealmap.telegrambot.bot;

import com.mealmap.starters.exceptionstarter.exception.InternalErrorException;
import com.mealmap.telegrambot.config.TgBotProperties;
import com.mealmap.telegrambot.strategy.manager.CommandManager;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.longpolling.BotSession;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@Component
@RequiredArgsConstructor
public class TgBot implements LongPollingSingleThreadUpdateConsumer {
    private final TgBotProperties tgBotProperties;

    private final CommandManager commandManager;

    private BotSession botSession;

    @PostConstruct
    public void start() {
        try {
            TelegramBotsLongPollingApplication botsApplication = new TelegramBotsLongPollingApplication();
            this.botSession = botsApplication.registerBot(tgBotProperties.getToken(), this);
        } catch (TelegramApiException e) {
            throw new InternalErrorException(e);
        }
    }

    @PreDestroy
    public void stop() {
        if (botSession != null) {
            botSession.stop();
        }
    }

    @Override
    public void consume(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            long chatId = update.getMessage().getChatId();

            log.info("Chat ID: {}", chatId);
            commandManager.processCommand(update);
        }
    }
}