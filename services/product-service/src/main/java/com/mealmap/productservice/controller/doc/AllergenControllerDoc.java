package com.mealmap.productservice.controller.doc;

import com.mealmap.productservice.core.dto.allergen.AllergenCreationDto;
import com.mealmap.productservice.core.dto.allergen.AllergenDto;
import com.mealmap.productservice.core.dto.allergen.AllergenUpdatingDto;
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
@Tag(name = "Allergen API Controller", description = "Предоставляет функционал для работы с аллергенами")
public interface AllergenControllerDoc {

    @Operation(
            summary = "Get all allergens",
            description = "Позволяет получить информацию о всех аллергенах")
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
    ResponseEntity<List<AllergenDto>> getAllAllergens();

    @Operation(
            summary = "Get allergens [bulk]",
            description = "Позволяет получить информацию о аллергенах по их идентифкаторам с помощью BULK-операции")
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
    ResponseEntity<List<AllergenDto>> bulkGetAllergens(
            @Parameter(
                    name = "Идентификаторы аллергенов")
            @RequestParam @Size(min = 1, max = 20) Set<@NotNull Long> ids);

    @Operation(
            summary = "Get allergen",
            description = "Позволяет получить информацию об аллергене")
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
                    description = "Ресурс не найден: аллерген не найден")
    })
    ResponseEntity<AllergenDto> getAllergen(
            @Parameter(
                    name = "Идентификатор аллергена")
            @PathVariable Long id);

    @Operation(
            summary = "Create allergen",
            description = "Позволяет создать новый аллерген")
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
                    responseCode = "409",
                    description = "Конфликт: аллерген с заданным наименованием уже существует"),
    })
    ResponseEntity<AllergenDto> createAllergen(
            @Parameter(
                    name = "Информация об аллергене",
                    description = "Информация о создаваемом аллергене",
                    required = true)
            @RequestBody @Valid AllergenCreationDto allergenDto);

    @Operation(
            summary = "Update allergen",
            description = "Позволяет обновить аллерген")
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
                    description = "Ресурс не найден: аллерген не найден"),
            @ApiResponse(
                    responseCode = "409",
                    description = "Конфликт: аллерген с заданным наименованием уже существует"),
    })
    ResponseEntity<AllergenDto> updateAllergen(
            @Parameter(
                    name = "Идентификатор аллергена")
            @PathVariable Long id,
            @Parameter(
                    name = "Информация об аллергене",
                    description = "Новая информация об аллергене",
                    required = true)
            @RequestBody @Valid AllergenUpdatingDto allergenDto);

    @Operation(
            summary = "Delete allergen",
            description = "Позволяет удалить аллерген")
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
                    description = "Ресурс не найден: аллерген не найден")
    })
    ResponseEntity<Void> deleteAllergen(
            @Parameter(
                    name = "Идентификатор аллергена")
            @PathVariable Long id);
}
