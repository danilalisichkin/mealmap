package com.mealmap.preferenceservice.core.dto;

import com.mealmap.preferenceservice.entity.enums.PreferenceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryPreferenceDto {
    private Long id;

    private Long categoryId;

    private PreferenceType preferenceType;
}
