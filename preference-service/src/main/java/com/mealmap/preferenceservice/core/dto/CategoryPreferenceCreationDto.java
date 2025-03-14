package com.mealmap.preferenceservice.core.dto;

import com.mealmap.preferenceservice.entity.enums.PreferenceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryPreferenceCreationDto {
    private Long categoryId;

    private PreferenceType preferenceType;
}
