package com.mealmap.productservice.controller.api;

import com.mealmap.productservice.controller.doc.CategoryControllerDoc;
import com.mealmap.productservice.core.dto.category.CategoryCreationDto;
import com.mealmap.productservice.core.dto.category.CategoryDto;
import com.mealmap.productservice.core.dto.category.CategoryTreeDto;
import com.mealmap.productservice.core.dto.category.CategoryUpdatingDto;
import com.mealmap.productservice.service.CategoryService;
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
@RequestMapping("/api/v1/categories")
public class CategoryController implements CategoryControllerDoc {
    private final CategoryService categoryService;

    @Override
    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        List<CategoryDto> categories = categoryService.getAllCategories();

        return ResponseEntity.status(HttpStatus.OK).body(categories);
    }

    @Override
    @GetMapping("/bulk")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<CategoryDto>> bulkGetCategories(
            @RequestParam @Size(min = 1, max = 20) Set<@NotNull Long> ids) {

        List<CategoryDto> categories = categoryService.getCategories(ids);

        return ResponseEntity.status(HttpStatus.OK).body(categories);
    }

    @Override
    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Long id) {
        CategoryDto category = categoryService.getCategory(id);

        return ResponseEntity.status(HttpStatus.OK).body(category);
    }

    @Override
    @GetMapping("/{id}/tree")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<CategoryTreeDto> getCategoryTree(@PathVariable Long id) {
        CategoryTreeDto categoryTree = categoryService.getCategoryTree(id);

        return ResponseEntity.status(HttpStatus.OK).body(categoryTree);
    }

    @Override
    @PostMapping
    @PreAuthorize("hasRole('OPERATOR') and hasRole('ADMIN')")
    public ResponseEntity<CategoryDto> createCategory(
            @RequestBody @Valid CategoryCreationDto categoryDto) {

        CategoryDto category = categoryService.createCategory(categoryDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }

    @Override
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('OPERATOR') and hasRole('ADMIN')")
    public ResponseEntity<CategoryDto> updateCategory(
            @PathVariable Long id, @RequestBody @Valid CategoryUpdatingDto categoryDto) {

        CategoryDto category = categoryService.updateCategory(id, categoryDto);

        return ResponseEntity.status(HttpStatus.OK).body(category);
    }

    @Override
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('OPERATOR') and hasRole('ADMIN')")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
