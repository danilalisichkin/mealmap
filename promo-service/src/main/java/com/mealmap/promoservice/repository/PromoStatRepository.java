package com.mealmap.promoservice.repository;

import com.mealmap.promoservice.document.PromoStat;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PromoStatRepository extends MongoRepository<PromoStat, ObjectId> {
    Optional<PromoStat> findByPromoCodeAndUserId(String promoCode, String userId);
}
