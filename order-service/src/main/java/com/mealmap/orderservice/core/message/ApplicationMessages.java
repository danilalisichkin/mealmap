package com.mealmap.orderservice.core.message;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ApplicationMessages {
    public static final String ORDER_NOT_FOUND = "заказ %s не найден";
    public static final String USER_ORDER_NOT_FOUND = "заказ %s пользователя %s не найден";
    public static final String ORDER_NOT_PAID = "заказ не оплачен";
    public static final String CANT_CHANGE_ORDER_STATUS = "нельзя изменить статус заказа с %s на %s";
}
