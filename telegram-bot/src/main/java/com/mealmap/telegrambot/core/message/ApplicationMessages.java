package com.mealmap.telegrambot.core.message;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ApplicationMessages {
    public static final String MUST_CONNECT_PROFILE
            = "для общения со мной необходимо привязать профиль через личный кабинет на сайте";
    public static final String USER_LINKED
            = "ваш профиль успешно привязан";
    public static final String UNKNOWN_COMMAND
            = "неизвестная команда";
}
