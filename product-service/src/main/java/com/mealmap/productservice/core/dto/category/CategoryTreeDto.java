package com.mealmap.productservice.core.dto.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryTreeDto {
    private Long id;

    private String name;

    private CategoryTreeDto parent;
}
