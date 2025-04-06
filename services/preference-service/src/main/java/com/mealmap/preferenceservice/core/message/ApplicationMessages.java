package com.mealmap.preferenceservice.core.message;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ApplicationMessages {
    public static final String PREFERENCES_FOR_USER_NOT_FOUND = "предпочтения пользователя %s не найдены";
    public static final String PRODUCT_PREFERENCE_ALREADY_EXISTS
            = "информация о предпочтении блюда %s уже существует";
    public static final String CATEGORY_PREFERENCE_ALREADY_EXISTS
            = "информация о предпочтении категории %s уже существует";
    public static final String PRODUCT_PREFERENCE_NOT_FOUND
            = "информация о предпочтении блюда c id=%s не найдена";
    public static final String CATEGORY_PREFERENCE_NOT_FOUND
            = "информация о предпочтении категории c id=%s не найдена";
}
