import React from "react";
import { OrderDto } from "../../../api/order/dto/OrderDto";

interface OrderDetailsProps {
  order: OrderDto;
}

const OrderDetails: React.FC<OrderDetailsProps> = ({ order }) => (
  <div className="border-t border-gray-200 mt-6 pt-6">
    <div className="flex justify-between">
      <span className="text-gray-700">Сумма заказа:</span>
      <span className="font-medium">
        {order.paymentInfo.totalAmount + order.paymentInfo.discountAmount}₽
      </span>
    </div>
    {order.paymentInfo.discountAmount > 0 && (
      <div className="flex justify-between mt-2">
        <span className="text-gray-700">Скидка по промокоду:</span>
        <span className="font-medium text-green-600">
          -{order.paymentInfo.discountAmount}₽
        </span>
      </div>
    )}
    <div className="flex justify-between mt-4 pt-4 border-t border-gray-200 font-bold text-lg">
      <span>Итого:</span>
      <span>{order.paymentInfo.totalAmount.toLocaleString()}₽</span>
    </div>
  </div>
);

export default OrderDetails;
