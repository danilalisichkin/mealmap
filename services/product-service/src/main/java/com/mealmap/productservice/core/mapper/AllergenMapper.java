package com.mealmap.productservice.core.mapper;

import com.mealmap.productservice.core.dto.allergen.AllergenCreationDto;
import com.mealmap.productservice.core.dto.allergen.AllergenDto;
import com.mealmap.productservice.core.dto.allergen.AllergenUpdatingDto;
import com.mealmap.productservice.document.AllergenDoc;
import com.mealmap.productservice.entity.Allergen;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AllergenMapper {
    AllergenDoc entityToDocument(Allergen entity);

    AllergenDto documentToDto(AllergenDoc document);

    AllergenDto entityToDto(Allergen entity);

    Allergen dtoToEntity(AllergenCreationDto dto);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(@MappingTarget Allergen entity, AllergenUpdatingDto dto);

    List<AllergenDto> entityListToDtoList(List<Allergen> entityList);
}
