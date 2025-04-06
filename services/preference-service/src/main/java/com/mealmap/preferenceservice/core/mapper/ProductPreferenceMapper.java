package com.mealmap.preferenceservice.core.mapper;

import com.mealmap.preferenceservice.core.dto.ProductPreferenceCreationDto;
import com.mealmap.preferenceservice.core.dto.ProductPreferenceDto;
import com.mealmap.preferenceservice.entity.ProductPreference;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductPreferenceMapper {
    ProductPreferenceDto entityToDto(ProductPreference entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userPreferences", ignore = true)
    ProductPreference dtoToEntity(ProductPreferenceCreationDto dto);

    List<ProductPreferenceDto> entityListToDtoList(List<ProductPreference> entityList);
}
