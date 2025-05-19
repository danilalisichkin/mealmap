package com.mealmap.productservice.core.dto.category;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Упрощенная информация о категории")
public class CategorySimpleDto {
    private Long id;

    private String name;

    private CategoryShortInfo parent;
}
