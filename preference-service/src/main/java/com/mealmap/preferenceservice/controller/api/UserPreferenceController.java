package com.mealmap.preferenceservice.controller.api;

import com.mealmap.preferenceservice.core.dto.CategoryPreferenceCreationDto;
import com.mealmap.preferenceservice.core.dto.CategoryPreferenceDto;
import com.mealmap.preferenceservice.core.dto.ProductPreferenceCreationDto;
import com.mealmap.preferenceservice.core.dto.ProductPreferenceDto;
import com.mealmap.preferenceservice.core.dto.UserPreferenceDto;
import com.mealmap.preferenceservice.entity.enums.PreferenceType;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api/v1/users")
public class UserPreferenceController {
    @GetMapping("/{userId}/preferences")
    public ResponseEntity<UserPreferenceDto> getUserPreferences(@PathVariable UUID userId) {

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{userId}/preferences/products")
    public ResponseEntity<List<ProductPreferenceDto>> getProductPreferences(
            @PathVariable UUID userId,
            @RequestParam(required = false) PreferenceType preferenceType) {

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{userId}/preferences/categories")
    public ResponseEntity<List<CategoryPreferenceDto>> getCategoryPreferences(
            @PathVariable UUID userId,
            @RequestParam(required = false) PreferenceType preferenceType) {

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/{userId}/preferences/products")
    public ResponseEntity<List<ProductPreferenceDto>> addProductPreference(
            @PathVariable UUID userId,
            @RequestBody @Valid ProductPreferenceCreationDto productPreferenceDto) {

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/{userId}/preferences/categories")
    public ResponseEntity<List<CategoryPreferenceDto>> addCategoryPreference(
            @PathVariable UUID userId,
            @RequestBody @Valid CategoryPreferenceCreationDto categoryPreferenceDto) {

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{userId}/preferences/products/{id}")
    public ResponseEntity<Void> removeUserProductPreference(@PathVariable UUID userId, @PathVariable Long id) {

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{userId}/preferences/categories/{id}")
    public ResponseEntity<Void> removeUserCategoryPreference(@PathVariable UUID userId, @PathVariable Long id) {

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
