package com.mealmap.recommendationservice.core.mapper;

import com.mealmap.recommendationservice.client.dto.preference.CategoryPreferenceDto;
import com.mealmap.recommendationservice.client.dto.preference.ProductPreferenceDto;
import com.mealmap.recommendationservice.client.dto.preference.UserPreferenceDto;
import com.mealmap.recommendationservice.core.model.preference.CategoryPreference;
import com.mealmap.recommendationservice.core.model.preference.Preferences;
import com.mealmap.recommendationservice.core.model.preference.ProductPreference;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PreferenceMapper {
    Preferences dtoToModel(UserPreferenceDto dto);

    ProductPreference productPreferenceDtoToModel(ProductPreferenceDto dto);

    CategoryPreference categoryPreferenceDtoToModel(CategoryPreferenceDto dto);

    List<ProductPreference> productPreferenceDtoListToModelList(List<ProductPreferenceDto> dtoList);

    List<CategoryPreference> categoryPreferenceDtoListToModelList(List<CategoryPreferenceDto> dtoList);
}
