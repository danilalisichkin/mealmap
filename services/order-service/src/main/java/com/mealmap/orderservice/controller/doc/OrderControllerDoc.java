package com.mealmap.orderservice.controller.doc;

import com.mealmap.orderservice.core.dto.filter.OrderFilter;
import com.mealmap.orderservice.core.dto.order.OrderDto;
import com.mealmap.orderservice.core.enums.OrderStatus;
import com.mealmap.orderservice.core.enums.sort.OrderSortField;
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
import jakarta.validation.constraints.PositiveOrZero;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@SecurityRequirement(name = "token")
@Tag(name = "Order API Controller", description = "Предоставляет функционал для работы с заказами")
public interface OrderControllerDoc {

    @Operation(
            summary = "Get orders",
            description = "Позволяет получить страницу заказов")
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
    ResponseEntity<PageDto<OrderDto>> getPageOfOrders(
            @RequestParam(defaultValue = "0") @PositiveOrZero Integer offset,
            @RequestParam(defaultValue = "10") @jakarta.validation.constraints.Positive @Max(20) Integer limit,
            @RequestParam(defaultValue = "ID") OrderSortField sortBy,
            @RequestParam(defaultValue = "ASC") Sort.Direction sortOrder,
            @ModelAttribute @Valid OrderFilter filter,
            @RequestParam(required = false) String address);

    @Operation(
            summary = "Get order",
            description = "Позволяет получить информацию о заказе")
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
                    description = "Доступ запрещен: пользователь не является оператором или поставщиком"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ресур не найден: заказ не найден")
    })
    ResponseEntity<OrderDto> getOrder(
            @Parameter(
                    name = "Идентификатор заказа")
            @PathVariable ObjectId id);

    @Operation(
            summary = "Update order status",
            description = "Позволяет управлять жизненным циклом заказа, изменяя его статус")
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
                    description = "Доступ запрещен: пользователь не является оператором или поставщиком"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ресур не найден: заказ не найден")
    })
    ResponseEntity<OrderDto> updateOrderStatus(
            @Parameter(
                    name = "Идентификатор заказа")
            @PathVariable ObjectId id,
            @Parameter(
                    name = "Статус заказа",
                    description = "Новый статус заказа",
                    required = true)
            @RequestBody @NotNull OrderStatus status);
}
