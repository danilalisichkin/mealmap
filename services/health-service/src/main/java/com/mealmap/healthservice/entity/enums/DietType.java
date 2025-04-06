package com.mealmap.healthservice.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum DietType {
    MAINTENANCE(1),
    WEIGHT_LOSS(2),
    WEIGHT_GAIN(3);

    private final Integer id;

    public static DietType fromId(final Integer id) {
        return id == null ? null :
                Arrays.stream(DietType.values())
                        .filter(event -> event.getId().equals(id))
                        .findFirst()
                        .orElse(null);
    }
}
