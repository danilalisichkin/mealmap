import ApiClient from "./client/ApiClient";
import { OrderDto } from "./dto/OrderDto";
import { OrderCreationDto } from "./dto/OrderCreationDto";
import { OrderFilter } from "./dto/OrderFilter";
import { OrderStatus } from "./enums/OrderStatus";
import { OrderSortField } from "./enums/OrderSortField";

export const OrderApi = {
    getOrders: async (
        offset: number = 0,
        limit: number = 10,
        sortBy: OrderSortField = OrderSortField.ID,
        sortOrder: "ASC" | "DESC" = "ASC",
        filter?: OrderFilter
    ): Promise<OrderDto[]> => {
        const response = await ApiClient.get<OrderDto[]>("/orders", {
            params: {
                offset,
                limit,
                sortBy,
                sortOrder,
                ...filter,
            },
        });
        return response.data;
    },

    getOrderById: async (id: string): Promise<OrderDto> => {
        const response = await ApiClient.get<OrderDto>(`/orders/${id}`);
        return response.data;
    },

    createOrder: async (userId: string, orderDto: OrderCreationDto): Promise<OrderDto> => {
        const response = await ApiClient.post<OrderDto>(`/users/${userId}/orders`, orderDto);
        return response.data;
    },

    updateOrderStatus: async (id: string, status: OrderStatus): Promise<OrderDto> => {
        const response = await ApiClient.patch<OrderDto>(`/orders/${id}/status`, { status });
        return response.data;
    },
};