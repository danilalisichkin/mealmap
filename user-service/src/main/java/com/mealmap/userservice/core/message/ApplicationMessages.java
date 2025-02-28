package com.mealmap.userservice.core.message;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ApplicationMessages {
    public static final String USER_NOT_FOUND = "пользователь %s не найден";
    public static final String USER_WITH_ID_ALREADY_EXISTS = "пользователь с приведенным id уже существует";
    public static final String USER_WITH_PHONE_ALREADY_EXISTS = "пользователь с приведенным номером телефона уже существует";
    public static final String USER_WITH_EMAIL_ALREADY_EXISTS = "пользователь с приведенным email уже существует";
    public static final String USER_IS_ALREADY_ACTIVE = "пользователь уже активен";
    public static final String USER_IS_ALREADY_DEACTIVATED = "пользователь уже деактивирован";
    public static final String USER_IS_ALREADY_TEMPORARY_BLOCKED = "пользователь уже временно заблокирован";
    public static final String USER_IS_ALREADY_BLOCKED = "пользователь уже заблокирован";
    public static final String USER_IS_NOT_BLOCKED = "пользователь на данный момент не заблокирован";
}
