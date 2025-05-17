package com.mealmap.recommendationservice.util;

import com.mealmap.recommendationservice.core.model.health.Diet;
import com.mealmap.recommendationservice.core.model.health.PhysicHealth;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.mealmap.recommendationservice.util.HealthMetricsCalculator.GRAMS_TO_KG;

@Component
@RequiredArgsConstructor
public class HealthInfoComposer {
    private static final String HEALTH_INFO_TEMPLATE = "- Мой вес: %s кг, ИМТ: %s , диета: %s";
    private static final String NO_HEALTH_INFO = "- Информации о здоровье нет";
    private static final String NO_DIET = "диеты нет";

    private final HealthMetricsCalculator metricsCalculator;

    public String composeHealthInfo(PhysicHealth physicHealth, Diet diet) {
        if (physicHealth == null) return NO_HEALTH_INFO;

        return HEALTH_INFO_TEMPLATE.formatted(
                metricsCalculator.calculateWeightKg(physicHealth),
                metricsCalculator.calculateBMI(physicHealth),
                defineDietGoal(diet)
        );
    }

    private String defineDietGoal(Diet diet) {
        if (diet == null) return NO_DIET;
        return switch (diet.getType()) {
            case MAINTENANCE -> "Поддержание текущего веса";
            case WEIGHT_LOSS -> "Снижение веса до %.1f кг".formatted(diet.getGoalWeight() * GRAMS_TO_KG);
            case WEIGHT_GAIN -> "Набор веса до %.1f кг".formatted(diet.getGoalWeight() * GRAMS_TO_KG);
        };
    }
}
