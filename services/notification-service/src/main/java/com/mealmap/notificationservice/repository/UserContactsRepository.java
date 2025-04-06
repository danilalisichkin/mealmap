package com.mealmap.notificationservice.repository;

import com.mealmap.notificationservice.doc.UserContacts;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserContactsRepository extends MongoRepository<UserContacts, ObjectId> {
    Optional<UserContacts> findByUserId(String userId);

    boolean existsByUserId(String userId);
}