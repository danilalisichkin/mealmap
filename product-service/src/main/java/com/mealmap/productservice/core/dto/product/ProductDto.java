package com.mealmap.productservice.core.dto.product;

import com.mealmap.productservice.core.dto.nutrient.NutrientDto;
import com.mealmap.productservice.core.dto.category.CategoryDto;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class ProductDto {
    Long id;

    String name;

    Integer price;

    Integer weight;

    NutrientDto nutrients;

    String description;

    Boolean isNew;

    Integer supplierId;

    List<CategoryDto> categories;
}
