package com.mealmap.orderservice.controller.api;

import com.mealmap.orderservice.controller.doc.UserOrderControllerDoc;
import com.mealmap.orderservice.core.dto.order.OrderCreationDto;
import com.mealmap.orderservice.core.dto.order.OrderDto;
import com.mealmap.orderservice.core.enums.OrderStatus;
import com.mealmap.orderservice.core.enums.sort.OrderSortField;
import com.mealmap.orderservice.service.UserOrderService;
import com.mealmap.starters.paginationstarter.dto.PageDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserOrderController implements UserOrderControllerDoc {
    private final UserOrderService userOrderService;

    @Override
    @GetMapping("/{userId}/orders")
    @PreAuthorize("(hasUserId(#userId) and hasRole('CUSTOMER')) " +
            "or (hasRole('OPERATOR') and hasRole('ADMIN')) " +
            "or (isApplicationService() and hasRole('RECOMMENDATION_SERVICE'))")
    public ResponseEntity<PageDto<OrderDto>> getPageOfUserOrders(
            @PathVariable @UUID String userId,
            @RequestParam(defaultValue = "0") @PositiveOrZero Integer offset,
            @RequestParam(defaultValue = "10") @Positive @Max(20) Integer limit,
            @RequestParam(defaultValue = "ID") OrderSortField sortBy,
            @RequestParam(defaultValue = "ASC") Sort.Direction sortOrder) {

        PageDto<OrderDto> page = userOrderService.getPageOfUserOrders(userId, offset, limit, sortBy, sortOrder);

        return ResponseEntity.status(HttpStatus.OK).body(page);
    }

    @Override
    @PostMapping("/{userId}/orders")
    @PreAuthorize("hasUserId(#userId) and hasRole('CUSTOMER')")
    public ResponseEntity<OrderDto> createOrder(
            @PathVariable @UUID String userId,
            @RequestBody @Valid OrderCreationDto orderDto) {

        OrderDto order = userOrderService.createOrder(userId, orderDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @Override
    @PatchMapping("/{userId}/orders/{id}/status")
    @PreAuthorize("hasUserId(#userId) and hasRole('CUSTOMER')")
    public ResponseEntity<OrderDto> updateOrderStatus(
            @PathVariable @UUID String userId,
            @PathVariable ObjectId id,
            @RequestBody @NotNull OrderStatus status) {

        OrderDto order = userOrderService.updateOrderStatus(userId, id, status);

        return ResponseEntity.status(HttpStatus.OK).body(order);
    }
}
