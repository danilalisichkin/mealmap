package com.mealmap.preferenceservice.controller.doc;

import com.mealmap.preferenceservice.core.dto.CategoryPreferenceCreationDto;
import com.mealmap.preferenceservice.core.dto.CategoryPreferenceDto;
import com.mealmap.preferenceservice.core.dto.ProductPreferenceCreationDto;
import com.mealmap.preferenceservice.core.dto.ProductPreferenceDto;
import com.mealmap.preferenceservice.core.dto.UserPreferencesDto;
import com.mealmap.preferenceservice.entity.enums.PreferenceType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@SecurityRequirement(name = "token")
@Tag(name = "User Preferences API Controller", description = "Предоставляет функционал для работы с предпочтениями пользователя")
public interface UserPreferencesControllerDoc {

    @Operation(
            summary = "Get all preferences",
            description = "Позволяет получить информацию о всех предпочтениях пользователя")
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
                    description = "Доступ запрещен: пользователь пытается получить предпочтения другого пользователя"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ресурс не найден: информация о предпочтениях пользователя не найдена")
    })
    ResponseEntity<UserPreferencesDto> getAllPreferences(
            @Parameter(
                    name = "Идентификатор пользователя")
            @PathVariable UUID userId);

    @Operation(
            summary = "Get product preferences",
            description = "Позволяет получить информацию о предпочтениии продуктов пользователя")
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
                    description = "Доступ запрещен: пользователь пытается получить предпочтения другого пользователя"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ресурс не найден: информация о предпочтениях пользователя не найдена")
    })
    ResponseEntity<List<ProductPreferenceDto>> getProductPreferences(
            @Parameter(
                    name = "Идентификатор пользователя")
            @PathVariable UUID userId,
            @Parameter(
                    name = "Тип предпочтения",
                    description = "Тип предпочтения")
            @RequestParam(required = false) PreferenceType preferenceType);

    @Operation(
            summary = "Get category preferences",
            description = "Позволяет получить информацию о предпочтениии категорий пользователя")
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
                    description = "Доступ запрещен: пользователь пытается получить предпочтения другого пользователя"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ресурс не найден: информация о предпочтениях пользователя не найдена")
    })
    ResponseEntity<List<CategoryPreferenceDto>> getCategoryPreferences(
            @Parameter(
                    name = "Идентификатор пользователя")
            @PathVariable UUID userId,
            @Parameter(
                    name = "Тип предпочтения",
                    description = "Тип предпочтения")
            @RequestParam(required = false) PreferenceType preferenceType);

    @Operation(
            summary = "Get product preference",
            description = "Позволяет получить информацию о предпочтениии продукта пользователя")
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
                    description = "Доступ запрещен: пользователь пытается получить предпочтения другого пользователя"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ресурс не найден: информация о предпочтении продукта не найдена")
    })
    ResponseEntity<ProductPreferenceDto> getProductPreference(
            @Parameter(
                    name = "Идентификатор пользователя")
            @PathVariable UUID userId,
            @Parameter(
                    name = "Идентификатор продукта")
            @PathVariable Long productId);

    @Operation(
            summary = "Get category preference",
            description = "Позволяет получить информацию о предпочтениии категории пользователя")
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
                    description = "Доступ запрещен: пользователь пытается получить предпочтения другого пользователя"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ресурс не найден: информация о предпочтении категории не найдена")
    })
    ResponseEntity<CategoryPreferenceDto> getCategoryPreference(
            @Parameter(
                    name = "Идентификатор пользователя")
            @PathVariable UUID userId,
            @Parameter(
                    name = "Идентификатор категории")
            @PathVariable Long categoryId);


    @Operation(
            summary = "Add product preference",
            description = "Позволяет добавить информацию о предпочтениии продукта пользователя")
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
                    description = "Доступ запрещен: пользователь пытается изменить предпочтения другого пользователя"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ресурс не найден: информация о предпочтенях пользователя не найдена"),
            @ApiResponse(
                    responseCode = "409",
                    description = "Конфликт: информация о предпочтении продукта уже существует")
    })
    ResponseEntity<ProductPreferenceDto> addProductPreference(
            @Parameter(
                    name = "Идентификатор пользователя")
            @PathVariable UUID userId,
            @Parameter(
                    name = "Информация о предпочтении продукта",
                    description = "Информация предпочтении продукта пользователя",
                    required = true)
            @RequestBody @Valid ProductPreferenceCreationDto productPreferenceDto);

    @Operation(
            summary = "Add category preference",
            description = "Позволяет добавить информацию о предпочтениии категории пользователя")
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
                    description = "Доступ запрещен: пользователь пытается изменить предпочтения другого пользователя"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ресурс не найден: информация о предпочтениях пользователя не найдена"),
            @ApiResponse(
                    responseCode = "409",
                    description = "Конфликт: информация о предпочтении категории уже существует")
    })
    ResponseEntity<CategoryPreferenceDto> addCategoryPreference(
            @Parameter(
                    name = "Идентификатор пользователя")
            @PathVariable UUID userId,
            @Parameter(
                    name = "Информация о предпочтении категории",
                    description = "Информация предпочтении категории пользователя",
                    required = true)
            @RequestBody @Valid CategoryPreferenceCreationDto categoryPreferenceDto);

    @Operation(
            summary = "Remove product preference",
            description = "Позволяет удалить информацию о предпочтениии продукта пользователя")
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
                    description = "Доступ запрещен: пользователь пытается изменить предпочтения другого пользователя"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ресурс не найден: информация о предпочтении пользователя не найдена")
    })
    ResponseEntity<Void> removeProductPreference(
            @Parameter(
                    name = "Идентификатор пользователя")
            @PathVariable UUID userId,
            @Parameter(
                    name = "Идентификатор продукта")
            @PathVariable Long productId);

    @Operation(
            summary = "Remove category preference",
            description = "Позволяет удалить информацию о предпочтениии категории пользователя")
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
                    description = "Доступ запрещен: пользователь пытается изменить предпочтения другого пользователя"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ресурс не найден: информация о предпочтении пользователя не найдена")
    })
    ResponseEntity<Void> removeCategoryPreference(
            @Parameter(
                    name = "Идентификатор пользователя")
            @PathVariable UUID userId,
            @Parameter(
                    name = "Идентификатор категории")
            @PathVariable Long categoryId);
}
