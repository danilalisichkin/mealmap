package com.mealmap.productservice.core.dto.product;

import com.mealmap.productservice.core.dto.nutrient.NutrientDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;

import java.util.Set;

@Value
@Builder
public class ProductCreatingDto {
    @NotEmpty
    @Size(max = 40)
    String name;

    @NotNull
    @Positive
    Integer price;

    @NotNull
    @Positive
    Integer weight;

    @NotNull
    @Valid
    NutrientDto nutrients;

    @Size(max = 255)
    String description;

    @NotNull
    Integer supplierId;

    @NotEmpty
    Set<Long> categories;
}
