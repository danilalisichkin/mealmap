package com.mealmap.authservice.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class UserStatus {
    private Boolean isActive;
    private Boolean isBlocked;
    private Boolean isTemporaryBlocked;
}
