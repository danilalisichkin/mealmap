package com.mealmap.productservice.core.dto.category;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CategoryUpdatingDto {
    @NotEmpty
    @Size(max = 50)
    String name;

    Long parent;
}
