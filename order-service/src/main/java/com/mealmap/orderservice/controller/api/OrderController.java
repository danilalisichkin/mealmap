package com.mealmap.orderservice.controller.api;

import com.mealmap.orderservice.core.dto.filter.OrderFilterDto;
import com.mealmap.orderservice.core.dto.order.OrderDto;
import com.mealmap.orderservice.core.dto.page.PageDto;
import com.mealmap.orderservice.core.enums.OrderStatus;
import com.mealmap.orderservice.core.enums.sort.OrderSortField;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    @GetMapping
    public ResponseEntity<PageDto<OrderDto>> getPageOfOrders(
            @RequestParam(defaultValue = "0") Integer offset,
            @RequestParam(defaultValue = "10") Integer limit,
            @RequestParam(defaultValue = "ID") OrderSortField sortBy,
            @RequestParam(defaultValue = "ASC") Sort.Direction sortOrder,
            @ModelAttribute OrderFilterDto filter,
            @RequestParam(required = false) String address) {

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable Long id) {

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<OrderDto> updateOrderStatus(
            @PathVariable Long id,
            @RequestBody OrderStatus status) {

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
