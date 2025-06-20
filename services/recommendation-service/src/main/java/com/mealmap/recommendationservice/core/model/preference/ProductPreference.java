package com.mealmap.recommendationservice.core.model.preference;

import com.mealmap.recommendationservice.client.dto.enums.PreferenceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductPreference {
    private Long productId;

    private PreferenceType preferenceType;
}
