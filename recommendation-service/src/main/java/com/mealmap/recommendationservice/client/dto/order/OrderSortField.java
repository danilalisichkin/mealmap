package com.mealmap.recommendationservice.client.dto.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderSortField {
    ORDERED_AT("orderedAt");

    private final String value;
}
