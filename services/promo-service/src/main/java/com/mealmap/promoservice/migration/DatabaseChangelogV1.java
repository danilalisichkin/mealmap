package com.mealmap.promoservice.migration;

import com.mealmap.promoservice.document.PromoCode;
import com.mealmap.promoservice.document.PromoStat;
import com.mongodb.client.model.IndexOptions;
import io.mongock.api.annotations.BeforeExecution;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackBeforeExecution;
import io.mongock.api.annotations.RollbackExecution;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;

@ChangeUnit(id = "init-collections", order = "001", author = "danilalisichkin")
public class DatabaseChangelogV1 {

    @BeforeExecution
    public void beforeExecution(final MongoTemplate mongoTemplate) {
        if (!mongoTemplate.collectionExists(PromoCode.class)) {
            mongoTemplate.createCollection(PromoCode.class);
        }

        if (!mongoTemplate.collectionExists(PromoStat.class)) {
            mongoTemplate.createCollection(PromoStat.class);
        }

        Document index = new Document("promoCode", 1).append("userId", 1);
        IndexOptions options = new IndexOptions().unique(true).name("idx_promo_stats_promoCode_userId");
        mongoTemplate.getCollection("promo_stats").createIndex(index, options);
    }

    @RollbackBeforeExecution
    public void rollbackBeforeExecution(final MongoTemplate mongoTemplate) {
        if (mongoTemplate.collectionExists(PromoCode.class)) {
            mongoTemplate.dropCollection(PromoCode.class);
        }
        if (mongoTemplate.collectionExists(PromoStat.class)) {
            mongoTemplate.dropCollection(PromoStat.class);
        }
    }

    @Execution
    public void execute(final MongoTemplate mongoTemplate) {
    }

    @RollbackExecution
    public void rollback(final MongoTemplate mongoTemplate) {
    }
}
