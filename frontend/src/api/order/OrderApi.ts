import ApiClient from "./client/ApiClient";
import { OrderDto } from "./dto/OrderDto";
import { OrderFilter } from "./dto/OrderFilter";
import { OrderStatus } from "./enums/OrderStatus";
import { OrderSortField } from "./enums/OrderSortField";
import { PageDto } from "../common/dto/PageDto";

export const OrderApi = {
  getOrders: async (
    offset: number = 0,
    limit: number = 10,
    sortBy: OrderSortField = OrderSortField.ID,
    sortOrder: "ASC" | "DESC" = "ASC",
    filter?: OrderFilter,
    address?: string
  ): Promise<PageDto<OrderDto>> => {
    const response = await ApiClient.get<PageDto<OrderDto>>("/orders", {
      params: {
        offset,
        limit,
        sortBy,
        sortOrder,
        ...filter,
        address,
      },
    });
    return response.data;
  },

  getOrderById: async (id: string): Promise<OrderDto> => {
    const response = await ApiClient.get<OrderDto>(`/orders/${id}`);
    return response.data;
  },

  updateOrderStatus: async (
    id: string,
    status: OrderStatus
  ): Promise<OrderDto> => {
    const response = await ApiClient.patch<OrderDto>(`/orders/${id}/status`, {
      status,
    });
    return response.data;
  },
};
