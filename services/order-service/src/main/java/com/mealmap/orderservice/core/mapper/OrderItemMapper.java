package com.mealmap.orderservice.core.mapper;

import com.mealmap.orderservice.core.dto.order.OrderItemCreationDto;
import com.mealmap.orderservice.core.dto.order.OrderItemDto;
import com.mealmap.orderservice.doc.value.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderItemMapper {
    OrderItemDto docToDto(OrderItem doc);

    @Mapping(target = "priceWhenOrdered", ignore = true)
    OrderItem dtoToDoc(OrderItemCreationDto dto);

    List<OrderItemDto> docListToDtoList(List<OrderItem> docList);
}
