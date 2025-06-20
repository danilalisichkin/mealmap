package com.mealmap.promoservice.controller.api;

import com.mealmap.promoservice.controller.doc.PromoStatControllerDoc;
import com.mealmap.promoservice.core.dto.promo.stat.PromoStatCreationDto;
import com.mealmap.promoservice.core.dto.promo.stat.PromoStatDto;
import com.mealmap.promoservice.core.enums.sort.PromoStatSortField;
import com.mealmap.promoservice.service.PromoStatService;
import com.mealmap.starters.paginationstarter.dto.PageDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/promo-stats")
public class PromoStatController implements PromoStatControllerDoc {
    private final PromoStatService promoStatService;

    @Override
    @GetMapping
    @PreAuthorize("hasRole('OPERATOR') and hasRole('ADMIN')")
    public ResponseEntity<PageDto<PromoStatDto>> getPageOfPromoStats(
            @RequestParam(defaultValue = "0") @PositiveOrZero Integer offset,
            @RequestParam(defaultValue = "10") @Positive @Max(20) Integer limit,
            @RequestParam(defaultValue = "ID") PromoStatSortField sortBy,
            @RequestParam(defaultValue = "ASC") Sort.Direction sortOrder) {

        PageDto<PromoStatDto> page = promoStatService.getPageOfPromoStats(offset, limit, sortBy, sortOrder);

        return ResponseEntity.status(HttpStatus.OK).body(page);
    }

    @Override
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('OPERATOR') and hasRole('ADMIN')")
    public ResponseEntity<PromoStatDto> getPromoStat(@PathVariable ObjectId id) {
        PromoStatDto promoStat = promoStatService.getPromoStat(id);

        return ResponseEntity.status(HttpStatus.OK).body(promoStat);
    }

    @Override
    @PostMapping
    @PreAuthorize("(hasUserId(#statDto.userId) and hasRole('CUSTOMER')) " +
            "or (isApplicationService() and hasRole('ORDER_SERVICE'))")
    public ResponseEntity<PromoStatDto> createPromoStat(
            @RequestBody @Valid PromoStatCreationDto statDto) {

        PromoStatDto promoStat = promoStatService.createPromoStat(statDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(promoStat);
    }
}
