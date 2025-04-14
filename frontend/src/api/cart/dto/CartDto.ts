import { CartItemDto } from "./CartItemDto";

export interface CartDto {
  id: number;
  userId: string; // UUID
  items: CartItemDto[]; // List of cart items
  createdAt: string; // ISO date-time string
  updatedAt: string; // ISO date-time string
}
