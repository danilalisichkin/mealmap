package com.mealmap.productservice.core.dto.category;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Информация о категории")
public class CategoryDto {
    private Long id;

    private String name;

    private CategoryShortInfo parent;

    private List<CategoryShortInfo> children;
}
