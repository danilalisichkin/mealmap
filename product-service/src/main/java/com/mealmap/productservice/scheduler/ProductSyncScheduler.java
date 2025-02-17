package com.mealmap.productservice.scheduler;

import com.mealmap.productservice.service.ElasticsearchSyncService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(
        prefix = "business.elasticsearch.product.sync", name = "enabled",
        havingValue = "true", matchIfMissing = true)
public class ProductSyncScheduler {
    private final ElasticsearchSyncService esSyncService;

    @Scheduled(fixedRateString = "${business.elasticsearch.product.sync.interval}000")
    public void syncCategories() {
        esSyncService.syncProducts();
    }
}
