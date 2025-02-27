package com.mealmap.orderservice.core.mapper;

import com.mealmap.orderservice.core.dto.order.OrderDto;
import com.mealmap.orderservice.document.Order;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {OrderItemMapper.class})
public interface OrderMapper {
    OrderDto docToDto(Order entity);

    List<OrderDto> docListToDtoList(List<Order> docList);

    default Page<OrderDto> docPageToDtoPage(Page<Order> docPage) {
        return new PageImpl<>(
                docListToDtoList(docPage.getContent()),
                docPage.getPageable(),
                docPage.getTotalElements());
    }
}
