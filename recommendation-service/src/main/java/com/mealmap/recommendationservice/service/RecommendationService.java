package com.mealmap.recommendationservice.service;

import com.mealmap.recommendationservice.core.dto.UserRecommendationDto;
import com.mealmap.recommendationservice.core.dto.page.PageDto;
import com.mealmap.recommendationservice.core.enums.sort.UserRecommendationSortField;
import org.springframework.data.domain.Sort;

import java.util.UUID;

public interface RecommendationService {
    PageDto<UserRecommendationDto> getUserRecommendations(
            UUID userId, Integer offset, Integer limit, UserRecommendationSortField sortBy, Sort.Direction sortOrder);

    UserRecommendationDto getNewUserRecommendation(UUID userId);
}
