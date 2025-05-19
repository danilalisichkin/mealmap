package com.mealmap.productservice.controller.doc;

import com.mealmap.productservice.core.dto.filter.ProductFilter;
import com.mealmap.productservice.core.dto.product.ProductCreationDto;
import com.mealmap.productservice.core.dto.product.ProductDto;
import com.mealmap.productservice.core.dto.product.ProductUpdatingDto;
import com.mealmap.productservice.core.enums.sort.ProductSortField;
import com.mealmap.starters.paginationstarter.dto.PageDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

@SecurityRequirement(name = "token")
@Tag(name = "Product API Controller", description = "Предоставляет функционал для работы с продуктами")
public interface ProductControllerDoc {

    @Operation(
            summary = "Get products",
            description = "Позволяет получить страницу продуктов")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный ответ"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Некорректный запрос: неверные параметры или отсутствующие обязательные поля"),
            @ApiResponse(
                    responseCode = "401",
                    description = "Несанкционированный доступ: некорректный токен доступа")
    })
    ResponseEntity<PageDto<ProductDto>> getPageOfProducts(
            @RequestParam(defaultValue = "0") @PositiveOrZero Integer offset,
            @RequestParam(defaultValue = "10") @Positive @Max(20) Integer limit,
            @RequestParam(defaultValue = "ID") ProductSortField sortBy,
            @RequestParam(defaultValue = "ASC") Sort.Direction sortOrder,
            @ModelAttribute @Valid ProductFilter filter,
            @RequestParam(required = false) String search);

    @Operation(
            summary = "Get all products",
            description = "Позволяет получить информацию о всех продуктах")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный ответ"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Некорректный запрос: неверные параметры или отсутствующие обязательные поля"),
            @ApiResponse(
                    responseCode = "401",
                    description = "Несанкционированный доступ: некорректный токен доступа")
    })
    ResponseEntity<List<ProductDto>> getAllProducts();

    @Operation(
            summary = "Get products [bulk]",
            description = "Позволяет получить информацию о продуктах по их идентифкаторам с помощью BULK-операции")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный ответ"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Некорректный запрос: неверные параметры или отсутствующие обязательные поля"),
            @ApiResponse(
                    responseCode = "401",
                    description = "Несанкционированный доступ: некорректный токен доступа")
    })
    ResponseEntity<List<ProductDto>> bulkGetProducts(
            @Parameter(
                    name = "Идентификаторы продуктов")
            @RequestParam @Size(min = 1, max = 20) Set<@NotNull Long> ids);

    @Operation(
            summary = "Get product",
            description = "Позволяет получить информацию о продукте")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный ответ"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Некорректный запрос: неверные параметры или отсутствующие обязательные поля"),
            @ApiResponse(
                    responseCode = "401",
                    description = "Несанкционированный доступ: некорректный токен доступа"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ресурс не найден: продукт не найден")
    })
    ResponseEntity<ProductDto> getProduct(
            @Parameter(
                    name = "Идентификатор продукта")
            @PathVariable Long id);

    @Operation(
            summary = "Create product",
            description = "Позволяет создать новый продукт")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Успешный ответ"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Некорректный запрос: неверные параметры или отсутствующие обязательные поля"),
            @ApiResponse(
                    responseCode = "401",
                    description = "Несанкционированный доступ: некорректный токен доступа"),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ запрещен: пользователь не является оператором или администратором поставщика"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ресурс не найден: продукт или его категории / аллергены не найдены"),
            @ApiResponse(
                    responseCode = "409",
                    description = "Конфликт: продукт с заданным наименованием уже существует"),
    })
    ResponseEntity<ProductDto> createProduct(
            @Parameter(
                    name = "Информация о продукте",
                    description = "Информация о создаваемом продукте",
                    required = true)
            @RequestBody @Valid ProductCreationDto productDto);

    @Operation(
            summary = "Update product",
            description = "Позволяет обновить продукт")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный ответ"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Некорректный запрос: неверные параметры или отсутствующие обязательные поля"),
            @ApiResponse(
                    responseCode = "401",
                    description = "Несанкционированный доступ: некорректный токен доступа"),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ запрещен: пользователь не является оператором или администратором поставщика"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ресурс не найден: категории / аллергены продукта не найдены"),
            @ApiResponse(
                    responseCode = "409",
                    description = "Конфликт: продукт с заданным наименованием уже существует"),
    })
    ResponseEntity<ProductDto> updateProduct(
            @Parameter(
                    name = "Идентификатор продукта")
            @PathVariable Long id,
            @Parameter(
                    name = "Информация о продукте",
                    description = "Новая информация о продукте",
                    required = true)
            @RequestBody @Valid ProductUpdatingDto productDto);

    @Operation(
            summary = "Delete product",
            description = "Позволяет удалить продукт")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Успешный ответ"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Некорректный запрос: неверные параметры или отсутствующие обязательные поля"),
            @ApiResponse(
                    responseCode = "401",
                    description = "Несанкционированный доступ: некорректный токен доступа"),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ запрещен: пользователь не является оператором"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ресурс не найден: продукт не найден")
    })
    ResponseEntity<Void> deleteProduct(
            @Parameter(
                    name = "Идентификатор продукта")
            @PathVariable Long id);
}
