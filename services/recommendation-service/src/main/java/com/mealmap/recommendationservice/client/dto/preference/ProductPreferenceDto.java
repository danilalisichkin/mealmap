package com.mealmap.recommendationservice.client.dto.preference;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductPreferenceDto {
    private Long id;

    private Long productId;

    private String preferenceType;
}
