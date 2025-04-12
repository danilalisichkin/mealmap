import ApiClient from "./client/ApiClient";
import { PromoStatDto } from "./dto/PromoStatDto";
import { PromoStatCreationDto } from "./dto/PromoStatCreationDto";
import { PromoStatSortField } from "./enums/PromoStatSortField";

export const PromoStatApi = {
    getPromoStats: async (
        offset: number = 0,
        limit: number = 10,
        sortBy: PromoStatSortField = PromoStatSortField.ID,
        sortOrder: "ASC" | "DESC" = "ASC"
    ): Promise<PromoStatDto[]> => {
        const response = await ApiClient.get<PromoStatDto[]>("/promo-stats", {
            params: { offset, limit, sortBy, sortOrder },
        });
        return response.data;
    },

    getPromoStatById: async (id: string): Promise<PromoStatDto> => {
        const response = await ApiClient.get<PromoStatDto>(`/promo-stats/${id}`);
        return response.data;
    },

    createPromoStat: async (promoStatDto: PromoStatCreationDto): Promise<PromoStatDto> => {
        const response = await ApiClient.post<PromoStatDto>("/promo-stats", promoStatDto);
        return response.data;
    },
};