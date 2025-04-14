import ApiClient from "./client/ApiClient";
import { PromoCodeDto } from "./dto/PromoCodeDto";
import { PromoCodeCreationDto } from "./dto/PromoCodeCreationDto";
import { PromoCodeUpdatingDto } from "./dto/PromoCodeUpdatingDto";
import { PromoCodeSortField } from "./enums/PromoCodeSortField";

export const PromoCodeApi = {
  getPromoCodes: async (
    offset: number = 0,
    limit: number = 10,
    sortBy: PromoCodeSortField = PromoCodeSortField.VALUE,
    sortOrder: "ASC" | "DESC" = "ASC"
  ): Promise<PromoCodeDto[]> => {
    const response = await ApiClient.get<PromoCodeDto[]>("/promo-codes", {
      params: { offset, limit, sortBy, sortOrder },
    });
    return response.data;
  },

  getPromoCodeByValue: async (code: string): Promise<PromoCodeDto> => {
    const response = await ApiClient.get<PromoCodeDto>(`/promo-codes/${code}`);
    return response.data;
  },

  createPromoCode: async (
    promoCodeDto: PromoCodeCreationDto
  ): Promise<PromoCodeDto> => {
    const response = await ApiClient.post<PromoCodeDto>(
      "/promo-codes",
      promoCodeDto
    );
    return response.data;
  },

  updatePromoCode: async (
    code: string,
    promoCodeDto: PromoCodeUpdatingDto
  ): Promise<PromoCodeDto> => {
    const response = await ApiClient.put<PromoCodeDto>(
      `/promo-codes/${code}`,
      promoCodeDto
    );
    return response.data;
  },
};
