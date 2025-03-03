package com.mealmap.orderservice.core.mapper;

import com.mealmap.orderservice.core.dto.price.PriceCalculationRequest;
import com.mealmap.orderservice.core.dto.price.PriceRecalculationRequest;
import com.mealmap.orderservice.document.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PriceMapper {
    @Mapping(target = "orderItems", source = "items")
    PriceCalculationRequest orderToPriceCalculationRequest(Order order);

    @Mapping(target = "promoCode", source = "promoCode")
    @Mapping(target = "basePrice", source = "paymentInfo.totalAmount")
    PriceRecalculationRequest orderToPriceRecalculationRequest(Order order);
}
