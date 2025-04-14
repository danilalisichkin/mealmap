import { OrderStatus } from "../enums/OrderStatus";
import { PaymentStatus } from "../enums/PaymentStatus";

export interface OrderFilter {
  userId?: string; // UUID as a string
  status?: OrderStatus;
  paymentStatus?: PaymentStatus;
  orderedAtStart?: string; // ISO date-time string
  orderedAtEnd?: string; // ISO date-time string
  readyAtStart?: string; // ISO date-time string
  readyAtEnd?: string; // ISO date-time string
  completedAtStart?: string; // ISO date-time string
  completedAtEnd?: string; // ISO date-time string
  productId?: number;
}
