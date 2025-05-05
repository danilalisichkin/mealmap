package com.mealmap.healthservice.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Gender {
    MALE(1),
    FEMALE(2);

    private final Integer id;

    public static Gender fromId(final Integer id) {
        return id == null ? null :
                Arrays.stream(Gender.values())
                        .filter(gender -> gender.getId().equals(id))
                        .findFirst()
                        .orElse(null);
    }
}
