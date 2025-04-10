package com.mealmap.productservice.controller.api;

import com.mealmap.productservice.core.dto.filter.ProductFilter;
import com.mealmap.productservice.core.dto.product.ProductCreatingDto;
import com.mealmap.productservice.core.dto.product.ProductDto;
import com.mealmap.productservice.core.dto.product.ProductUpdatingDto;
import com.mealmap.productservice.core.enums.sort.ProductSortField;
import com.mealmap.productservice.service.ProductService;
import com.mealmap.starters.paginationstarter.dto.PageDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<PageDto<ProductDto>> getPageOfProducts(
            @RequestParam(defaultValue = "0") @PositiveOrZero Integer offset,
            @RequestParam(defaultValue = "10") @Positive @Max(20) Integer limit,
            @RequestParam(defaultValue = "ID") ProductSortField sortBy,
            @RequestParam(defaultValue = "ASC") Sort.Direction sortOrder,
            @ModelAttribute @Valid ProductFilter filter,
            @RequestParam(required = false) String search) {

        var page = productService.getPageOfProducts(offset, limit, sortBy, sortOrder, filter, search);

        return ResponseEntity.status(HttpStatus.OK).body(page);
    }

    @GetMapping("/all")
    @PreAuthorize("isApplicationService() and hasRole('RECOMMENDATION_SERVICE')")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> allProducts = productService.getAllProducts();

        return ResponseEntity.status(HttpStatus.OK).body(allProducts);
    }

    @GetMapping("/bulk")
    @PreAuthorize("isApplicationService() and hasRole('ORDER_SERVICE')")
    public ResponseEntity<List<ProductDto>> bulkGetProducts(
            @RequestParam @Size(min = 2, max = 20) Set<@NotNull Long> ids) {

        List<ProductDto> products = productService.getProducts(ids);

        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long id) {
        ProductDto product = productService.getProduct(id);

        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')") //TODO: allow Supplier Admin use this method
    public ResponseEntity<ProductDto> createProduct(@RequestBody @Valid ProductCreatingDto productDto) {
        ProductDto product = productService.createProduct(productDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')") //TODO: allow Supplier Admin use this method
    public ResponseEntity<ProductDto> updateProduct(
            @PathVariable Long id, @RequestBody @Valid ProductUpdatingDto productDto) {

        ProductDto product = productService.updateProduct(id, productDto);

        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')") //TODO: allow Supplier Admin use this method
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
