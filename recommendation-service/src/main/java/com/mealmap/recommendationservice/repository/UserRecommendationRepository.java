package com.mealmap.recommendationservice.repository;

import com.mealmap.recommendationservice.document.UserRecommendation;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRecommendationRepository extends MongoRepository<UserRecommendation, ObjectId> {
    Page<UserRecommendation> findAllByUserId(String userId, Pageable pageable);
}
