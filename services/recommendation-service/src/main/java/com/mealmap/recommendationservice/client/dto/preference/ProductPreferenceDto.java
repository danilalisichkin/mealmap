package com.mealmap.recommendationservice.client.dto.preference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductPreferenceDto {
    private Long id;

    private Long productId;

    private String preferenceType;
}
