package com.mealmap.preferenceservice.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Информация о предпочтениях пользователя")
public class UserPreferencesDto {
    private Long id;

    private UUID userId;

    private List<ProductPreferenceDto> productPreferences;

    private List<CategoryPreferenceDto> categoryPreferences;
}
