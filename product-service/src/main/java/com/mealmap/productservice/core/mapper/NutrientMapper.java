package com.mealmap.productservice.core.mapper;

import com.mealmap.productservice.core.dto.nutrient.NutrientDto;
import com.mealmap.productservice.document.NutrientDoc;
import com.mealmap.productservice.entity.Nutrient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface NutrientMapper {
    NutrientDoc entityToDocument(Nutrient entity);

    NutrientDto documentToDto(NutrientDoc document);

    @Mapping(target = "id", ignore = true)
    Nutrient dtoToEntity(NutrientDto dto);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(@MappingTarget Nutrient entity, NutrientDto dto);
}
