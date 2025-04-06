package com.mealmap.preferenceservice.core.dto;

import com.mealmap.preferenceservice.entity.enums.PreferenceType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryPreferenceCreationDto {
    @NotNull
    private Long categoryId;

    @NotNull
    private PreferenceType preferenceType;
}
