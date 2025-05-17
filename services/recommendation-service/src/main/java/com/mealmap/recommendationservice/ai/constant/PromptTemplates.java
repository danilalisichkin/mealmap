package com.mealmap.recommendationservice.ai.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PromptTemplates {
    public static final String USER_RECOMMENDATIONS = """
            - Меню продуктов: %s
            - Мои любимые блюда: %s
            - Мои любимые категории блюд: %s
            - Мои последние заказы: %s
            - Мои аллергии: %s.
            %s
            """;
}
