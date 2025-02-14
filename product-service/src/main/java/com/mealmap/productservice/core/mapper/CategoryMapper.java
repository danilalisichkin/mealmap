package com.mealmap.productservice.core.mapper;

import com.mealmap.productservice.core.dto.category.CategoryCreatingDto;
import com.mealmap.productservice.core.dto.category.CategoryDto;
import com.mealmap.productservice.core.dto.category.CategoryShortInfo;
import com.mealmap.productservice.core.dto.category.CategoryUpdatingDto;
import com.mealmap.productservice.document.CategoryDoc;
import com.mealmap.productservice.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {
    CategoryDoc entityToDocument(Category entity);

    CategoryDto documentToDto(CategoryDoc document);

    CategoryShortInfo documentToShortInfo(CategoryDoc document);

    @Mapping(target = "parent", ignore = true)
    @Mapping(target = "children", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "products", ignore = true)
    Category dtoToEntity(CategoryCreatingDto dto);

    @Mapping(target = "parent", ignore = true)
    @Mapping(target = "children", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "products", ignore = true)
    void updateEntityFromDto(@MappingTarget Category entity, CategoryUpdatingDto dto);
}
