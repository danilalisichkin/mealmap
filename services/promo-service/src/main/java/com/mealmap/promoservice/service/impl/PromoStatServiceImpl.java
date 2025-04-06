package com.mealmap.promoservice.service.impl;

import com.mealmap.promoservice.core.dto.page.PageDto;
import com.mealmap.promoservice.core.dto.promo.stat.PromoStatCreationDto;
import com.mealmap.promoservice.core.dto.promo.stat.PromoStatDto;
import com.mealmap.promoservice.core.enums.sort.PromoStatSortField;
import com.mealmap.promoservice.core.mapper.PageMapper;
import com.mealmap.promoservice.core.mapper.PromoStatMapper;
import com.mealmap.promoservice.document.PromoCode;
import com.mealmap.promoservice.document.PromoStat;
import com.mealmap.promoservice.repository.PromoCodeRepository;
import com.mealmap.promoservice.repository.PromoStatRepository;
import com.mealmap.promoservice.service.PromoStatService;
import com.mealmap.promoservice.util.PageBuilder;
import com.mealmap.promoservice.validator.PromoCodeValidator;
import com.mealmap.promoservice.validator.PromoStatValidator;
import com.mealmap.starters.exceptionstarter.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static com.mealmap.promoservice.core.message.ApplicationMessages.PROMO_CODE_NOT_FOUND;
import static com.mealmap.promoservice.core.message.ApplicationMessages.PROMO_STAT_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class PromoStatServiceImpl implements PromoStatService {
    private final PromoStatValidator promoStatValidator;

    private final PromoCodeValidator promoCodeValidator;

    private final PromoStatRepository promoStatRepository;

    private final PromoCodeRepository promoCodeRepository;

    private final PromoStatMapper promoStatMapper;

    private final PageMapper pageMapper;

    @Override
    public PageDto<PromoStatDto> getPageOfPromoStats(
            Integer offset, Integer limit, PromoStatSortField sortBy, Sort.Direction sortOrder) {

        PageRequest request = PageBuilder.buildPageRequest(offset, limit, sortBy.getValue(), sortOrder);

        return pageMapper.pageToPageDto(
                promoStatMapper.documentPageToDtoPage(
                        promoStatRepository.findAll(request)));
    }

    @Override
    public PromoStatDto getPromoStat(ObjectId id) {
        PromoStat promoStat = getPromoStatDoc(id);

        return promoStatMapper.docToDto(promoStat);
    }

    @Override
    @Transactional
    public PromoStatDto createPromoStat(PromoStatCreationDto statDto) {
        promoStatValidator.validateStatUniqueness(statDto.getPromoCode(), statDto.getUserId());

        PromoCode promoCode = getPromoCodeDoc(statDto.getPromoCode());
        promoCodeValidator.validatePromoCodeExpiration(promoCode.getEndDate());
        promoCodeValidator.validatePromoCodeApplicationLimit(promoCode.getLimit());

        PromoStat promoStatToCreate = promoStatMapper.dtoToDoc(statDto);
        promoStatToCreate.setCreatedAt(LocalDateTime.now());

        decreasePromoCodeApplicationLimit(promoCode);

        return promoStatMapper.docToDto(
                promoStatRepository.save(promoStatToCreate));
    }

    private PromoStat getPromoStatDoc(ObjectId id) {
        return promoStatRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(PROMO_STAT_NOT_FOUND.formatted(id.toHexString())));
    }

    private PromoCode getPromoCodeDoc(String code) {
        return promoCodeRepository
                .findById(code)
                .orElseThrow(() -> new ResourceNotFoundException(PROMO_CODE_NOT_FOUND.formatted(code)));
    }

    private void decreasePromoCodeApplicationLimit(PromoCode promoCode) {
        promoCode.setLimit(promoCode.getLimit() - 1);
        promoCodeRepository.save(promoCode);
    }
}
