package com.mealmap.recommendationservice.core.model.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Long id;

    private String name;

    private Integer price;

    private Integer weight;

    private Nutrient nutrients;

    private String description;

    private Boolean isNew;

    private Integer supplierId;

    private List<CategorySimple> categories;
}
