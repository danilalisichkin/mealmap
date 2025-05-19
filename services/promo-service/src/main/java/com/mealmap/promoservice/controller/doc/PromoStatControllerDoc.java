package com.mealmap.promoservice.controller.doc;

import com.mealmap.promoservice.core.dto.promo.stat.PromoStatCreationDto;
import com.mealmap.promoservice.core.dto.promo.stat.PromoStatDto;
import com.mealmap.promoservice.core.enums.sort.PromoStatSortField;
import com.mealmap.starters.paginationstarter.dto.PageDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@SecurityRequirement(name = "token")
@Tag(name = "Promo Stat API Controller", description = "Предоставляет функционал для работы со статистикой использования промокодов")
public interface PromoStatControllerDoc {

    @Operation(
            summary = "Get promo stats",
            description = "Позволяет получить страницу статистики использования промокодов")
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
                    description = "Доступ запрещен: пользователь не является оператором")
    })
    ResponseEntity<PageDto<PromoStatDto>> getPageOfPromoStats(
            @RequestParam(defaultValue = "0") @PositiveOrZero Integer offset,
            @RequestParam(defaultValue = "10") @Positive @Max(20) Integer limit,
            @RequestParam(defaultValue = "ID") PromoStatSortField sortBy,
            @RequestParam(defaultValue = "ASC") Sort.Direction sortOrder);


    @Operation(
            summary = "Get promo stat",
            description = "Позволяет получить информацию о статистике использования промокода")
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
                    description = "Ресурс не найден: статистика использования промокода не найдена")
    })
    ResponseEntity<PromoStatDto> getPromoStat(
            @Parameter(
                    name = "Идентификатор статистики использования промокода")
            @PathVariable ObjectId id);


    @Operation(
            summary = "Create promo stat",
            description = "Позволяет погасить (использовать) промокод")
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
                    description = "Доступ запрещен: пользователь не является клиентом"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ресурс на найден: промокод не найден"),
            @ApiResponse(
                    responseCode = "409",
                    description = "Конфликт: промокод уже был использован")
    })
    ResponseEntity<PromoStatDto> createPromoStat(
            @Parameter(
                    name = "Информация о промокоде",
                    description = "Информация о применяемом промокоде",
                    required = true)
            @RequestBody @Valid PromoStatCreationDto statDto);
}
