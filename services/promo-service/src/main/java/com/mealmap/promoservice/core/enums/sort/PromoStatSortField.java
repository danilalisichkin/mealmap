package com.mealmap.promoservice.core.enums.sort;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PromoStatSortField {
    ID("id"),
    PROMO_CODE("promoCode"),
    USER_ID("userId"),
    CREATED_AT("createdAt");

    private final String value;
}
