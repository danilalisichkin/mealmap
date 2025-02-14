package com.mealmap.productservice.core.dto.product;

import com.mealmap.productservice.core.dto.nutrient.NutrientDto;
import lombok.Builder;
import lombok.Value;

import java.util.Set;

@Value
@Builder
public class ProductUpdatingDto {
    String name;

    Integer price;

    Integer weight;

    NutrientDto nutrients;

    String description;

    Integer supplierId;

    Set<Long> categories;
}
