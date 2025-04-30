import React from "react";
import { CartItemDto } from "../../../api/cart/dto/CartItemDto";
import { ProductDto } from "../../../api/product/dto/ProductDto";

interface CartCheckoutItemProps {
  item: CartItemDto;
  product: ProductDto;
}

const CartCheckoutItem: React.FC<CartCheckoutItemProps> = ({
  item,
  product,
}) => {
  const itemTotal = (product.price * item.quantity) / 100;

  return (
    <div className="py-4 flex items-center">
      <img
        src="https://images.unsplash.com/photo-1546069901-ba9599a7e63c?ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80"
        alt={product.name}
        className="w-16 h-16 object-cover rounded-lg mr-4"
      />
      <div className="flex-1">
        <h4 className="font-medium text-gray-800">{product.name}</h4>
        <div className="flex justify-between items-center mt-1">
          <span className="text-gray-600">
            {product.price}₽ × {item.quantity}
          </span>
          <span className="font-medium">{itemTotal.toFixed(2)}₽</span>
        </div>
      </div>
    </div>
  );
};

export default CartCheckoutItem;
