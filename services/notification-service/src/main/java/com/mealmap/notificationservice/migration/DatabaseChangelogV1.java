package com.mealmap.notificationservice.migration;

import com.mealmap.notificationservice.doc.Notification;
import com.mealmap.notificationservice.doc.UserContacts;
import io.mongock.api.annotations.BeforeExecution;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackBeforeExecution;
import io.mongock.api.annotations.RollbackExecution;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;

@ChangeUnit(id = "init-collections", order = "001", author = "danilalisichkin")
public class DatabaseChangelogV1 {

    @BeforeExecution
    public void beforeExecution(final MongoTemplate mongoTemplate) {
        mongoTemplate.indexOps(UserContacts.class).ensureIndex(
                new Index().on("userId", Sort.Direction.ASC).unique()
        );

        if (!mongoTemplate.collectionExists(UserContacts.class)) {
            mongoTemplate.createCollection(UserContacts.class);
        }

        mongoTemplate.indexOps(Notification.class).ensureIndex(
                new Index().on("userId", Sort.Direction.ASC)
        );

        if (!mongoTemplate.collectionExists(Notification.class)) {
            mongoTemplate.createCollection(Notification.class);
        }
    }

    @RollbackBeforeExecution
    public void rollbackBeforeExecution(final MongoTemplate mongoTemplate) {
        if (mongoTemplate.collectionExists(UserContacts.class)) {
            mongoTemplate.dropCollection(UserContacts.class);
        }

        if (mongoTemplate.collectionExists(Notification.class)) {
            mongoTemplate.dropCollection(Notification.class);
        }
    }

    @Execution
    public void execute(final MongoTemplate mongoTemplate) {
    }

    @RollbackExecution
    public void rollback(final MongoTemplate mongoTemplate) {
    }
}
