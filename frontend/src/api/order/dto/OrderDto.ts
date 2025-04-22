import { Address } from "../../common/dto/Address";
import { OrderStatus } from "../enums/OrderStatus";
import { OrderItemDto } from "./OrderItemDto";
import { PaymentInfo } from "./PaymentInfo";

export interface OrderDto {
  id: string; // ObjectId as a string
  userId: string;
  deliveryAddress: Address; // Assuming Address is a string
  status: OrderStatus;
  promoCode: string;
  paymentInfo: PaymentInfo; // Assuming PaymentInfo is a string
  orderedAt: string; // ISO date-time string
  readyAt: string; // ISO date-time string
  completedAt: string; // ISO date-time string
  items: OrderItemDto[];
}
