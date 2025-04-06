package com.mealmap.productservice.core.enums.sort;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CategorySortField {
    ID("id"),
    NAME("name");

    private final String value;
}
