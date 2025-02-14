package com.mealmap.productservice.core.dto.category;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CategoryShortInfo {
    Long id;

    String name;
}
