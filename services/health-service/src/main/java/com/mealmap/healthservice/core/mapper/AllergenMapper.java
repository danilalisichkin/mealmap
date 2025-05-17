package com.mealmap.healthservice.core.mapper;

import com.mealmap.healthservice.core.dto.allergen.AllergenAddingDto;
import com.mealmap.healthservice.core.dto.allergen.AllergenDto;
import com.mealmap.healthservice.entity.Allergen;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AllergenMapper {
    AllergenDto entityToDto(Allergen entity);

    @Mapping(target = "id", ignore = true)
    Allergen dtoToEntity(AllergenAddingDto dto);

    List<AllergenDto> entityListToDtoList(List<Allergen> entities);
}
