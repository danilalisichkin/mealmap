package com.mealmap.recommendationservice.core.enums.sort;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRecommendationSortField {
    CREATED_AT("createdAt");

    private final String value;
}
