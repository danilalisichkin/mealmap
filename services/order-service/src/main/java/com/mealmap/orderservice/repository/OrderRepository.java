package com.mealmap.orderservice.repository;

import com.mealmap.orderservice.document.Order;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends MongoRepository<Order, ObjectId>, QuerydslPredicateExecutor<Order> {
    Optional<Order> findByIdAndUserId(ObjectId id, String userId);

    Page<Order> findAllByUserId(String userId, Pageable pageable);
}
