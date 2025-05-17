package com.mealmap.productservice.service.impl;

import com.mealmap.productservice.core.dto.allergen.AllergenCreationDto;
import com.mealmap.productservice.core.dto.allergen.AllergenDto;
import com.mealmap.productservice.core.dto.allergen.AllergenUpdatingDto;
import com.mealmap.productservice.core.mapper.AllergenMapper;
import com.mealmap.productservice.entity.Allergen;
import com.mealmap.productservice.repository.AllergenRepository;
import com.mealmap.productservice.service.AllergenService;
import com.mealmap.productservice.validator.AllergenValidator;
import com.mealmap.starters.exceptionstarter.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

import static com.mealmap.productservice.core.message.ApplicationMessages.ALLERGEN_NOT_FOUND;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheResolver = "allergenCacheResolver")
public class AllergenServiceImpl implements AllergenService {
    private final AllergenValidator allergenValidator;

    private final AllergenMapper allergenMapper;

    private final AllergenRepository allergenRepository;

    @Override
    @Cacheable
    public List<AllergenDto> getAllAllergens() {
        List<Allergen> allergens = allergenRepository.findAll();

        return allergenMapper.entityListToDtoList(allergens);
    }

    @Override
    @Cacheable(key = "#ids.stream().sorted().toList().toString()")
    public List<AllergenDto> getAllergens(Set<Long> ids) {
        return allergenMapper.entityListToDtoList(
                allergenRepository.findAllById(ids));
    }

    @Override
    @Cacheable(key = "#id")
    public AllergenDto getAllergen(Long id) {
        Allergen allergen = getAllergenEntity(id);

        return allergenMapper.entityToDto(allergen);
    }

    @Override
    @Transactional
    @CachePut(key = "#result.id")
    public AllergenDto createAllergen(AllergenCreationDto allergenDto) {
        allergenValidator.validateNameUniqueness(allergenDto.getName());

        Allergen allergenToCreate = allergenMapper.dtoToEntity(allergenDto);

        return allergenMapper.entityToDto(
                allergenRepository.save(allergenToCreate));
    }

    @Override
    @Transactional
    @CachePut(key = "#result.id")
    public AllergenDto updateAllergen(Long id, AllergenUpdatingDto allergenDto) {
        Allergen allergenToUpdate = getAllergenEntity(id);

        if (!allergenToUpdate.getName().equals(allergenDto.getName())) {
            allergenValidator.validateNameUniqueness(allergenDto.getName());
        }

        allergenMapper.updateEntityFromDto(allergenToUpdate, allergenDto);

        return allergenMapper.entityToDto(
                allergenRepository.save(allergenToUpdate));
    }

    @Override
    @Transactional
    @CacheEvict(key = "#id")
    public void deleteAllergen(Long id) {
        allergenValidator.validateExistenceOfAllergenWithId(id);

        allergenRepository.deleteById(id);
    }

    private Allergen getAllergenEntity(Long id) {
        return allergenRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ALLERGEN_NOT_FOUND.formatted(id)));
    }
}
