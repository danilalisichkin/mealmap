package com.mealmap.preferenceservice.controller.api;

import com.mealmap.preferenceservice.core.dto.CategoryPreferenceCreationDto;
import com.mealmap.preferenceservice.core.dto.CategoryPreferenceDto;
import com.mealmap.preferenceservice.core.dto.ProductPreferenceCreationDto;
import com.mealmap.preferenceservice.core.dto.ProductPreferenceDto;
import com.mealmap.preferenceservice.core.dto.UserPreferencesDto;
import com.mealmap.preferenceservice.entity.enums.PreferenceType;
import com.mealmap.preferenceservice.service.UserPreferencesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserPreferencesController {
    private final UserPreferencesService userPreferencesService;

    @GetMapping("/{userId}/preferences")
    @PreAuthorize("hasUserId(#userId) or (isApplicationService() and hasRole('RECOMMENDATION_SERVICE'))")
    public ResponseEntity<UserPreferencesDto> getAllPreferences(@PathVariable UUID userId) {
        UserPreferencesDto userPreference = userPreferencesService.getAllPreferences(userId);

        return ResponseEntity.status(HttpStatus.OK).body(userPreference);
    }

    @GetMapping("/{userId}/preferences/products")
    @PreAuthorize("hasUserId(#userId)")
    public ResponseEntity<List<ProductPreferenceDto>> getProductPreferences(
            @PathVariable UUID userId,
            @RequestParam(required = false) PreferenceType preferenceType) {

        List<ProductPreferenceDto> productPreferences
                = userPreferencesService.getProductPreferences(userId, preferenceType);

        return ResponseEntity.status(HttpStatus.OK).body(productPreferences);
    }

    @GetMapping("/{userId}/preferences/categories")
    @PreAuthorize("hasUserId(#userId)")
    public ResponseEntity<List<CategoryPreferenceDto>> getCategoryPreferences(
            @PathVariable UUID userId,
            @RequestParam(required = false) PreferenceType preferenceType) {

        List<CategoryPreferenceDto> categoryPreferences
                = userPreferencesService.getCategoryPreferences(userId, preferenceType);

        return ResponseEntity.status(HttpStatus.OK).body(categoryPreferences);
    }

    @PostMapping("/{userId}/preferences/products")
    @PreAuthorize("hasUserId(#userId)")
    public ResponseEntity<ProductPreferenceDto> addProductPreference(
            @PathVariable UUID userId,
            @RequestBody @Valid ProductPreferenceCreationDto productPreferenceDto) {

        ProductPreferenceDto productPreference
                = userPreferencesService.addProductPreference(userId, productPreferenceDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(productPreference);
    }

    @PostMapping("/{userId}/preferences/categories")
    @PreAuthorize("hasUserId(#userId)")
    public ResponseEntity<CategoryPreferenceDto> addCategoryPreference(
            @PathVariable UUID userId,
            @RequestBody @Valid CategoryPreferenceCreationDto categoryPreferenceDto) {

        CategoryPreferenceDto categoryPreference
                = userPreferencesService.addCategoryPreference(userId, categoryPreferenceDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(categoryPreference);
    }

    @DeleteMapping("/{userId}/preferences/products/{id}")
    @PreAuthorize("hasUserId(#userId)")
    public ResponseEntity<Void> removeProductPreference(@PathVariable UUID userId, @PathVariable Long id) {
        userPreferencesService.removeProductPreference(userId, id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{userId}/preferences/categories/{id}")
    @PreAuthorize("hasUserId(#userId)")
    public ResponseEntity<Void> removeCategoryPreference(@PathVariable UUID userId, @PathVariable Long id) {
        userPreferencesService.removeCategoryPreference(userId, id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
