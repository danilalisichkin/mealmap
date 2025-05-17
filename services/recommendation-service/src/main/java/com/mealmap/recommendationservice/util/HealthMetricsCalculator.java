package com.mealmap.recommendationservice.util;

import com.mealmap.recommendationservice.core.model.health.PhysicHealth;
import org.springframework.stereotype.Component;

@Component
public class HealthMetricsCalculator {
    public static final double CM_TO_METERS = 0.01;
    public static final double GRAMS_TO_KG = 0.001;

    public double calculateWeightKg(PhysicHealth health) {
        return health.getWeight() * GRAMS_TO_KG;
    }

    public double calculateBMI(PhysicHealth health) {
        double heightM = health.getHeight() * CM_TO_METERS;
        return health.getWeight() * GRAMS_TO_KG / (heightM * heightM);
    }
}
