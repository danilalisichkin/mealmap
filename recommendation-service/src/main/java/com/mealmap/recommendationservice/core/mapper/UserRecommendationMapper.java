package com.mealmap.recommendationservice.core.mapper;

import com.mealmap.recommendationservice.ai.dto.ChatRecommendations;
import com.mealmap.recommendationservice.core.dto.UserRecommendationDto;
import com.mealmap.recommendationservice.document.UserRecommendation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserRecommendationMapper {
    UserRecommendationDto docToDto(UserRecommendation doc);

    @Mapping(target = "items", source = "recommendations")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    UserRecommendation chatRecommendationsToDoc(ChatRecommendations chatRecommendations);

    List<UserRecommendationDto> docListToDtoList(List<UserRecommendation> docList);

    default Page<UserRecommendationDto> docPageToDtoPage(Page<UserRecommendation> docPage) {
        return new PageImpl<>(
                docListToDtoList(docPage.getContent()),
                docPage.getPageable(),
                docPage.getTotalElements());
    }
}
