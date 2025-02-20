package com.mealmap.userservice.core.enums.sort;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StatusHistorySortField {
    ID("id"),
    EVENT_AT("eventAt");

    private final String value;
}
