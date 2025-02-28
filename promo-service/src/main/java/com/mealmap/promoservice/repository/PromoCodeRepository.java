package com.mealmap.promoservice.repository;

import com.mealmap.promoservice.document.PromoCode;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromoCodeRepository extends MongoRepository<PromoCode, String> {
}
