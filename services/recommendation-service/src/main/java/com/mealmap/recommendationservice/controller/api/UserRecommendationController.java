package com.mealmap.recommendationservice.controller.api;

import com.mealmap.recommendationservice.controller.doc.UserRecommendationControllerDoc;
import com.mealmap.recommendationservice.core.dto.UserRecommendationDto;
import com.mealmap.recommendationservice.core.enums.sort.UserRecommendationSortField;
import com.mealmap.recommendationservice.service.RecommendationService;
import com.mealmap.starters.paginationstarter.dto.PageDto;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserRecommendationController implements UserRecommendationControllerDoc {
    private final RecommendationService recommendationService;

    @Override
    @GetMapping("/{userId}/recommendations")
    @PreAuthorize("hasUserId(#userId) and hasRole('CUSTOMER')")
    public ResponseEntity<PageDto<UserRecommendationDto>> getUserRecommendations(
            @PathVariable UUID userId,
            @RequestParam(defaultValue = "0") @PositiveOrZero Integer offset,
            @RequestParam(defaultValue = "10") @Positive @Max(20) Integer limit,
            @RequestParam(defaultValue = "ID") UserRecommendationSortField sortBy,
            @RequestParam(defaultValue = "ASC") Sort.Direction sortOrder) {

        PageDto<UserRecommendationDto> page =
                recommendationService.getUserRecommendations(userId, offset, limit, sortBy, sortOrder);

        return ResponseEntity.status(HttpStatus.OK).body(page);
    }

    @Override
    @GetMapping("/{userId}/recommendations/new")
    @PreAuthorize("hasUserId(#userId) and hasRole('CUSTOMER')")
    public ResponseEntity<UserRecommendationDto> getNewUserRecommendation(@PathVariable UUID userId) {
        UserRecommendationDto recommendation = recommendationService.getNewUserRecommendation(userId);

        return ResponseEntity.status(HttpStatus.OK).body(recommendation);
    }
}
