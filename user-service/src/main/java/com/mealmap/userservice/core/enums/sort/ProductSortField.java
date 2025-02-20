package com.mealmap.userservice.core.enums.sort;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProductSortField {
    ID("id"),
    NAME("name"),
    PRICE("price"),
    WEIGHT("weight"),
    CALORIES("nutrients.calories"),
    PROTEINS("nutrients.proteins"),
    FATS("nutrients.fats"),
    CARBS("nutrients.carbs"),
    FIBERS("nutrients.fibers"),
    SUGARS("nutrients.sugars"),
    NEWNESS("createdAt");

    private final String value;
}
