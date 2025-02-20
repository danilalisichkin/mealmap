package com.mealmap.userservice.core.message;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ApplicationMessages {
    public static final String USER_NOT_FOUND = "пользователь %s не найден";
    public static final String USER_WITH_PHONE_ALREADY_EXISTS = "пользователь с приведенным номером телефона уже существует";
    public static final String USER_WITH_EMAIL_ALREADY_EXISTS = "пользователь с приведенным email уже существует";
}
