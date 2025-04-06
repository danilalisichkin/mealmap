package com.mealmap.orderservice.doc.value;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @NotEmpty
    @Size(min = 2, max = 50)
    private String fullAddress;

    @NotEmpty
    @Size(min = 2, max = 2)
    private List<@NotNull Double> coordinates;
}
