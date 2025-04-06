package com.mealmap.promoservice.core.enums.sort;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PromoCodeSortField {
    VALUE("value"),
    DISCOUNT("discountPercentage"),
    START_DATE("startDate"),
    END_DATE("endDate"),
    LIMITS("limits");

    private final String value;
}
