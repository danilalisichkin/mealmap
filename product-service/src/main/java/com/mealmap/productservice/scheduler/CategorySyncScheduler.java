package com.mealmap.productservice.scheduler;

import com.mealmap.productservice.service.ElasticsearchSyncService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(
        prefix = "business.elasticsearch.category.sync", name = "enabled",
        havingValue = "true", matchIfMissing = true)
public class CategorySyncScheduler {
    private final ElasticsearchSyncService esSyncService;

    @Scheduled(fixedRateString = "${business.elasticsearch.category.sync.interval}000")
    public void syncCategories() {
        esSyncService.syncCategories();
    }
}
