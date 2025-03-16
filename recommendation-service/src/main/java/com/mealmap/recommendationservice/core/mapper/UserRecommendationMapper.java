package com.mealmap.recommendationservice.core.mapper;

import com.mealmap.recommendationservice.core.dto.UserRecommendationDto;
import com.mealmap.recommendationservice.document.UserRecommendation;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserRecommendationMapper {
    UserRecommendationDto docToDto(UserRecommendation doc);
}
