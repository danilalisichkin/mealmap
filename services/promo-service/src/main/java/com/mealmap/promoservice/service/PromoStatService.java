package com.mealmap.promoservice.service;

import com.mealmap.promoservice.core.dto.page.PageDto;
import com.mealmap.promoservice.core.dto.promo.stat.PromoStatCreationDto;
import com.mealmap.promoservice.core.dto.promo.stat.PromoStatDto;
import com.mealmap.promoservice.core.enums.sort.PromoStatSortField;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;

public interface PromoStatService {
    PageDto<PromoStatDto> getPageOfPromoStats(
            Integer offset, Integer limit, PromoStatSortField sortBy, Sort.Direction sortOrder);

    PromoStatDto getPromoStat(ObjectId id);

    PromoStatDto createPromoStat(PromoStatCreationDto statDto);
}
