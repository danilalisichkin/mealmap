import React from "react";
import { OrderItemDto } from "../../../api/order/dto/OrderItemDto";

interface OrderProductListProps {
  items: OrderItemDto[];
}

const OrderProductList: React.FC<OrderProductListProps> = ({ items }) => (
  <div className="space-y-4">
    {items.map((item) => (
      <div className="flex items-start gap-4" key={item.productId}>
        <img
          src="https://images.unsplash.com/photo-1490645935967-10de6ba17061?ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=80"
          alt="DEBUG"
          className="product-image w-16 h-16"
        />
        {/* TODO: replace with item.productName */}
        <div className="flex-1">
          <h4 className="font-medium text-gray-800">"DEBUG"</h4>
          <p className="text-sm text-gray-600">"DEBUG" г</p>
          <div className="flex justify-between items-center mt-1">
            <span className="text-gray-600">
              {item.quantity} × {item.priceWhenOrdered}₽
            </span>
            <span className="font-medium">
              {(item.quantity * item.priceWhenOrdered).toLocaleString()}₽
            </span>
          </div>
        </div>
      </div>
    ))}
  </div>
);

export default OrderProductList;
