package com.mealmap.authservice.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@Schema(description = "Статус пользователя")
public class UserStatus {
    private Boolean isActive;
    private Boolean isBlocked;
    private Boolean isTemporaryBlocked;
}
