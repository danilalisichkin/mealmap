package com.mealmap.orderservice.core.mapper;

import com.mealmap.orderservice.core.dto.order.OrderItemDto;
import com.mealmap.orderservice.document.value.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderItemMapper {
    OrderItemDto docToDto(OrderItem doc);

    List<OrderItemDto> docListToDtoList(List<OrderItem> docList);
}
