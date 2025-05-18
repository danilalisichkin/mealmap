import ApiClient from "./client/ApiClient";
import { AllergenDto } from "./dto/AllergenDto";
import { AllergenAddingDto } from "./dto/AllergenAddingDto";
import { AllergenUpdatingDto } from "./dto/AllergenUpdatingDto";

export const AllergenApi = {
  getAllAllergens: async (): Promise<AllergenDto[]> => {
    const response = await ApiClient.get<AllergenDto[]>("/allergens/all");
    return response.data;
  },

  bulkGetAllergens: async (ids: number[]): Promise<AllergenDto[]> => {
    const response = await ApiClient.get<AllergenDto[]>("/allergens/bulk", {
      params: { ids },
      paramsSerializer: (params) => {
        return `ids=${params.ids.join(",")}`;
      },
    });
    return response.data;
  },

  getAllergenById: async (id: number): Promise<AllergenDto> => {
    const response = await ApiClient.get<AllergenDto>(`/allergens/${id}`);
    return response.data;
  },

  createAllergen: async (
    allergenDto: AllergenAddingDto
  ): Promise<AllergenDto> => {
    const response = await ApiClient.post<AllergenDto>(
      "/allergens",
      allergenDto
    );
    return response.data;
  },

  updateAllergen: async (
    id: number,
    allergenDto: AllergenUpdatingDto
  ): Promise<AllergenDto> => {
    const response = await ApiClient.put<AllergenDto>(
      `/allergens/${id}`,
      allergenDto
    );
    return response.data;
  },

  deleteAllergen: async (id: number): Promise<void> => {
    await ApiClient.delete(`/allergens/${id}`);
  },
};
