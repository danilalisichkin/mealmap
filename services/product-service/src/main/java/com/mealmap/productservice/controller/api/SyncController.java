package com.mealmap.productservice.controller.api;

import com.mealmap.productservice.service.ElasticsearchSyncService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sync")
public class SyncController {
    private final ElasticsearchSyncService esSyncService;

    @PostMapping("/products")
    @PreAuthorize("hasRole('OPERATOR') and hasRole('ADMIN')")
    public ResponseEntity<Void> syncProducts() {
        esSyncService.syncProducts();

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/categories")
    @PreAuthorize("hasRole('OPERATOR') and hasRole('ADMIN')")
    public ResponseEntity<Void> syncCategories() {
        esSyncService.syncCategories();

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
