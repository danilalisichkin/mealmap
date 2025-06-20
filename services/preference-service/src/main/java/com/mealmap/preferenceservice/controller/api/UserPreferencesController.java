package com.mealmap.preferenceservice.controller.api;

import com.mealmap.preferenceservice.controller.doc.UserPreferencesControllerDoc;
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
public class UserPreferencesController implements UserPreferencesControllerDoc {
    private final UserPreferencesService userPreferencesService;

    @Override
    @GetMapping("/{userId}/preferences")
    @PreAuthorize("(hasUserId(#userId) and hasRole('CUSTOMER')) " +
            "or (isApplicationService() and hasRole('RECOMMENDATION_SERVICE'))")
    public ResponseEntity<UserPreferencesDto> getAllPreferences(@PathVariable UUID userId) {
        UserPreferencesDto userPreference = userPreferencesService.getAllPreferences(userId);

        return ResponseEntity.status(HttpStatus.OK).body(userPreference);
    }

    @Override
    @GetMapping("/{userId}/preferences/products")
    @PreAuthorize("hasUserId(#userId) and hasRole('CUSTOMER')")
    public ResponseEntity<List<ProductPreferenceDto>> getProductPreferences(
            @PathVariable UUID userId,
            @RequestParam(required = false) PreferenceType preferenceType) {

        List<ProductPreferenceDto> productPreferences
                = userPreferencesService.getProductPreferences(userId, preferenceType);

        return ResponseEntity.status(HttpStatus.OK).body(productPreferences);
    }

    @Override
    @GetMapping("/{userId}/preferences/categories")
    @PreAuthorize("hasUserId(#userId) and hasRole('CUSTOMER')")
    public ResponseEntity<List<CategoryPreferenceDto>> getCategoryPreferences(
            @PathVariable UUID userId,
            @RequestParam(required = false) PreferenceType preferenceType) {

        List<CategoryPreferenceDto> categoryPreferences
                = userPreferencesService.getCategoryPreferences(userId, preferenceType);

        return ResponseEntity.status(HttpStatus.OK).body(categoryPreferences);
    }

    @Override
    @GetMapping("/{userId}/preferences/products/{productId}")
    @PreAuthorize("hasUserId(#userId) and hasRole('CUSTOMER')")
    public ResponseEntity<ProductPreferenceDto> getProductPreference(@PathVariable UUID userId, @PathVariable Long productId) {
        ProductPreferenceDto productPreference = userPreferencesService.getProductPreference(userId, productId);

        return ResponseEntity.status(HttpStatus.OK).body(productPreference);
    }

    @Override
    @GetMapping("/{userId}/preferences/categories/{categoryId}")
    @PreAuthorize("hasUserId(#userId) and hasRole('CUSTOMER')")
    public ResponseEntity<CategoryPreferenceDto> getCategoryPreference(@PathVariable UUID userId, @PathVariable Long categoryId) {
        CategoryPreferenceDto categoryPreference = userPreferencesService.getCategoryPreference(userId, categoryId);

        return ResponseEntity.status(HttpStatus.OK).body(categoryPreference);
    }

    @Override
    @PostMapping("/{userId}/preferences/products")
    @PreAuthorize("hasUserId(#userId) and hasRole('CUSTOMER')")
    public ResponseEntity<ProductPreferenceDto> addProductPreference(
            @PathVariable UUID userId,
            @RequestBody @Valid ProductPreferenceCreationDto productPreferenceDto) {

        ProductPreferenceDto productPreference
                = userPreferencesService.addProductPreference(userId, productPreferenceDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(productPreference);
    }

    @Override
    @PostMapping("/{userId}/preferences/categories")
    @PreAuthorize("hasUserId(#userId) and hasRole('CUSTOMER')")
    public ResponseEntity<CategoryPreferenceDto> addCategoryPreference(
            @PathVariable UUID userId,
            @RequestBody @Valid CategoryPreferenceCreationDto categoryPreferenceDto) {

        CategoryPreferenceDto categoryPreference
                = userPreferencesService.addCategoryPreference(userId, categoryPreferenceDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(categoryPreference);
    }

    @Override
    @DeleteMapping("/{userId}/preferences/products/{productId}")
    @PreAuthorize("hasUserId(#userId) and hasRole('CUSTOMER')")
    public ResponseEntity<Void> removeProductPreference(@PathVariable UUID userId, @PathVariable Long productId) {
        userPreferencesService.removeProductPreference(userId, productId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    @DeleteMapping("/{userId}/preferences/categories/{categoryId}")
    @PreAuthorize("hasUserId(#userId) and hasRole('CUSTOMER')")
    public ResponseEntity<Void> removeCategoryPreference(@PathVariable UUID userId, @PathVariable Long categoryId) {
        userPreferencesService.removeCategoryPreference(userId, categoryId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
