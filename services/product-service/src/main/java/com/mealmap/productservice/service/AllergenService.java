package com.mealmap.productservice.service;

import com.mealmap.productservice.core.dto.allergen.AllergenCreationDto;
import com.mealmap.productservice.core.dto.allergen.AllergenDto;
import com.mealmap.productservice.core.dto.allergen.AllergenUpdatingDto;

import java.util.List;
import java.util.Set;

public interface AllergenService {
    List<AllergenDto> getAllAllergens();

    List<AllergenDto> getAllergens(Set<Long> ids);

    AllergenDto getAllergen(Long id);

    AllergenDto createAllergen(AllergenCreationDto allergenDto);

    AllergenDto updateAllergen(Long id, AllergenUpdatingDto allergenDto);

    void deleteAllergen(Long id);
}
