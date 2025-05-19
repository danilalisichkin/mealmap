package com.mealmap.healthservice.controller.doc;

import com.mealmap.healthservice.core.dto.allergen.AllergenAddingDto;
import com.mealmap.healthservice.core.dto.allergen.AllergenDto;
import com.mealmap.healthservice.core.dto.diet.DietCreationDto;
import com.mealmap.healthservice.core.dto.diet.DietDto;
import com.mealmap.healthservice.core.dto.diet.DietUpdatingDto;
import com.mealmap.healthservice.core.dto.health.PhysicHealthCreationDto;
import com.mealmap.healthservice.core.dto.health.PhysicHealthDto;
import com.mealmap.healthservice.core.dto.health.PhysicHealthHistoryDto;
import com.mealmap.healthservice.core.dto.health.PhysicHealthUpdatingDto;
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

import java.util.List;
import java.util.UUID;

@SecurityRequirement(name = "token")
@Tag(name = "USer Health API Controller", description = "Предоставляет функционал для работы со здоровьем пользователя")
public interface UserHealthControllerDoc {

    @Operation(
            summary = "Get user physical health",
            description = "Позволяет получить информацию о физическом здоровье пользователя")
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
                    description = "Доступ запрещен: пользователь пытается получить информацию о чужом здоровье"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ресурс не найден: информация о физическом здоровье пользователя не найдена")
    })
    ResponseEntity<PhysicHealthDto> getUserPhysicHealth(
            @Parameter(
                    description = "Идентификатор пользователя")
            @PathVariable UUID userId);

    @Operation(
            summary = "Get user physical health history",
            description = "Позволяет получить информацию об истории физического здоровья пользователя")
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
                    description = "Доступ запрещен: пользователь пытается получить информацию о чужом здоровье"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ресурс не найден: информация о физическом здоровье пользователя не найдена")
    })
    ResponseEntity<List<PhysicHealthHistoryDto>> getUserPhysicHealthHistory(
            @Parameter(
                    description = "Идентификатор пользователя")
            @PathVariable UUID userId);

    @Operation(
            summary = "Get user diet",
            description = "Позволяет получить информацию об действующей диете пользователя")
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
                    description = "Доступ запрещен: пользователь пытается получить информацию о чужом здоровье"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ресурс не найден: информация о физическом здоровье пользователя не найдена")
    })
    ResponseEntity<DietDto> getUserDiet(
            @Parameter(
                    description = "Идентификатор пользователя")
            @PathVariable UUID userId);

    @Operation(
            summary = "Get user allergens",
            description = "Позволяет получить информацию об аллергиях пользователя")
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
                    description = "Доступ запрещен: пользователь пытается получить информацию о чужом здоровье"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ресурс не найден: информация о физическом здоровье пользователя не найдена")
    })
    ResponseEntity<List<AllergenDto>> getUserAllergens(
            @Parameter(
                    description = "Идентификатор пользователя")
            @PathVariable UUID userId);

    @Operation(
            summary = "Create physical health",
            description = "Позволяет добавить информацию о физическом здоровье пользователя")
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
                    description = "Доступ запрещен: пользователь пытается добавить информацию о чужом здоровье"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ресурс не найден: информация о физическом здоровье пользователя не найдена"),
            @ApiResponse(
                    responseCode = "409",
                    description = "Конфликт: информация о физическом здоровье пользователя уже существует")
    })
    ResponseEntity<PhysicHealthDto> createUserPhysicHealth(
            @Parameter(
                    description = "Идентификатор пользователя")
            @PathVariable UUID userId,
            @Parameter(
                    name = "Информация о физическом здоровье",
                    description = "Информация о физическом здоровье пользователя",
                    required = true)
            @RequestBody @Valid PhysicHealthCreationDto userPhysicHealthDto);

    @Operation(
            summary = "Create diet",
            description = "Позволяет пользователю начать диету")
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
                    description = "Доступ запрещен: пользователь пытается создать диету для другого пользователя"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ресурс не найден: информация о физическом здоровье пользователя не найдена"),
            @ApiResponse(
                    responseCode = "409",
                    description = "Конфликт: информация о диете пользователя уже существует")
    })
    ResponseEntity<DietDto> createUserDiet(
            @Parameter(
                    description = "Идентификатор пользователя")
            @PathVariable UUID userId,
            @Parameter(
                    name = "Информация о диете",
                    description = "Информация о диете пользователя",
                    required = true)
            @RequestBody @Valid DietCreationDto userDietDto);

    @Operation(
            summary = "Add allergen",
            description = "Позволяет пользователю добавить информацию об аллергии")
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
                    description = "Доступ запрещен: пользователь пытается добавить аллергию для другого пользователя"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ресурс не найден: информация о физическом здоровье пользователя не найдена"),
            @ApiResponse(
                    responseCode = "409",
                    description = "Конфликт: информация об аллергии уже существует")
    })
    ResponseEntity<AllergenDto> addUserAllergen(
            @Parameter(
                    description = "Идентификатор пользователя")
            @PathVariable UUID userId,
            @Parameter(
                    name = "Информация об аллергии",
                    description = "Информация об аллергии пользователя",
                    required = true)
            @RequestBody @Valid AllergenAddingDto allergenDto);

    @Operation(
            summary = "Update physical health",
            description = "Позволяет пользователю обновить информацию о физическом здоровье")
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
                    description = "Доступ запрещен: пользователь пытается добавить аллергию для другого пользователя"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ресурс не найден: информация о физическом здоровье пользователя не найдена")
    })
    ResponseEntity<PhysicHealthDto> updateUserPhysicHealth(
            @Parameter(
                    description = "Идентификатор пользователя")
            @PathVariable UUID userId,
            @Parameter(
                    name = "Информация о физическом здоровье",
                    description = "Информация о физическом здоровье пользователя",
                    required = true)
            @RequestBody @Valid PhysicHealthUpdatingDto userPhysicHealthDto);

    @Operation(
            summary = "Update diet",
            description = "Позволяет пользователю обновить информацию о диете")
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
                    description = "Доступ запрещен: пользователь пытается добавить аллергию для другого пользователя"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ресурс не найден: информация о диете пользователя не найдена")
    })
    ResponseEntity<DietDto> updateUserDiet(
            @Parameter(
                    description = "Идентификатор пользователя")
            @PathVariable UUID userId,
            @Parameter(
                    name = "Информация о диете",
                    description = "Информация о диете пользователя",
                    required = true)
            @RequestBody @Valid DietUpdatingDto userDietDto);

    @Operation(
            summary = "Delete diet",
            description = "Позволяет пользователю завершить диету")
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
                    description = "Доступ запрещен: пользователь пытается завершить диету другого пользователя"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ресурс не найден: информация о диете пользователя не найдена")
    })
    ResponseEntity<Void> deleteUserDiet(
            @Parameter(
                    description = "Идентификатор пользователя")
            @PathVariable UUID userId);

    @Operation(
            summary = "Delete allergen",
            description = "Позволяет пользователю убрать аллергию")
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
                    description = "Доступ запрещен: пользователь пытается завершить диету другого пользователя"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ресурс не найден: информация об аллергии пользователя не найдена")
    })
    ResponseEntity<AllergenDto> removeUserAllergen(
            @Parameter(
                    description = "Идентификатор пользователя")
            @PathVariable UUID userId,
            @Parameter(
                    description = "Идентификатор аллергена")
            @PathVariable Long allergenId);
}
