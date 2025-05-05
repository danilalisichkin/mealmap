import React, { useEffect, useState } from "react";

interface CartCheckoutSidebarProps {
  totalPrice: number;
  onApplyPromoCode: (promoCode: string) => void;
  promoCodeStatus: "valid" | "invalid" | null;
  discount: number;
  onPlaceOrder: () => void;
}

const CartCheckoutSidebar: React.FC<CartCheckoutSidebarProps> = ({
  totalPrice,
  onApplyPromoCode,
  promoCodeStatus,
  discount,
  onPlaceOrder,
}) => {
  const [promoCode, setPromoCode] = useState("");
  const [promoCodeExistenceStatus, setPromoCodeExistenceStatus] = useState<"valid" | "invalid" | null>(null);

  useEffect(() => {
    setPromoCodeExistenceStatus(promoCodeStatus);
  }, [promoCodeStatus]);
  const discountAmount = (discount / 100) * totalPrice;

  const handleApplyPromoCode = () => {
    onApplyPromoCode(promoCode);
  };

  const handlePromoCodeChange = (value: string) => {
    setPromoCodeExistenceStatus(null);
    setPromoCode(value);
  }

  return (
    <div className="bg-white rounded-lg shadow-md p-6 sticky top-16">
      <h3 className="text-lg font-semibold text-gray-800 mb-4">Итого</h3>
      <div className="space-y-3 mb-4">
        <div className="flex justify-between">
          <span className="text-gray-600">Товары:</span>
          <span id="checkout-subtotal" className="font-medium">
            ₽{totalPrice}
          </span>
        </div>
        <div className="flex justify-between items-center">
          <span className="text-gray-600">Промокод:</span>
          <input
            type="text"
            id="promo-code"
            placeholder="Введите код"
            onChange={(e) => handlePromoCodeChange(e.target.value)}
            className={`px-3 py-1 border font-medium ${
              promoCodeExistenceStatus === "valid"
                ? "border-green-500"
                : promoCodeExistenceStatus === "invalid"
                ? "border-red-500"
                : "border-gray-300"
            } rounded-lg text-sm w-28 focus:ring-2 focus:ring-green-800 focus:border-green-800`}
          />
        </div>
        <div className="flex justify-end">
            <button
            onClick={handleApplyPromoCode}
            className={`px-3 py-1 w-28 text-white rounded-lg text-sm ${
              promoCodeExistenceStatus === null ? "bg-green-500 hover:bg-green-600" : "bg-gray-300"
            }`}
            disabled={promoCodeExistenceStatus !== null}
            >
            Применить
            </button>
        </div>
        {discount > 0 && (
          <div
            id="promo-discount"
            className="flex justify-between text-green-600"
          >
            <span>Скидка:</span>
            <span>-₽{discountAmount}</span>
          </div>
        )}
      </div>
      <div className="border-t border-gray-200 pt-4 mb-4">
        <div className="flex justify-between font-bold text-lg">
          <span>Всего:</span>
          <span id="checkout-total">₽{totalPrice - discountAmount}</span>
        </div>
      </div>
      <button
        id="place-order"
        onClick={onPlaceOrder}
        className="w-full bg-green-600 hover:bg-green-700 text-white py-3 rounded-lg font-medium transition-colors"
      >
        Подтвердить заказ
      </button>
    </div>
  );
};

export default CartCheckoutSidebar;
