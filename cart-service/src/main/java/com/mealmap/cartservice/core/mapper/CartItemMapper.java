package com.mealmap.cartservice.core.mapper;

import com.mealmap.cartservice.core.dto.cart.CartItemAddingDto;
import com.mealmap.cartservice.core.dto.cart.CartItemDto;
import com.mealmap.cartservice.entity.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CartItemMapper {
    CartItemDto entityToDto(CartItem entity);

    CartItem dtoToEntity(CartItemAddingDto dto);

    List<CartItemDto> entityListToDtoList(List<CartItem> entityList);
}
