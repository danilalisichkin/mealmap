import { OrderStatus } from "../enums/OrderStatus";
import { OrderItemDto } from "./OrderItemDto";

export interface OrderDto {
    id: string; // ObjectId as a string
    userId: string;
    deliveryAddress: string; // Assuming Address is a string
    status: OrderStatus;
    promoCode: string;
    paymentInfo: string; // Assuming PaymentInfo is a string
    orderedAt: string; // ISO date-time string
    readyAt: string; // ISO date-time string
    completedAt: string; // ISO date-time string
    items: OrderItemDto[];
}