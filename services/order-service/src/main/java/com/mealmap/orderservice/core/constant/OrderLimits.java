package com.mealmap.orderservice.core.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class OrderLimits {
    public static final int MAX_ITEMS_PER_ORDER = 20;
    public static final int MAX_PRODUCTS_PER_ITEM = 20;
}
