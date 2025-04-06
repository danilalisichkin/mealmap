package com.mealmap.telegrambot.strategy;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.generics.TelegramClient;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class BaseCommandHandler implements CommandHandler {
    protected final TelegramClient tgClient;
}
