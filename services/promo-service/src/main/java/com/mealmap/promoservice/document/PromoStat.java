package com.mealmap.promoservice.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Document("promo_stats")
@CompoundIndex(
        name = "idx_promo_stats_promoCode_userId",
        def = "{'promoCode': 1, 'userId': 1}",
        unique = true)
public class PromoStat {
    @Id
    private ObjectId id;

    @Indexed
    private String promoCode;

    @Indexed
    private String userId;

    private LocalDateTime createdAt;
}
