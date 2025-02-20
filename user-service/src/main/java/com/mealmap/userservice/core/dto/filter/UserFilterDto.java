package com.mealmap.userservice.core.dto.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserFilterDto {
    private Boolean isActive;

    private Boolean isBlocked;

    private Boolean isTemporaryBlocked;
}
