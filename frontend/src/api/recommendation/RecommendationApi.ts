import ApiClient from "./client/ApiClient";
import { UserRecommendationDto } from "./dto/UserRecommendationDto";
import { UserRecommendationSortField } from "./enums/UserRecommendationSortField";

export const RecommendationApi = {
    getUserRecommendations: async (
        userId: string,
        offset: number = 0,
        limit: number = 10,
        sortBy: UserRecommendationSortField = UserRecommendationSortField.CREATED_AT,
        sortOrder: "ASC" | "DESC" = "ASC"
    ): Promise<UserRecommendationDto[]> => {
        const response = await ApiClient.get<UserRecommendationDto[]>(`/users/${userId}/recommendations`, {
            params: { offset, limit, sortBy, sortOrder },
        });
        return response.data;
    },

    getNewUserRecommendation: async (userId: string): Promise<UserRecommendationDto> => {
        const response = await ApiClient.get<UserRecommendationDto>(`/users/${userId}/recommendations/new`);
        return response.data;
    },
};