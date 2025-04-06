package com.mealmap.recommendationservice.service;

import java.util.UUID;

public interface PromptBuildingService {
    String buildPromptUserMessage(UUID userId);
}
