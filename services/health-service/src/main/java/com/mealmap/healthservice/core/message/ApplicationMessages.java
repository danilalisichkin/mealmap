package com.mealmap.healthservice.core.message;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ApplicationMessages {
    public static final String USER_PHYSICAL_HEALTH_NOT_FOUND
            = "информация о физическом здоровьи пользователя %s не найдена";
    public static final String USER_PHYSICAL_HEALTH_ALREADY_EXISTS
            = "информация о физическом здоровьи пользователя %s уже существует";
    public static final String USER_DIET_NOT_FOUND
            = "информация о диете пользователя %s не найдена";
    public static final String USER_DIET_ALREADY_EXISTS
            = "информация о диете пользователя %s уже существует";
    public static final String USER_ALLERGEN_NOT_FOUND
            = "информации об аллергии пользователя не существует";
    public static final String USER_ALLERGEN_ALREADY_EXISTS
            = "информация об аллергии пользователя уже существует";
}
