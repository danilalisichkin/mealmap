package com.mealmap.productservice.controller.api;

import com.mealmap.productservice.core.dto.allergen.AllergenCreationDto;
import com.mealmap.productservice.core.dto.allergen.AllergenDto;
import com.mealmap.productservice.core.dto.allergen.AllergenUpdatingDto;
import com.mealmap.productservice.service.AllergenService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/allergens")
public class AllergenController {
    private final AllergenService allergenService;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<AllergenDto>> getAllergens() {
        List<AllergenDto> allergens = allergenService.getAllAllergens();

        return ResponseEntity.status(HttpStatus.OK).body(allergens);
    }

    @GetMapping("/bulk")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<AllergenDto>> bulkGetAllergens(
            @RequestParam @Size(min = 1, max = 20) Set<@NotNull Long> ids) {


        List<AllergenDto> allergens = allergenService.getAllergens(ids);

        return ResponseEntity.status(HttpStatus.OK).body(allergens);
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<AllergenDto> getAllergen(@PathVariable Long id) {
        AllergenDto allergen = allergenService.getAllergen(id);

        return ResponseEntity.status(HttpStatus.OK).body(allergen);
    }

    @PostMapping
    @PreAuthorize("hasRole('OPERATOR') and hasRole('ADMIN')")
    public ResponseEntity<AllergenDto> createAllergen(
            @RequestBody @Valid AllergenCreationDto allergenDto) {

        AllergenDto allergen = allergenService.createAllergen(allergenDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(allergen);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('OPERATOR') and hasRole('ADMIN')")
    public ResponseEntity<AllergenDto> updateCategory(
            @PathVariable Long id, @RequestBody @Valid AllergenUpdatingDto allergenDto) {

        AllergenDto allergen = allergenService.updateAllergen(id, allergenDto);

        return ResponseEntity.status(HttpStatus.OK).body(allergen);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('OPERATOR') and hasRole('ADMIN')")
    public ResponseEntity<Void> deleteAllergen(@PathVariable Long id) {
        allergenService.deleteAllergen(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
