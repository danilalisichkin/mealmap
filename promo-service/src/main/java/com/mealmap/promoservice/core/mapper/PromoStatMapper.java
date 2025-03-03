package com.mealmap.promoservice.core.mapper;

import com.mealmap.promoservice.core.dto.promo.stat.PromoStatCreationDto;
import com.mealmap.promoservice.core.dto.promo.stat.PromoStatDto;
import com.mealmap.promoservice.document.PromoStat;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PromoStatMapper {
    PromoStatDto docToDto(PromoStat doc);

    PromoStat dtoToDoc(PromoStatCreationDto dto);

    List<PromoStatDto> docListToDtoList(List<PromoStat> docList);

    default Page<PromoStatDto> documentPageToDtoPage(Page<PromoStat> docPage) {
        return new PageImpl<>(
                docListToDtoList(docPage.getContent()),
                docPage.getPageable(),
                docPage.getTotalElements());
    }
}
