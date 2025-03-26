package com.mealmap.recommendationservice.ai.dto;

import com.mealmap.recommendationservice.document.value.RecommendationItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatRecommendations {
    private List<RecommendationItem> recommendations;

    private String message;
}
