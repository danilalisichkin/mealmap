package com.mealmap.productservice.core.dto.product;

import com.mealmap.productservice.core.dto.allergen.AllergenDto;
import com.mealmap.productservice.core.dto.category.CategorySimpleDto;
import com.mealmap.productservice.core.dto.nutrient.NutrientDto;
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
@Schema(description = "Информация о продукте")
public class ProductDto {
    private Long id;

    private String name;

    private String imageUrl;

    private Integer price;

    private Integer weight;

    private NutrientDto nutrients;

    private String description;

    private Boolean isNew;

    private Integer supplierId;

    private List<CategorySimpleDto> categories;

    private List<AllergenDto> allergens;
}
