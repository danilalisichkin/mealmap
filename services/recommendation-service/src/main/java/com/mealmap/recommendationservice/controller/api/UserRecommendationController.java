package com.mealmap.recommendationservice.controller.api;

import com.mealmap.recommendationservice.core.dto.UserRecommendationDto;
import com.mealmap.recommendationservice.core.dto.page.PageDto;
import com.mealmap.recommendationservice.core.enums.sort.UserRecommendationSortField;
import com.mealmap.recommendationservice.service.RecommendationService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class UserRecommendationController {
    private final RecommendationService recommendationService;

    @GetMapping("/{userId}/recommendations")
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

    @GetMapping("/{userId}/recommendations/new")
    public ResponseEntity<UserRecommendationDto> getNewUserRecommendation(@PathVariable UUID userId) {
        UserRecommendationDto recommendation = recommendationService.getNewUserRecommendation(userId);

        return ResponseEntity.status(HttpStatus.OK).body(recommendation);
    }
}
