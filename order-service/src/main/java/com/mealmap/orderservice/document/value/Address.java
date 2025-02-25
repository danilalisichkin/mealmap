package com.mealmap.orderservice.document.value;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class Address {
    private String fullAddress;

    private List<Double> coordinates;
}
