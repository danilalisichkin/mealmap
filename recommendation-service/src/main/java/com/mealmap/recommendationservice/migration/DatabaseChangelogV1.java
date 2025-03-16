package com.mealmap.recommendationservice.migration;

import com.mealmap.recommendationservice.document.UserRecommendation;
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
        mongoTemplate.indexOps(UserRecommendation.class).ensureIndex(
                new Index().on("userId", Sort.Direction.ASC)
        );

        if (!mongoTemplate.collectionExists(UserRecommendation.class)) {
            mongoTemplate.createCollection(UserRecommendation.class);
        }
    }

    @RollbackBeforeExecution
    public void rollbackBeforeExecution(final MongoTemplate mongoTemplate) {
        if (mongoTemplate.collectionExists(UserRecommendation.class)) {
            mongoTemplate.dropCollection(UserRecommendation.class);
        }
    }

    @Execution
    public void execute(final MongoTemplate mongoTemplate) {
    }

    @RollbackExecution
    public void rollback(final MongoTemplate mongoTemplate) {
    }
}
