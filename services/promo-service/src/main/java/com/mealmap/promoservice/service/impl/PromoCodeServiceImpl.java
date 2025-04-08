package com.mealmap.promoservice.service.impl;

import com.mealmap.promoservice.core.dto.promo.code.PromoCodeCreationDto;
import com.mealmap.promoservice.core.dto.promo.code.PromoCodeDto;
import com.mealmap.promoservice.core.dto.promo.code.PromoCodeUpdatingDto;
import com.mealmap.promoservice.core.enums.sort.PromoCodeSortField;
import com.mealmap.promoservice.core.mapper.PromoCodeMapper;
import com.mealmap.promoservice.document.PromoCode;
import com.mealmap.promoservice.repository.PromoCodeRepository;
import com.mealmap.promoservice.service.PromoCodeService;
import com.mealmap.promoservice.validator.PromoCodeValidator;
import com.mealmap.starters.exceptionstarter.exception.ResourceNotFoundException;
import com.mealmap.starters.paginationstarter.dto.PageDto;
import com.mealmap.starters.paginationstarter.mapper.PageMapper;
import com.mealmap.starters.paginationstarter.util.PageBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.mealmap.promoservice.core.message.ApplicationMessages.PROMO_CODE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class PromoCodeServiceImpl implements PromoCodeService {
    private final PromoCodeValidator promoCodeValidator;

    private final PromoCodeRepository promoCodeRepository;

    private final PromoCodeMapper promoCodeMapper;

    private final PageMapper pageMapper;

    @Override
    public PageDto<PromoCodeDto> getPageOfPromoCodes(
            Integer offset, Integer limit, PromoCodeSortField sortBy, Sort.Direction sortOrder) {

        PageRequest request = PageBuilder.buildPageRequest(offset, limit, sortBy.getValue(), sortOrder);

        return pageMapper.pageToPageDto(
                promoCodeMapper.documentPageToDtoPage(
                        promoCodeRepository.findAll(request)));
    }

    @Override
    public PromoCodeDto getPromoCode(String code) {
        PromoCode promoCode = getPromoCodeDoc(code);

        return promoCodeMapper.docToDto(promoCode);
    }

    @Override
    @Transactional
    public PromoCodeDto createPromoCode(PromoCodeCreationDto codeDto) {
        promoCodeValidator.validateCodeUniqueness(codeDto.getValue());

        PromoCode promoCodeToCreate = promoCodeMapper.dtoToDoc(codeDto);

        return promoCodeMapper.docToDto(
                promoCodeRepository.save(promoCodeToCreate));
    }

    @Override
    @Transactional
    public PromoCodeDto updatePromoCode(String code, PromoCodeUpdatingDto codeDto) {
        PromoCode promoCodeToUpdate = getPromoCodeDoc(code);

        promoCodeValidator.validatePromoCodeDateRange(
                promoCodeToUpdate.getStartDate(), codeDto.getEndDate());
        promoCodeMapper.updateDocFromDto(promoCodeToUpdate, codeDto);

        return promoCodeMapper.docToDto(
                promoCodeRepository.save(promoCodeToUpdate));
    }

    private PromoCode getPromoCodeDoc(String code) {
        return promoCodeRepository
                .findById(code)
                .orElseThrow(() -> new ResourceNotFoundException(PROMO_CODE_NOT_FOUND.formatted(code)));
    }
}
