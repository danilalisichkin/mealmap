package com.mealmap.recommendationservice.client.dto.product;

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

    private Integer price;

    private Integer weight;

    private NutrientDto nutrients;

    private String description;

    private Boolean isNew;

    private Integer supplierId;

    private List<CategorySimpleDto> categories;
}
