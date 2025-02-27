package com.mealmap.cartservice.core.message;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ApplicationMessages {
    public static final String CART_NOT_FOUND = "корзина %s не найдена";
    public static final String CART_ITEM_NOT_FOUND = "позиция %s заказа %s не найдена";
}
