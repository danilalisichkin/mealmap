package com.mealmap.preferenceservice.core.mapper;

import com.mealmap.preferenceservice.core.dto.UserPreferencesDto;
import com.mealmap.preferenceservice.entity.UserPreferences;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {
        ProductPreferenceMapper.class,
        CategoryPreferenceMapper.class})
public interface UserPreferencesMapper {
    UserPreferencesDto entityToDto(UserPreferences entity);
}
