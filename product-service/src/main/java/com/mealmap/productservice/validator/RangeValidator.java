package com.mealmap.productservice.validator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RangeValidator {
    public static boolean isValidRange(Integer min, Integer max) {
        return min == null || max == null || min <= max;
    }
}
