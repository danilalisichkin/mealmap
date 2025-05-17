package com.mealmap.productservice.core.mapper;

import com.mealmap.productservice.core.dto.category.CategoryCreationDto;
import com.mealmap.productservice.core.dto.category.CategoryDto;
import com.mealmap.productservice.core.dto.category.CategoryShortInfo;
import com.mealmap.productservice.core.dto.category.CategorySimpleDto;
import com.mealmap.productservice.core.dto.category.CategoryTreeDto;
import com.mealmap.productservice.core.dto.category.CategoryUpdatingDto;
import com.mealmap.productservice.document.CategoryDoc;
import com.mealmap.productservice.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {
    CategoryDoc entityToDocument(Category entity);

    CategoryDto documentToDto(CategoryDoc document);

    CategorySimpleDto documentToSimpleDto(CategoryDoc document);

    CategoryDto entityToDto(Category entity);

    CategoryShortInfo entityToShortInfo(Category entity);

    CategoryTreeDto entityToTreeDto(Category entity);

    @Mapping(target = "parent", ignore = true)
    @Mapping(target = "children", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "products", ignore = true)
    Category dtoToEntity(CategoryCreationDto dto);

    @Mapping(target = "parent", ignore = true)
    @Mapping(target = "children", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "products", ignore = true)
    void updateEntityFromDto(@MappingTarget Category entity, CategoryUpdatingDto dto);

    List<CategoryDto> docListToDtoList(List<CategoryDoc> docList);

    List<CategoryDto> entityListToDtoList(List<Category> entityList);
}
