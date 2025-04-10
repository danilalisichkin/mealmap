package com.mealmap.recommendationservice.client.dto.preference;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserPreferenceDto {
    private Long id;

    private UUID userId;

    private List<ProductPreferenceDto> productPreferences;

    private List<CategoryPreferenceDto> categoryPreferences;
}
