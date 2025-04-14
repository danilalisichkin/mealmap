import ApiClient from "./client/ApiClient";
import { OrderDto } from "./dto/OrderDto";
import { OrderCreationDto } from "./dto/OrderCreationDto";
import { OrderStatus } from "./enums/OrderStatus";
import { OrderSortField } from "./enums/OrderSortField";
import { PageDto } from "../common/dto/PageDto";

export const UserOrderApi = {
  getUserOrders: async (
    userId: string,
    offset: number = 0,
    limit: number = 10,
    sortBy: OrderSortField = OrderSortField.ID,
    sortOrder: "ASC" | "DESC" = "ASC"
  ): Promise<PageDto<OrderDto>> => {
    const response = await ApiClient.get<PageDto<OrderDto>>(
      `/users/${userId}/orders`,
      {
        params: {
          offset,
          limit,
          sortBy,
          sortOrder,
        },
      }
    );
    return response.data;
  },

  createUserOrder: async (
    userId: string,
    orderDto: OrderCreationDto
  ): Promise<OrderDto> => {
    const response = await ApiClient.post<OrderDto>(
      `/users/${userId}/orders`,
      orderDto
    );
    return response.data;
  },

  updateUserOrderStatus: async (
    userId: string,
    id: string,
    status: OrderStatus
  ): Promise<OrderDto> => {
    const response = await ApiClient.patch<OrderDto>(
      `/users/${userId}/orders/${id}/status`,
      { status }
    );
    return response.data;
  },
};
