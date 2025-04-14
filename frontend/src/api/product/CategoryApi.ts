import ApiClient from "./client/ApiClient";
import { CategoryDto } from "./dto/CategoryDto";
import { CategoryCreatingDto } from "./dto/CategoryCreatingDto";
import { CategoryUpdatingDto } from "./dto/CategoryUpdatingDto";
import { CategoryTreeDto } from "./dto/CategoryTreeDto";
import { CategorySortField } from "./enums/CategorySortField";
import { PageDto } from "../common/dto/PageDto";

export const CategoryApi = {
  getCategories: async (
    offset: number = 0,
    limit: number = 10,
    sortBy: CategorySortField = CategorySortField.ID,
    sortOrder: "ASC" | "DESC" = "ASC",
    search?: string
  ): Promise<PageDto<CategoryDto>> => {
    const response = await ApiClient.get<PageDto<CategoryDto>>("/categories", {
      params: {
        offset,
        limit,
        sortBy,
        sortOrder,
        search,
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
    categoryDto: CategoryCreatingDto
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
