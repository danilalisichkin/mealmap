package com.mealmap.recommendationservice.core.dto;

import com.mealmap.recommendationservice.document.value.RecommendationItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRecommendationDto {
    private String userId;

    private List<RecommendationItem> items;

    private String message;

    private LocalDateTime createdAt;
}
