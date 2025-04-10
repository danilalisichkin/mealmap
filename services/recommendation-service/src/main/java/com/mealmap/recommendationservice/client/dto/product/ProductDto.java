package com.mealmap.recommendationservice.client.dto.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
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
