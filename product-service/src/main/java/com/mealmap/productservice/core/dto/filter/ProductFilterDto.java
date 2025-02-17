package com.mealmap.productservice.core.dto.filter;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

import static com.mealmap.productservice.validator.RangeValidator.isValidRange;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductFilterDto {
    @PositiveOrZero
    private Integer minPrice;

    @PositiveOrZero
    private Integer maxPrice;

    @PositiveOrZero
    private Integer minWeight;

    @Positive
    private Integer maxWeight;

    private Integer minCalories;

    private Integer maxCalories;

    @PositiveOrZero
    private Integer minProteins;

    @Positive
    private Integer maxProteins;

    @PositiveOrZero
    private Integer minFats;

    @Positive
    private Integer maxFats;

    @PositiveOrZero
    private Integer minCarbs;

    @Positive
    private Integer maxCarbs;

    @PositiveOrZero
    private Integer minFibers;

    @Positive
    private Integer maxFibers;

    @PositiveOrZero
    private Integer minSugars;

    @Positive
    private Integer maxSugars;

    private Integer supplierId;

    private Set<Long> categories;

    @AssertTrue(message = "minPrice must be less than maxPrice")
    private boolean isValidPriceRange() {
        return isValidRange(minPrice, maxPrice);
    }

    @AssertTrue(message = "minWeight must be less than maxWeight")
    private boolean isValidWeightRange() {
        return isValidRange(minWeight, maxWeight);
    }

    @AssertTrue(message = "minCalories must be less than maxCalories")
    private boolean isValidCaloriesRange() {
        return isValidRange(minCalories, maxCalories);
    }

    @AssertTrue(message = "minProteins must be less than maxProteins")
    private boolean isValidProteinsRange() {
        return isValidRange(minProteins, maxProteins);
    }

    @AssertTrue(message = "minFats must be less than maxFats")
    private boolean isValidFatsRange() {
        return isValidRange(minFats, maxFats);
    }

    @AssertTrue(message = "minCarbs must be less than maxCarbs")
    private boolean isValidCarbsRange() {
        return isValidRange(minCarbs, maxCarbs);
    }

    @AssertTrue(message = "minFibers must be less than maxFibers")
    private boolean isValidFibersRange() {
        return isValidRange(minFibers, maxFibers);
    }

    @AssertTrue(message = "minSugars must be less than maxSugars")
    private boolean isValidSugarsRange() {
        return isValidRange(minSugars, maxSugars);
    }
}
