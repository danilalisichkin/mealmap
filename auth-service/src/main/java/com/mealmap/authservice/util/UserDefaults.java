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

    public static UserStatus clientHeadStatus() {
        return new UserStatus(
                false,
                false,
                false);
    }

    public static UserStatus supplierAdminStatus() {
        return new UserStatus(
                false,
                false,
                false);
    }
}
