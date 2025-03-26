package com.mealmap.recommendationservice.ai.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PromptTemplates {
    public static final String USER_RECOMMENDATIONS = "menu.json:\n%s user_info.json:\n%s";
}
