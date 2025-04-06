package com.mealmap.recommendationservice.document;

import com.mealmap.recommendationservice.document.value.RecommendationItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Document("user_recommendations")
public class UserRecommendation {
    @Id
    private ObjectId id;

    @Indexed
    private String userId;

    private List<RecommendationItem> items;

    private String message;

    private LocalDateTime createdAt;
}
