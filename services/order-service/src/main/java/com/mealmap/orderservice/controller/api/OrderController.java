package com.mealmap.orderservice.controller.api;

import com.mealmap.orderservice.controller.doc.OrderControllerDoc;
import com.mealmap.orderservice.core.dto.filter.OrderFilter;
import com.mealmap.orderservice.core.dto.order.OrderDto;
import com.mealmap.orderservice.core.enums.OrderStatus;
import com.mealmap.orderservice.core.enums.sort.OrderSortField;
import com.mealmap.orderservice.service.OrderService;
import com.mealmap.starters.paginationstarter.dto.PageDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController implements OrderControllerDoc {
    private final OrderService orderService;

    @Override
    @GetMapping
    @PreAuthorize("hasRole('OPERATOR') and hasRole('ADMIN')")
    public ResponseEntity<PageDto<OrderDto>> getPageOfOrders(
            @RequestParam(defaultValue = "0") @PositiveOrZero Integer offset,
            @RequestParam(defaultValue = "10") @Positive @Max(20) Integer limit,
            @RequestParam(defaultValue = "ID") OrderSortField sortBy,
            @RequestParam(defaultValue = "ASC") Sort.Direction sortOrder,
            @ModelAttribute @Valid OrderFilter filter,
            @RequestParam(required = false) String address) {

        PageDto<OrderDto> page = orderService.getPageOfOrders(
                offset, limit, sortBy, sortOrder, filter, address);

        return ResponseEntity.status(HttpStatus.OK).body(page);
    }

    @Override
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('SUPPLIER') or (hasRole('OPERATOR') and hasRole('ADMIN'))")
    public ResponseEntity<OrderDto> getOrder(@PathVariable ObjectId id) {
        OrderDto order = orderService.getOrder(id);

        return ResponseEntity.status(HttpStatus.OK).body(order);
    }

    @Override
    @PatchMapping("/{id}/status")
    @PreAuthorize("hasRole('SUPPLIER') or (hasRole('OPERATOR') and hasRole('ADMIN'))")
    public ResponseEntity<OrderDto> updateOrderStatus(
            @PathVariable ObjectId id,
            @RequestBody @NotNull OrderStatus status) {

        OrderDto order = orderService.updateOrderStatus(id, status);

        return ResponseEntity.status(HttpStatus.OK).body(order);
    }
}
