package com.mealmap.cartservice.core.mapper;

import com.mealmap.cartservice.core.dto.cart.CartDto;
import com.mealmap.cartservice.entity.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {CartItemMapper.class})
public interface CartMapper {
    CartDto entityToDto(Cart entity);
}
