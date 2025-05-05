import React, { useCallback, useEffect, useState } from "react";
import "./Cart.css";
import { CartDto } from "../../../api/cart/dto/CartDto";
import { ProductDto } from "../../../api/product/dto/ProductDto";
import { useNavigate } from "react-router-dom";
import CartItem from "../CartItem/CartItem";
import { CartApi } from "../../../api/cart/UserCartApi";
import { ProductApi } from "../../../api/product/ProductApi";
import { useAuth } from "../../../contexts/AuthContext";

interface CartProps {
  isOpened: boolean;
  onClose: () => void;
}

const Cart: React.FC<CartProps> = ({ isOpened, onClose }) => {
  const [cart, setCart] = useState<CartDto | null>(null);
  const [productsMap, setProductsMap] = useState<Record<number, ProductDto>>(
    {}
  );
  const [loading, setLoading] = useState<boolean>(false);
  const [error, setError] = useState<string | null>(null);

  const navigate = useNavigate();
  const { userId } = useAuth();

  const fetchCart = async (userId: string): Promise<CartDto> => {
    try {
      return await CartApi.getCart(userId);
    } catch (err) {
      console.error("Ошибка при загрузке корзины:", err);
      throw new Error("Не удалось загрузить корзину.");
    }
  };

  const fetchProducts = async (
    productIds: number[]
  ): Promise<Record<number, ProductDto>> => {
    try {
      const products = await ProductApi.bulkGetProducts(productIds);
      const productMap: Record<number, ProductDto> = {};
      products.forEach((product: ProductDto) => {
        productMap[product.id] = product;
      });
      return productMap;
    } catch (err) {
      console.error("Ошибка при загрузке продуктов:", err);
      throw new Error("Не удалось загрузить продукты.");
    }
  };

  const initializeCart = useCallback(async () => {
    if (!userId) {
      setError("Пользователь не авторизован");
      setLoading(false);
      return;
    }

    try {
      setLoading(true);

      const fetchedCart = await fetchCart(userId);
      setCart(fetchedCart);

      const productIds = fetchedCart.items.map((item) => item.productId);
      const productMap = await fetchProducts(productIds);
      setProductsMap(productMap);
    } catch (err: any) {
      setError(err.message ?? "Не удалось загрузить данные корзины.");
    } finally {
      setLoading(false);
    }
  }, [userId]);

  useEffect(() => {
    if (isOpened) {
      initializeCart();
    }
  }, [isOpened, initializeCart]);

  const handleCartSubmit = () => {
    onClose();
    navigate("cart/checkout", { state: { cart } });
  };

  const handleQuantityChange = async (itemId: number, newQuantity: number) => {
    if (!cart || !userId) return;

    try {
      const updatedItem = await CartApi.changeCartItemQuantity(
        userId,
        itemId,
        newQuantity
      );
      setCart((prev) => ({
        ...prev!,
        items: prev!.items.map((item) =>
          item.id === itemId
            ? {
                ...item,
                quantity: updatedItem.quantity,
              }
            : item
        ),
      }));
    } catch (err) {
      console.error("Ошибка при изменении количества товара:", err);
      setError("Не удалось изменить количество товара.");
    }
  };

  const handleRemove = async (itemId: number) => {
    if (!cart || !userId) return;

    try {
      await CartApi.deleteItemFromCart(userId, itemId);
      setCart((prev) => ({
        ...prev!,
        items: prev!.items.filter((item) => item.id !== itemId),
      }));
    } catch (err) {
      console.error("Ошибка при удалении товара из корзины:", err);
      setError("Не удалось удалить товар из корзины.");
    }
  };

  if (!isOpened) {
    return null;
  }

  if (loading) {
    return <div className="text-center py-12">Загрузка...</div>;
  }

  if (error) {
    return <div className="text-center py-12 text-red-500">{error}</div>;
  }

  if (!cart || cart.items.length === 0) {
    return (
      <div
        id="cart-panel"
        className={`cart-panel fixed top-0 right-0 w-full md:w-96 h-full bg-white shadow-lg z-50 overflow-y-auto ${
          isOpened ? "open" : "hidden fixed inset-0 bg-black bg-opacity-50 z-40"
        }`}
      >
        <i className="fas fa-shopping-cart text-4xl mb-3 opacity-30"></i>
        <p>Ваша корзина пуста</p>
      </div>
    );
  }

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
        {cart.items.map((cartItem) => {
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
        })}
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
