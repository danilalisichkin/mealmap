import ApiClient from "./client/ApiClient";
import { CategoryDto } from "./dto/CategoryDto";
import { CategoryUpdatingDto } from "./dto/CategoryUpdatingDto";
import { CategoryTreeDto } from "./dto/CategoryTreeDto";
import { CategoryCreationDto } from "./dto/CategoryCreationDto";

export const CategoryApi = {
  getAllCategories: async (): Promise<CategoryDto[]> => {
    const response = await ApiClient.get<CategoryDto[]>("/categories/all");
    return response.data;
  },

  bulkGetCategories: async (ids: number[]): Promise<CategoryDto[]> => {
    const response = await ApiClient.get<CategoryDto[]>("/categories/bulk", {
      params: { ids },
      paramsSerializer: (params) => {
        return `ids=${params.ids.join(",")}`;
      },
    });
    return response.data;
  },

  getCategoryById: async (id: number): Promise<CategoryDto> => {
    const response = await ApiClient.get<CategoryDto>(`/categories/${id}`);
    return response.data;
  },

  getCategoryTree: async (id: number): Promise<CategoryTreeDto> => {
    const response = await ApiClient.get<CategoryTreeDto>(
      `/categories/${id}/tree`
    );
    return response.data;
  },

  createCategory: async (
    categoryDto: CategoryCreationDto
  ): Promise<CategoryDto> => {
    const response = await ApiClient.post<CategoryDto>(
      "/categories",
      categoryDto
    );
    return response.data;
  },

  updateCategory: async (
    id: number,
    categoryDto: CategoryUpdatingDto
  ): Promise<CategoryDto> => {
    const response = await ApiClient.put<CategoryDto>(
      `/categories/${id}`,
      categoryDto
    );
    return response.data;
  },

  deleteCategory: async (id: number): Promise<void> => {
    await ApiClient.delete(`/categories/${id}`);
  },
};
