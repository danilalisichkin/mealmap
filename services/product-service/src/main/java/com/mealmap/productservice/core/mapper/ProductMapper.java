package com.mealmap.productservice.core.mapper;

import com.mealmap.productservice.core.dto.product.ProductCreationDto;
import com.mealmap.productservice.core.dto.product.ProductDto;
import com.mealmap.productservice.core.dto.product.ProductUpdatingDto;
import com.mealmap.productservice.document.ProductDoc;
import com.mealmap.productservice.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {
                CategoryMapper.class,
                NutrientMapper.class,
                AllergenMapper.class,
                NewnessMapper.class})
public interface ProductMapper {
    @Mapping(target = "isNew", source = "createdAt")
    ProductDoc entityToDocument(Product entity);

    ProductDto documentToDto(ProductDoc document);

    @Mapping(target = "isNew", source = "createdAt")
    ProductDto entityToDto(Product entity);

    @Mapping(target = "categories", ignore = true)
    @Mapping(target = "allergens", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Product dtoToEntity(ProductCreationDto dto);

    @Mapping(target = "categories", ignore = true)
    @Mapping(target = "allergens", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void updateEntityFromDto(@MappingTarget Product entity, ProductUpdatingDto dto);

    List<ProductDto> docListToDtoList(List<ProductDoc> docList);

    List<ProductDto> entityListToDtoList(List<Product> entityList);

    default Page<ProductDto> docPageToDtoPage(Page<ProductDoc> docPage) {
        return new PageImpl<>(
                docListToDtoList(docPage.getContent()),
                docPage.getPageable(),
                docPage.getTotalElements());
    }
}
