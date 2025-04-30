import React, { useMemo, useState } from "react";
import "./Cart.css";
import { CartDto } from "../../../api/cart/dto/CartDto";
import { ProductDto } from "../../../api/product/dto/ProductDto";
import { mockProducts } from "../../../mock/products";
import { useNavigate } from "react-router-dom";
import CartItem from "../CartItem/CartItem";

interface CartProps {
  data: CartDto;
  isOpened: boolean;
  onClose: () => void;
}

const Cart: React.FC<CartProps> = ({ data, isOpened, onClose }) => {
  const [cart, setCart] = useState<CartDto>(data);
  const navigate = useNavigate();

  // TEST CASES ONLY, TODO: API CALL
  const productsMap = useMemo(() => {
    const map: Record<number, ProductDto> = {};
    mockProducts.forEach((p) => {
      map[p.id] = p;
    });
    return map;
  }, []);

  const handleCartSubmit = () => {
    onClose();
    navigate("/cart/checkout");
  };

  const handleQuantityChange = (itemId: number, newQuantity: number) => {
    setCart((prev) => ({
      ...prev,
      items: prev.items.map((item) =>
        item.id === itemId
          ? {
              ...item,
              quantity: newQuantity,
            }
          : item
      ),
    }));
  };

  const handleRemove = (itemId: number) => {
    setCart((prev) => ({
      ...prev,
      items: prev.items.filter((item) => item.id !== itemId),
    }));
  };

  const subtotal = cart.items.reduce((sum, item) => {
    const product = productsMap[item.productId];
    return sum + (product ? (product.price * item.quantity) / 100 : 0);
  }, 0);

  const totalItems = cart.items.reduce((sum, item) => sum + item.quantity, 0);

  return (
    <div
      id="cart-panel"
      className={`cart-panel fixed top-0 right-0 w-full md:w-96 h-full bg-white shadow-lg z-50 overflow-y-auto ${
        isOpened ? "open" : "hidden fixed inset-0 bg-black bg-opacity-50 z-40"
      }`}
    >
      <div className="p-4 border-b border-gray-200 flex justify-between items-center">
        <h2 className="text-xl font-semibold text-gray-800">
          Ваша корзина покупок
        </h2>
        <button
          id="cart-close"
          className="text-gray-500 hover:text-gray-700"
          onClick={onClose}
        >
          <i className="fas fa-times"></i>
        </button>
      </div>

      <div id="cart-items" className="divide-y divide-gray-200">
        {cart.items.length === 0 ? (
          <div
            className="text-center py-8 text-gray-500"
            id="empty-cart-message"
          >
            <i className="fas fa-shopping-cart text-4xl mb-3 opacity-30"></i>
            <p>Ваша корзина пуста</p>
          </div>
        ) : (
          cart.items.map((cartItem) => {
            const product = productsMap[cartItem.productId];
            if (!product) return null;
            return (
              <CartItem
                key={cartItem.id}
                cartItem={cartItem}
                product={product}
                onRemove={() => handleRemove(cartItem.id)}
                onQuantityChange={(newQuantity) =>
                  handleQuantityChange(cartItem.id, newQuantity)
                }
              />
            );
          })
        )}
      </div>

      <div className="p-4 border-t border-gray-200">
        <div className="flex justify-between mb-2">
          <span className="font-medium text-gray-700">Итого:</span>
          <span id="cart-subtotal" className="font-medium">
            ₽{subtotal.toFixed(2)}
          </span>
        </div>
        <div className="flex justify-between mb-4">
          <span className="font-medium text-gray-700">Товаров:</span>
          <span id="cart-total-items" className="font-medium">
            {totalItems}
          </span>
        </div>
        <button
          id="checkout-btn"
          className="w-full bg-green-600 text-white py-3 rounded-lg hover:bg-green-700 transition-colors flex items-center justify-center gap-2"
          onClick={handleCartSubmit}
        >
          <i className="fas fa-credit-card"></i>
          <span>Оформить заказ</span>
        </button>
      </div>
    </div>
  );
};

export default Cart;
