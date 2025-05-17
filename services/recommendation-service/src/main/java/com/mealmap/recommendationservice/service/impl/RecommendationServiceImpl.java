package com.mealmap.recommendationservice.service.impl;

import com.mealmap.recommendationservice.ai.dto.ChatRecommendations;
import com.mealmap.recommendationservice.core.dto.UserRecommendationDto;
import com.mealmap.recommendationservice.core.enums.sort.UserRecommendationSortField;
import com.mealmap.recommendationservice.core.mapper.UserRecommendationMapper;
import com.mealmap.recommendationservice.document.UserRecommendation;
import com.mealmap.recommendationservice.repository.UserRecommendationRepository;
import com.mealmap.recommendationservice.service.PromptBuildingService;
import com.mealmap.recommendationservice.service.RecommendationService;
import com.mealmap.starters.paginationstarter.dto.PageDto;
import com.mealmap.starters.paginationstarter.mapper.PageMapper;
import com.mealmap.starters.paginationstarter.util.PageBuilder;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecommendationServiceImpl implements RecommendationService {
    private final ChatClient chatClient;

    private final PromptBuildingService promptBuildingService;

    private final PageMapper pageMapper;

    private final UserRecommendationMapper userRecommendationMapper;

    private final UserRecommendationRepository userRecommendationRepository;

    @Override
    public PageDto<UserRecommendationDto> getUserRecommendations(
            UUID userId, Integer offset, Integer limit, UserRecommendationSortField sortBy,
            Sort.Direction sortOrder) {

        PageRequest request = PageBuilder.buildPageRequest(offset, limit, sortBy.getValue(), sortOrder);

        Page<UserRecommendation> recommendations
                = userRecommendationRepository.findAllByUserId(userId.toString(), request);

        return pageMapper.pageToPageDto(
                userRecommendationMapper.docPageToDtoPage(recommendations));
    }

    @Override
    @SneakyThrows
    @Transactional
    public UserRecommendationDto getNewUserRecommendation(UUID userId) {
        var prompt = promptBuildingService.buildPromptUserMessage(userId);

        log.info(prompt);

        var response = chatClient
                .prompt(prompt)
                .user(userText -> userText
                        .text("{data}")
                        .param("data", prompt))
                .call()
                .entity(ChatRecommendations.class);

        UserRecommendation newRecommendation = userRecommendationMapper.chatRecommendationsToDoc(response);
        newRecommendation.setUserId(userId.toString());
        newRecommendation.setCreatedAt(LocalDateTime.now());

        return userRecommendationMapper.docToDto(
                userRecommendationRepository.save(newRecommendation));
    }
}
