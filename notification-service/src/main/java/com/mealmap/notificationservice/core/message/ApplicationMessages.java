package com.mealmap.notificationservice.core.message;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ApplicationMessages {
    public static final String USER_CONTACTS_NOT_FOUND = "контакты пользователя %s не найдены";
    public static final String USER_TELEGRAM_CHAT_NOT_SET = "Telegram-чат с пользователем %s не задан";
    public static final String USER_EMAIL_NOT_SET = "email пользователя %s не задан";
    public static final String USER_PHONE_NOT_SET = "номер телефона пользователя %s не задан";
    public static final String NOTIFICATION_METHOD_IS_DISABLED
            = "запрашиваемый метод уведомления в данный момент отключен";
}
