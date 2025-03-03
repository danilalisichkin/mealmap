package com.mealmap.promoservice.core.message;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ApplicationMessages {
    public static final String PROMO_CODE_NOT_FOUND = "промо-код %s не найден";
    public static final String PROMO_CODE_ALREADY_EXISTS = "промо-код %s уже существует";
    public static final String PROMO_CODE_EXPIRED = "срок действия промо-кода истек";
    public static final String PROMO_CODE_LIMIT_REACHED = "лимит применения промо-кода исчерпан";
    public static final String PROMO_CODE_END_DATE_BEFORE_START_DATE
            = "дата окончания действия промо-кода не может быть раньше даты начала его действия";
    public static final String PROMO_STAT_NOT_FOUND = "статистика %s использования промокода не найдена";
    public static final String PROMO_STAT_FOR_USER_ALREADY_EXISTS
            = "статистика использования промокода %s пользователем %s уже существует";
}
