package com.mealmap.preferenceservice.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPreferenceDto {
    private Long id;

    private UUID userId;

    private List<ProductPreferenceDto> productPreferences;

    private List<CategoryPreferenceDto> categoryPreferences;
}
