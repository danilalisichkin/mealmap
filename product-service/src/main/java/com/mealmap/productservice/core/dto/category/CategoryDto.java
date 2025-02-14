package com.mealmap.productservice.core.dto.category;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class CategoryDto {
    Long id;

    String name;

    CategoryShortInfo parent;

    List<CategoryShortInfo> children;
}
