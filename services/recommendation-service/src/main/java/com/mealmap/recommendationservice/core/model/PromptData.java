package com.mealmap.recommendationservice.core.model;

public record PromptData(
        String catalogJson,
        String likedProductsJson,
        String likedCategoriesJson,
        String ordersJson,
        String allergies,
        String healthInfo
) {}