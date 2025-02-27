package com.mealmap.orderservice.document.value;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class Address {
    @NotEmpty
    @Size(min = 2, max = 50)
    private String fullAddress;

    @NotEmpty
    @Size(min = 2, max = 2)
    private List<Double> coordinates;
}
