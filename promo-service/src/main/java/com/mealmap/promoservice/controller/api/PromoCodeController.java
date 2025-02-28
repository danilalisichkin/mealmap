package com.mealmap.promoservice.controller.api;

import com.mealmap.promoservice.core.dto.page.PageDto;
import com.mealmap.promoservice.core.dto.promo.code.PromoCodeCreationDto;
import com.mealmap.promoservice.core.dto.promo.code.PromoCodeDto;
import com.mealmap.promoservice.core.dto.promo.code.PromoCodeUpdatingDto;
import com.mealmap.promoservice.core.enums.sort.PromoCodeSortField;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/promo-codes")
public class PromoCodeController {
    @GetMapping
    public ResponseEntity<PageDto<PromoCodeDto>> getPageOfPromoCodes(
            @RequestParam(defaultValue = "0") Integer offset,
            @RequestParam(defaultValue = "10") Integer limit,
            @RequestParam(defaultValue = "value") PromoCodeSortField sortBy,
            @RequestParam(defaultValue = "ASC") Sort.Direction sortOrder) {

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{code}")
    public ResponseEntity<PromoCodeDto> getPromoCode(@PathVariable String code) {

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping
    public ResponseEntity<PromoCodeDto> createPromoCode(@RequestBody PromoCodeCreationDto codeDto) {

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{code}")
    public ResponseEntity<PromoCodeDto> updatePromoCode(
            @PathVariable String code,
            @RequestBody PromoCodeUpdatingDto codeDto) {

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
