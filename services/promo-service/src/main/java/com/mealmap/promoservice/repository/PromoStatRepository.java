package com.mealmap.promoservice.repository;

import com.mealmap.promoservice.document.PromoStat;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromoStatRepository extends MongoRepository<PromoStat, ObjectId> {
    boolean existsByPromoCodeAndUserId(String promoCode, String userId);
}
