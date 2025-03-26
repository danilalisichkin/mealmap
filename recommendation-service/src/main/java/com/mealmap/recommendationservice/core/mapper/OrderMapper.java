package com.mealmap.recommendationservice.core.mapper;

import com.mealmap.recommendationservice.client.dto.order.OrderDto;
import com.mealmap.recommendationservice.client.dto.order.OrderItemDto;
import com.mealmap.recommendationservice.core.model.order.Order;
import com.mealmap.recommendationservice.core.model.order.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {
    Order dtoToModel(OrderDto dto);

    OrderItem orderItemDtoToModel(OrderItemDto dto);

    List<OrderItem> orderItemDtoListToModelList(List<OrderItemDto> dtoList);
}
