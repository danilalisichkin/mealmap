package com.mealmap.promoservice.core.mapper;

import com.mealmap.promoservice.core.dto.promo.code.PromoCodeCreationDto;
import com.mealmap.promoservice.core.dto.promo.code.PromoCodeDto;
import com.mealmap.promoservice.core.dto.promo.code.PromoCodeUpdatingDto;
import com.mealmap.promoservice.document.PromoCode;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PromoCodeMapper {
    PromoCodeDto docToDto(PromoCode doc);

    PromoCode dtoToDoc(PromoCodeCreationDto dto);

    void updateDocFromDto(@MappingTarget PromoCode doc, PromoCodeUpdatingDto dto);

    List<PromoCodeDto> docListToDtoList(List<PromoCode> docList);

    default Page<PromoCodeDto> documentPageToDtoPage(Page<PromoCode> docPage) {
        return new PageImpl<>(
                docListToDtoList(docPage.getContent()),
                docPage.getPageable(),
                docPage.getTotalElements());
    }
}
