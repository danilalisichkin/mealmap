package com.mealmap.authservice.core.message;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ApplicationMessages {
    public static final String INVALID_REFRESH_TOKEN = "некорекктный refresh-токен";
    public static final String REFRESH_TOKEN_EXPIRED = "срок действия refresh-токена истек";
    public static final String REGISTER_USER_WITH_ROLE_PROHIBITED = "регистрация пользователя с данной ролью запрещена";
    public static final String REGISTER_USER_WITH_USED_EMAIL = "email уже используется другим пользователем";
    public static final String REGISTER_USER_WITH_USED_PHONE = "номер телефона уже используется другим пользователем";
    public static final String USER_WITH_ID_NOT_FOUND = "пользователь с id=%s не найден";
    public static final String USER_NOT_FOUND = "пользователь не найден";
    public static final String USER_ROLE_NOT_FOUND = "роль не найдена";
    public static final String WRONG_LOGIN_OR_PASSWORD = "неправильный логин или пароль";
    public static final String EMAIL_NOT_EXIST = "email не существует или не доступен";
}
