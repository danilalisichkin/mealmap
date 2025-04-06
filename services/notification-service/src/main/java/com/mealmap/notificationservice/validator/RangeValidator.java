package com.mealmap.notificationservice.validator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RangeValidator {
    public static boolean isValidRange(LocalDateTime min, LocalDateTime max) {
        return min == null || max == null || min.isBefore(max);
    }
}
