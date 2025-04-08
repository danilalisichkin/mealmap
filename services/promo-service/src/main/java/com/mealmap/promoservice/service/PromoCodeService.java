package com.mealmap.promoservice.service;

import com.mealmap.promoservice.core.dto.promo.code.PromoCodeCreationDto;
import com.mealmap.promoservice.core.dto.promo.code.PromoCodeDto;
import com.mealmap.promoservice.core.dto.promo.code.PromoCodeUpdatingDto;
import com.mealmap.promoservice.core.enums.sort.PromoCodeSortField;
import com.mealmap.starters.paginationstarter.dto.PageDto;
import org.springframework.data.domain.Sort;

public interface PromoCodeService {
    PageDto<PromoCodeDto> getPageOfPromoCodes(
            Integer offset, Integer limit, PromoCodeSortField sortBy, Sort.Direction sortOrder);

    PromoCodeDto getPromoCode(String code);

    PromoCodeDto createPromoCode(PromoCodeCreationDto codeDto);

    PromoCodeDto updatePromoCode(String code, PromoCodeUpdatingDto codeDto);
}
