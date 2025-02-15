package com.mealmap.productservice.core.dto.category;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CategorySimpleDto {
    Long id;

    String name;

    CategoryShortInfo parent;
}
