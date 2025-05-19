package com.mealmap.productservice.controller.doc;

import com.mealmap.productservice.core.dto.category.CategoryCreationDto;
import com.mealmap.productservice.core.dto.category.CategoryDto;
import com.mealmap.productservice.core.dto.category.CategoryTreeDto;
import com.mealmap.productservice.core.dto.category.CategoryUpdatingDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

@SecurityRequirement(name = "token")
@Tag(name = "Product API Controller", description = "Предоставляет функционал для работы с продуктами")
public interface CategoryControllerDoc {

    @Operation(
            summary = "Get all categories",
            description = "Позволяет получить все категории")
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
    ResponseEntity<List<CategoryDto>> getAllCategories();

    @Operation(
            summary = "Get categories [bulk]",
            description = "Позволяет получить информацию о категориях по их идентифкаторам с помощью BULK-операции")
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
    ResponseEntity<List<CategoryDto>> bulkGetCategories(
            @Parameter(
                    name = "Идентификаторы продуктов")
            @RequestParam @Size(min = 1, max = 20) Set<@NotNull Long> ids);

    @Operation(
            summary = "Get category",
            description = "Позволяет получить информацию о категории")
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
                    description = "Ресурс не найден: категория не найдена")
    })
    ResponseEntity<CategoryDto> getCategory(
            @Parameter(
                    name = "Идентификатор категории")
            @PathVariable Long id);

    @Operation(
            summary = "Get category tree",
            description = "Позволяет получить древо иерархии категории")
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
                    description = "Ресурс не найден: категория не найдена")
    })
    ResponseEntity<CategoryTreeDto> getCategoryTree(
            @Parameter(
                    name = "Идентификатор категории")
            @PathVariable Long id);

    @Operation(
            summary = "Create category",
            description = "Позволяет создать новую категорию")
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
                    description = "Доступ запрещен: пользователь не является оператором"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ресурс не найден: родительская категория не найдена"),
            @ApiResponse(
                    responseCode = "409",
                    description = "Конфликт: категория с заданным наименованием уже существует"),
    })
    ResponseEntity<CategoryDto> createCategory(
            @Parameter(
                    name = "Информация о категории",
                    description = "Информация о создаваемой категории",
                    required = true)
            @RequestBody @Valid CategoryCreationDto categoryDto);

    @Operation(
            summary = "Update category",
            description = "Позволяет обновить категорию")
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
                    description = "Доступ запрещен: пользователь не является оператором"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ресурс не найден: категория или ее родительская категория не найдены"),
            @ApiResponse(
                    responseCode = "409",
                    description = "Конфликт: категория с заданным наименованием уже существует"),
    })
    ResponseEntity<CategoryDto> updateCategory(
            @Parameter(
                    name = "Идентификатор категории")
            @PathVariable Long id,
            @Parameter(
                    name = "Информация о категории",
                    description = "Новая информация о категории",
                    required = true)
            @RequestBody @Valid CategoryUpdatingDto categoryDto);

    @Operation(
            summary = "Delete category",
            description = "Позволяет удалить категорию")
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
                    description = "Ресурс не найден: категория не найдена")
    })
    ResponseEntity<Void> deleteCategory(
            @Parameter(
                    name = "Идентификатор категории")
            @PathVariable Long id);
}
