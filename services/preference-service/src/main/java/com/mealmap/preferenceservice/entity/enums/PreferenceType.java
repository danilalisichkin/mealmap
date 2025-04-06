package com.mealmap.preferenceservice.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum PreferenceType {
    LIKED(1),
    DISLIKED(2);

    private final Integer id;

    public static PreferenceType fromId(final Integer id) {
        return id == null ? null :
                Arrays.stream(PreferenceType.values())
                        .filter(event -> event.getId().equals(id))
                        .findFirst()
                        .orElse(null);
    }
}
