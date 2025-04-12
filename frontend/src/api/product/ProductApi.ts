import ApiClient from "./client/ApiClient";
import { ProductDto } from "./dto/ProductDto";
import { ProductCreatingDto } from "./dto/ProductCreatingDto";
import { ProductUpdatingDto } from "./dto/ProductUpdatingDto";
import { ProductFilter } from "./dto/ProductFilter";
import { ProductSortField } from "./enums/ProductSortField";

export const ProductApi = {
    getProducts: async (
        offset: number = 0,
        limit: number = 10,
        sortBy: ProductSortField = ProductSortField.ID,
        sortOrder: "ASC" | "DESC" = "ASC",
        filter?: ProductFilter,
        search?: string
    ): Promise<ProductDto[]> => {
        const response = await ApiClient.get<ProductDto[]>("/products", {
            params: {
                offset,
                limit,
                sortBy,
                sortOrder,
                ...filter,
                search,
            },
        });
        return response.data;
    },

    getProductById: async (id: number): Promise<ProductDto> => {
        const response = await ApiClient.get<ProductDto>(`/products/${id}`);
        return response.data;
    },

    createProduct: async (productDto: ProductCreatingDto): Promise<ProductDto> => {
        const response = await ApiClient.post<ProductDto>("/products", productDto);
        return response.data;
    },

    updateProduct: async (id: number, productDto: ProductUpdatingDto): Promise<ProductDto> => {
        const response = await ApiClient.put<ProductDto>(`/products/${id}`, productDto);
        return response.data;
    },

    deleteProduct: async (id: number): Promise<void> => {
        await ApiClient.delete(`/products/${id}`);
    },
};