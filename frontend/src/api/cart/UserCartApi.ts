import ApiClient from "./client/ApiClient";
import { CartDto } from "./dto/CartDto";
import { CartItemAddingDto } from "./dto/CartItemAddingDto";
import { CartItemDto } from "./dto/CartItemDto";

export const CartApi = {
  getCart: async (userId: string): Promise<CartDto> => {
    const response = await ApiClient.get<CartDto>(`/users/${userId}/cart`);
    return response.data;
  },

  addItemToCart: async (
    userId: string,
    itemDto: CartItemAddingDto
  ): Promise<CartDto> => {
    const response = await ApiClient.post<CartDto>(
      `/users/${userId}/cart/items`,
      itemDto
    );
    return response.data;
  },

  changeCartItemQuantity: async (
    userId: string,
    itemId: number,
    quantity: number
  ): Promise<CartItemDto> => {
    const response = await ApiClient.patch<CartItemDto>(
      `/users/${userId}/cart/items/${itemId}/quantity`,
      { quantity }
    );
    return response.data;
  },

  deleteItemFromCart: async (userId: string, itemId: number): Promise<void> => {
    await ApiClient.delete(`/users/${userId}/cart/items/${itemId}`);
  },

  clearCart: async (userId: string): Promise<void> => {
    await ApiClient.delete(`/users/${userId}/cart`);
  },
};
