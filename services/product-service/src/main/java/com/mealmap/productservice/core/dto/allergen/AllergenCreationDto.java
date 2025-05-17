package com.mealmap.productservice.core.dto.allergen;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AllergenCreationDto {
    @NotEmpty
    @Size(max = 50)
    String name;
}
