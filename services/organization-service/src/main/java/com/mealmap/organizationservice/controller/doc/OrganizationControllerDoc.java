package com.mealmap.organizationservice.controller.doc;

import com.mealmap.organizationservice.core.dto.filter.OrganizationFilter;
import com.mealmap.organizationservice.core.dto.organization.OrganizationCreationDto;
import com.mealmap.organizationservice.core.dto.organization.OrganizationDto;
import com.mealmap.organizationservice.core.dto.organization.OrganizationUpdatingDto;
import com.mealmap.organizationservice.core.enums.sort.OrganizationSortField;
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
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@SecurityRequirement(name = "token")
@Tag(name = "Organization API Controller", description = "Предоставляет функционал для работы с организациями")
public interface OrganizationControllerDoc {

    @Operation(
            summary = "Get organizations",
            description = "Позволяет получить страницу организаций")
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
    ResponseEntity<PageDto<OrganizationDto>> getPageOfOrganizations(
            @RequestParam(defaultValue = "0") @PositiveOrZero Integer offset,
            @RequestParam(defaultValue = "10") @Positive @Max(20) Integer limit,
            @RequestParam(defaultValue = "ID") OrganizationSortField sortBy,
            @RequestParam(defaultValue = "ASC") Sort.Direction sortOrder,
            @ModelAttribute @Valid OrganizationFilter filter);

    @Operation(
            summary = "Get suppliers",
            description = "Позволяет получить страницу поставщиков")
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
    ResponseEntity<PageDto<OrganizationDto>> getPageOfSuppliers(
            @RequestParam(defaultValue = "0") @PositiveOrZero Integer offset,
            @RequestParam(defaultValue = "10") @Positive @Max(20) Integer limit,
            @RequestParam(defaultValue = "ID") OrganizationSortField sortBy,
            @RequestParam(defaultValue = "ASC") Sort.Direction sortOrder);

    @Operation(
            summary = "Get organization",
            description = "Позволяет получить информацию об организаций")
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
                    description = "Ресурс не найден: информация об организации не найдена")
    })
    ResponseEntity<OrganizationDto> getOrganization(
            @Parameter(
                    name = "Идентификатор организации")
            @PathVariable Integer id);

    @Operation(
            summary = "Get supplier",
            description = "Позволяет получить информацию о поставщике")
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
                    description = "Ресурс не найден: информация о поставщике не найдена")
    })
    ResponseEntity<OrganizationDto> getSupplier(
            @Parameter(
                    name = "Идентификатор поставщика")
            @PathVariable Integer id);

    @Operation(
            summary = "Create organization",
            description = "Позволяет зарегестрировать организацию в системе")
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
                    description = "Конфликт: организация с указанным УНП или названием уже существует")
    })
    ResponseEntity<OrganizationDto> createOrganization(
            @Parameter(
                    name = "Информация об организации",
                    description = "Информация о регистрируемой организации",
                    required = true)
            @RequestBody @Valid OrganizationCreationDto organizationDto);

    @Operation(
            summary = "Update organization",
            description = "Позволяет обновить информацию об организации")
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
                    description = "Доступ запрещен: пользователь не является оператором или администратором организации"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ресурс не найден: организация не найдена"),
            @ApiResponse(
                    responseCode = "409",
                    description = "Конфликт: организация с указанным УНП или названием уже существует")
    })
    ResponseEntity<OrganizationDto> updateOrganization(
            @Parameter(
                    name = "Идентификатор организации")
            @PathVariable Integer id,
            @Parameter(
                    name = "Информация об организации",
                    description = "Информация об организации",
                    required = true)
            @RequestBody @Valid OrganizationUpdatingDto organizationDto);
}
