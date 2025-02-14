package com.mealmap.productservice.core.dto.category;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class CategoryUpdatingDto {
    String name;

    Long parent;

    List<Long> children;
}
