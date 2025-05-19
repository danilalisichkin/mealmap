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
@Schema(description = "Информация о дереве иерархии категории")
public class CategoryTreeDto {
    private Long id;

    private String name;

    private CategoryTreeDto parent;
}
