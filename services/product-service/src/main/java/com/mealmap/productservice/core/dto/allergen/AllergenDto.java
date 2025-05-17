package com.mealmap.productservice.core.dto.allergen;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AllergenDto {
    private Long id;

    private String name;
}
