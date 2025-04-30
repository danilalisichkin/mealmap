import React from "react";

interface CartCheckoutSidebarProps {
  totalPrice: number;
}

const CartCheckoutSidebar: React.FC<CartCheckoutSidebarProps> = ({
  totalPrice,
}) => (
  <div className="bg-white rounded-lg shadow-md p-6 sticky top-16">
    <h3 className="text-lg font-semibold text-gray-800 mb-4">Итого</h3>
    <div className="space-y-3 mb-4">
      <div className="flex justify-between">
        <span className="text-gray-600">Товары:</span>
        <span id="checkout-subtotal" className="font-medium">
          ₽{totalPrice}
        </span>
      </div>
      <div className="flex justify-between">
        <span className="text-gray-600">Доставка:</span>
        <span className="font-medium">₽200</span>
      </div>
      <div className="flex justify-between items-center">
        <span className="text-gray-600">Промокод:</span>
        <div className="flex items-center">
          <input
            type="text"
            id="promo-code"
            placeholder="Введите код"
            className="px-3 py-1 border border-gray-300 rounded-lg text-sm w-28 focus:ring-2 focus:ring-green-800 focus:border-green-800"
          />
        </div>
      </div>
      <div
        id="promo-discount"
        className="flex justify-between text-green-600 hidden"
      >
        <span>Скидка:</span>
        <span>-₽0</span>
      </div>
    </div>
    <div className="border-t border-gray-200 pt-4 mb-4">
      <div className="flex justify-between font-bold text-lg">
        <span>Всего:</span>
        <span id="checkout-total">₽{totalPrice}</span>
      </div>
    </div>
    <button
      id="place-order"
      className="w-full bg-green-600 hover:bg-green-700 text-white py-3 rounded-lg font-medium transition-colors"
    >
      Подтвердить заказ
    </button>
  </div>
);

export default CartCheckoutSidebar;
