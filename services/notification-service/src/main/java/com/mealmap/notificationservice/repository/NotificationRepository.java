package com.mealmap.notificationservice.repository;

import com.mealmap.notificationservice.doc.Notification;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository
        extends MongoRepository<Notification, ObjectId>, QuerydslPredicateExecutor<Notification> {

    Page<Notification> findAllByUserId(String userId, Pageable pageable);
}
