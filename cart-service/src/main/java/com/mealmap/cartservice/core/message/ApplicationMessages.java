package com.mealmap.cartservice.core.message;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ApplicationMessages {
    public static final String CART_NOT_FOUND = "корзина %s не найдена";
    public static final String CART_ITEM_NOT_FOUND = "позиция %s заказа %s не найдена";
    public static final String CART_IS_FULL = "корзина уже содержит максимально возможное количество позиций";
    public static final String CART_ITEM_IS_FULL = "позиция корзины уже содержит максимально возможное количество товаров";
}
