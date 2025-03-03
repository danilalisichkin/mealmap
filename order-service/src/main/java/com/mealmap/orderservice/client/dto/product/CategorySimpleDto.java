package com.mealmap.orderservice.client.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategorySimpleDto {
    private Long id;

    private String name;

    private CategoryShortInfo parent;
}
