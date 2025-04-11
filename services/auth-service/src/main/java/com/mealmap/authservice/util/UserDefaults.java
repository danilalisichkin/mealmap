package com.mealmap.authservice.util;

import com.mealmap.authservice.core.dto.UserStatus;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserDefaults {
    public static UserStatus status() {
        return new UserStatus(
                true,
                false,
                false);
    }

    public static UserStatus supplierStatus() {
        return new UserStatus(
                false,
                false,
                false);
    }

    public static UserStatus customerStatus() {
        return new UserStatus(
                true,
                false,
                false);
    }
}
