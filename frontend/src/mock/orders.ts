import { PageDto } from "../api/common/dto/PageDto";
import { OrderDto } from "../api/order/dto/OrderDto";
import { OrderStatus } from "../api/order/enums/OrderStatus";
import { PaymentStatus } from "../api/order/enums/PaymentStatus";

export const mockOrders: OrderDto[] = [
  {
    id: "111111111111111111111111",
    userId: "0e3901b5-fb3f-44e4-b691-adac0a735789",
    deliveryAddress: {
      fullAddress: "Советская ул., дом 6",
      coordinates: [-118.358467, 34.063621],
    },
    status: OrderStatus.COMPLETED,
    promoCode: "SUMMER2025",
    paymentInfo: {
      totalAmount: 200,
      discountAmount: 100,
      paymentId: 1,
      paymentStatus: PaymentStatus.PAID,
    },
    orderedAt: "2024-01-26T14:30:45Z",
    readyAt: "2024-01-26T14:40:45Z",
    completedAt: "2024-01-26T14:50:45Z",
    items: [
      { productId: 1, supplierId: 2, priceWhenOrdered: 50, quantity: 4 },
      { productId: 2, supplierId: 2, priceWhenOrdered: 100, quantity: 1 },
    ],
  },
  {
    id: "22222222222222222222222222",
    userId: "0e3901b5-fb3f-44e4-b691-adac0a735789",
    deliveryAddress: {
      fullAddress: "Советская ул., дом 6",
      coordinates: [-118.358467, 34.063621],
    },
    status: OrderStatus.READY,
    promoCode: "",
    paymentInfo: {
      totalAmount: 400,
      discountAmount: 100,
      paymentId: 2,
      paymentStatus: PaymentStatus.PENDING,
    },
    orderedAt: "2024-01-26T14:30:45Z",
    readyAt: "2024-01-26T14:40:45Z",
    completedAt: "",
    items: [
      { productId: 1, supplierId: 2, priceWhenOrdered: 50, quantity: 6 },
      { productId: 2, supplierId: 2, priceWhenOrdered: 100, quantity: 1 },
    ],
  },
];

export const mockOrderPage: PageDto<OrderDto> = {
  currentPage: 2,
  pageSize: 5,
  totalPages: 10,
  totalElements: 50,
  items: mockOrders,
};
