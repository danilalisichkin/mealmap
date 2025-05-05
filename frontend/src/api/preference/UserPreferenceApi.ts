import ApiClient from "./client/ApiClient";
import { UserPreferencesDto } from "./dto/UserPreferencesDto";
import { ProductPreferenceDto } from "./dto/ProductPreferenceDto";
import { CategoryPreferenceDto } from "./dto/CategoryPreferenceDto";
import { ProductPreferenceCreationDto } from "./dto/ProductPreferenceCreationDto";
import { CategoryPreferenceCreationDto } from "./dto/CategoryPreferenceCreationDto";
import { PreferenceType } from "./enums/PreferenceType";

export const PreferenceApi = {
  getAllPreferences: async (userId: string): Promise<UserPreferencesDto> => {
    const response = await ApiClient.get<UserPreferencesDto>(
      `/users/${userId}/preferences`
    );
    return response.data;
  },

  getProductPreferences: async (
    userId: string,
    preferenceType?: PreferenceType
  ): Promise<ProductPreferenceDto[]> => {
    const response = await ApiClient.get<ProductPreferenceDto[]>(
      `/users/${userId}/preferences/products`,
      {
        params: { preferenceType },
      }
    );
    return response.data;
  },

  getCategoryPreferences: async (
    userId: string,
    preferenceType?: PreferenceType
  ): Promise<CategoryPreferenceDto[]> => {
    const response = await ApiClient.get<CategoryPreferenceDto[]>(
      `/users/${userId}/preferences/categories`,
      {
        params: { preferenceType },
      }
    );
    return response.data;
  },

  getProductPreference: async (
    userId: string,
    productId: number
  ): Promise<ProductPreferenceDto> => {
    const response = await ApiClient.get<ProductPreferenceDto>(
      `/users/${userId}/preferences/products/${productId}`
    );
    return response.data;
  },

  getCategoryPreference: async (
    userId: string,
    categoryId: number
  ): Promise<CategoryPreferenceDto> => {
    const response = await ApiClient.get<CategoryPreferenceDto>(
      `/users/${userId}/preferences/categories/${categoryId}`
    );
    return response.data;
  },

  addProductPreference: async (
    userId: string,
    productPreference: ProductPreferenceCreationDto
  ): Promise<ProductPreferenceDto> => {
    const response = await ApiClient.post<ProductPreferenceDto>(
      `/users/${userId}/preferences/products`,
      productPreference
    );
    return response.data;
  },

  addCategoryPreference: async (
    userId: string,
    categoryPreference: CategoryPreferenceCreationDto
  ): Promise<CategoryPreferenceDto> => {
    const response = await ApiClient.post<CategoryPreferenceDto>(
      `/users/${userId}/preferences/categories`,
      categoryPreference
    );
    return response.data;
  },

  removeProductPreference: async (
    userId: string,
    id: number
  ): Promise<void> => {
    await ApiClient.delete(`/users/${userId}/preferences/products/${id}`);
  },

  removeCategoryPreference: async (
    userId: string,
    id: number
  ): Promise<void> => {
    await ApiClient.delete(`/users/${userId}/preferences/categories/${id}`);
  },
};
