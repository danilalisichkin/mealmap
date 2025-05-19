package com.mealmap.recommendationservice.controller.doc;

import com.mealmap.recommendationservice.core.dto.UserRecommendationDto;
import com.mealmap.recommendationservice.core.enums.sort.UserRecommendationSortField;
import com.mealmap.starters.paginationstarter.dto.PageDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@SecurityRequirement(name = "token")
@Tag(name = "User Recommendation API Controller", description = "Предоставляет функционал для работы с рекомендациями пользователя")
public interface UserRecommendationControllerDoc {

    @Operation(
            summary = "Get recommendations",
            description = "Позволяет получить информацию о рекомендациях пользователя")
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
                    description = "Доступ запрещен: пользователь не является клиентом")
    })
    ResponseEntity<PageDto<UserRecommendationDto>> getUserRecommendations(
            @Parameter(
                    name = "Идентификатор пользователя")
            @PathVariable UUID userId,
            @RequestParam(defaultValue = "0") @PositiveOrZero Integer offset,
            @RequestParam(defaultValue = "10") @Positive @Max(20) Integer limit,
            @RequestParam(defaultValue = "ID") UserRecommendationSortField sortBy,
            @RequestParam(defaultValue = "ASC") Sort.Direction sortOrder);

    @Operation(
            summary = "Get new recommendation",
            description = "Позволяет получить новую рекомендацию для пользователя")
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
                    description = "Доступ запрещен: пользователь не является клиентом")
    })
    ResponseEntity<UserRecommendationDto> getNewUserRecommendation(
            @Parameter(
                    name = "Идентификатор пользователя")
            @PathVariable UUID userId);
}
