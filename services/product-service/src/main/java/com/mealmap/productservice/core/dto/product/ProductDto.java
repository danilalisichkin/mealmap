package com.mealmap.productservice.core.dto.product;

import com.mealmap.productservice.core.dto.category.CategorySimpleDto;
import com.mealmap.productservice.core.dto.nutrient.NutrientDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;

    private String name;

    private String imageUrl;

    private Integer price;

    private Integer weight;

    private NutrientDto nutrients;

    private String description;

    private Boolean isNew;

    private Integer supplierId;

    private List<CategorySimpleDto> categories;
}
