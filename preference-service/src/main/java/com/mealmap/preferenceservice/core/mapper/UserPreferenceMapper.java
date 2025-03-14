package com.mealmap.preferenceservice.core.mapper;

import com.mealmap.preferenceservice.core.dto.UserPreferenceDto;
import com.mealmap.preferenceservice.entity.UserPreference;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {
        ProductPreferenceMapper.class,
        CategoryPreferenceMapper.class})
public interface UserPreferenceMapper {
    UserPreferenceDto entityToDto(UserPreference entity);
}
