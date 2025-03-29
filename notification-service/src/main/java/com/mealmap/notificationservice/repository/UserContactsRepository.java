package com.mealmap.notificationservice.repository;

import com.mealmap.notificationservice.document.UserContacts;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserContactsRepository extends MongoRepository<UserContacts, ObjectId> {
}