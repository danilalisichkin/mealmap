package com.mealmap.promoservice.validator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RangeValidator {
    public static boolean isValidRange(LocalDate min, LocalDate max) {
        return min == null || max == null || min.isBefore(max);
    }
}
