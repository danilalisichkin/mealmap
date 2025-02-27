package com.mealmap.cartservice.core.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CartLimits {
    public static final int MAX_ITEMS_PER_CART = 20;
    public static final int MAX_PRODUCTS_PER_ITEM = 20;
}
