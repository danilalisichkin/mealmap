package com.mealmap.promoservice.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Document("promo_codes")
public class PromoCode {
    @Id
    private String id;

    private Long limit;

    private Integer discountPercentage;

    private LocalDate startDate;

    private LocalDate endDate;
}
