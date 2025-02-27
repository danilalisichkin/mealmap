package com.mealmap.orderservice.core.message;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ApplicationMessages {
    public static final String ORDER_NOT_FOUND = "заказ %s не найден";
    public static final String USER_ORDER_NOT_FOUND = "заказ %s пользователя %s не найден";
    public static final String ORDER_NOT_PAID = "заказ не оплачен";
    public static final String CANT_CHANGE_ORDER_STATUS = "нельзя изменить статус заказа с %s на %s";
    public static final String TOO_MANY_ITEMS_IN_ORDER = "заказ содержит слишком много позиций";
    public static final String TOO_MANY_PRODUCTS_IN_ITEM = "заказ содержит слишком много товаров";
    public static final String PRODUCTS_IN_ITEMS_REPEAT = "товары в заказе повторяются";
}
