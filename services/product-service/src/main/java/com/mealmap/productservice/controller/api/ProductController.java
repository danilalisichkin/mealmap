package com.mealmap.productservice.controller.api;

import com.mealmap.productservice.controller.doc.ProductControllerDoc;
import com.mealmap.productservice.core.dto.filter.ProductFilter;
import com.mealmap.productservice.core.dto.product.ProductCreationDto;
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
public class ProductController implements ProductControllerDoc {
    private final ProductService productService;

    @Override
    @GetMapping
    @PreAuthorize("isAuthenticated()")
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

    @Override
    @GetMapping("/all")
    @PreAuthorize("isApplicationService() and hasRole('RECOMMENDATION_SERVICE')")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> allProducts = productService.getAllProducts();

        return ResponseEntity.status(HttpStatus.OK).body(allProducts);
    }

    @Override
    @GetMapping("/bulk")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<ProductDto>> bulkGetProducts(
            @RequestParam @Size(min = 1, max = 20) Set<@NotNull Long> ids) {

        List<ProductDto> products = productService.getProducts(ids);

        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @Override
    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long id) {
        ProductDto product = productService.getProduct(id);

        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @Override
    @PostMapping
    @PreAuthorize("hasRole('SUPPLIER') and hasRole('ADMIN') and isOrganizationMember(#productDto.supplierId)")
    public ResponseEntity<ProductDto> createProduct(@RequestBody @Valid ProductCreationDto productDto) {
        ProductDto product = productService.createProduct(productDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @Override
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('SUPPLIER') and hasRole('ADMIN') and isOrganizationMember(#productDto.supplierId)")
    public ResponseEntity<ProductDto> updateProduct(
            @PathVariable Long id, @RequestBody @Valid ProductUpdatingDto productDto) {

        ProductDto product = productService.updateProduct(id, productDto);

        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @Override
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('OPERATOR') and hasRole('ADMIN')")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
