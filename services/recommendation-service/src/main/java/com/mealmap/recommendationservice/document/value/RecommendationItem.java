package com.mealmap.recommendationservice.document.value;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecommendationItem {
    private Long productId;

    private Integer quantity;
}
