package com.mealmap.recommendationservice.core.model.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategorySimple {
    private Long id;

    private String name;

    private CategoryShortInfo parent;
}
