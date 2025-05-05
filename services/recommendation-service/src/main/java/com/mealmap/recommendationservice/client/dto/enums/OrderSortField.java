package com.mealmap.recommendationservice.client.dto.enums;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public enum OrderSortField {
    ORDERED_AT("orderedAt");

    private final String value;
}
