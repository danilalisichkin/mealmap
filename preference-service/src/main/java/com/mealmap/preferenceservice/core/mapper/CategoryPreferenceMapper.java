package com.mealmap.preferenceservice.core.mapper;

import com.mealmap.preferenceservice.core.dto.CategoryPreferenceCreationDto;
import com.mealmap.preferenceservice.core.dto.CategoryPreferenceDto;
import com.mealmap.preferenceservice.entity.CategoryPreference;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryPreferenceMapper {
    CategoryPreferenceDto entityToDto(CategoryPreference entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userPreference", ignore = true)
    CategoryPreference dtoToEntity(CategoryPreferenceCreationDto dto);

    List<CategoryPreferenceDto> entityListToDtoList(List<CategoryPreference> entityList);
}
