package com.mealmap.promoservice.controller.api;

import com.mealmap.promoservice.core.dto.promo.code.PromoCodeCreationDto;
import com.mealmap.promoservice.core.dto.promo.code.PromoCodeDto;
import com.mealmap.promoservice.core.dto.promo.code.PromoCodeUpdatingDto;
import com.mealmap.promoservice.core.enums.sort.PromoCodeSortField;
import com.mealmap.promoservice.service.PromoCodeService;
import com.mealmap.starters.paginationstarter.dto.PageDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/promo-codes")
public class PromoCodeController {
    private final PromoCodeService promoCodeService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PageDto<PromoCodeDto>> getPageOfPromoCodes(
            @RequestParam(defaultValue = "0") @PositiveOrZero Integer offset,
            @RequestParam(defaultValue = "10") @Positive @Max(20) Integer limit,
            @RequestParam(defaultValue = "VALUE") PromoCodeSortField sortBy,
            @RequestParam(defaultValue = "ASC") Sort.Direction sortOrder) {

        PageDto<PromoCodeDto> page = promoCodeService.getPageOfPromoCodes(offset, limit, sortBy, sortOrder);

        return ResponseEntity.status(HttpStatus.OK).body(page);
    }

    @GetMapping("/{code}")
    @PreAuthorize("isAuthenticated()") //TODO: allow order-service use this method
    public ResponseEntity<PromoCodeDto> getPromoCode(@PathVariable @Size(min = 2, max = 20) String code) {
        PromoCodeDto promoCode = promoCodeService.getPromoCode(code);

        return ResponseEntity.status(HttpStatus.OK).body(promoCode);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PromoCodeDto> createPromoCode(@RequestBody @Valid PromoCodeCreationDto codeDto) {
        PromoCodeDto promoCode = promoCodeService.createPromoCode(codeDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(promoCode);
    }

    @PutMapping("/{code}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PromoCodeDto> updatePromoCode(
            @PathVariable @Size(min = 2, max = 20) String code,
            @RequestBody @Valid PromoCodeUpdatingDto codeDto) {

        PromoCodeDto promoCode = promoCodeService.updatePromoCode(code, codeDto);

        return ResponseEntity.status(HttpStatus.OK).body(promoCode);
    }
}
