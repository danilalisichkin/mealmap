import { CartDto } from "../api/cart/dto/CartDto";

export const mockCart: CartDto = {
  id: 1,
  userId: "0e3901b5-fb3f-44e4-b691-adac0a735789",
  items: [
    {
      id: 1,
      productId: 41,
      quantity: 2,
    },
    {
      id: 2,
      productId: 55,
      quantity: 1,
    },
  ],
  createdAt: "2025-04-21T12:00:00Z",
  updatedAt: "2025-04-21T12:30:00Z",
};
