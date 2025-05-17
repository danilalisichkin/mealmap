import React, { useCallback, useEffect, useState } from "react";
import "./Cart.css";
import { CartDto } from "../../../api/cart/dto/CartDto";
import { ProductDto } from "../../../api/product/dto/ProductDto";
import { useNavigate } from "react-router-dom";
import CartItem from "../CartItem/CartItem";
import { CartApi } from "../../../api/cart/UserCartApi";
import { ProductApi } from "../../../api/product/ProductApi";
import { useAuth } from "../../../contexts/AuthContext";
import { ErrorDetail } from "../../../api/common/dto/ErrorDetail";
import PopupNotification, {
  NotificationType,
} from "../PopupNotification/PopupNotification";
import ErrorBanner from "../../commons/ErrorBanner/ErrorBanner";
import LoadingSpinner from "../../commons/LoadingSpinner/LoadingSpinner";

interface CartProps {
  isOpened: boolean;
  onClose: () => void;
}

const Cart: React.FC<CartProps> = ({ isOpened, onClose }) => {
  const navigate = useNavigate();
  const { userId } = useAuth();

  const [cart, setCart] = useState<CartDto | null>(null);
  const [productsMap, setProductsMap] = useState<Record<number, ProductDto>>(
    {}
  );
  const [loading, setLoading] = useState<boolean>(false);
  const [error, setError] = useState<ErrorDetail | null>(null);

  const [notification, setNotification] = useState<{
    id: number;
    message: string;
    type: NotificationType;
    isVisible: boolean;
  }>({
    id: 0,
    message: "",
    type: NotificationType.SUCCESS,
    isVisible: false,
  });

  const showNotification = (message: string, type: NotificationType) => {
    setNotification({
      id: Date.now(),
      message,
      type,
      isVisible: true,
    });
  };

  const fetchCart = async () => {
    if (!userId) {
      setError({
        title: "Пользователь не авторизован",
        detail: "Пользователь не авторизован",
        status: "401",
      });
      setLoading(false);
      return;
    }

    try {
      const response = await CartApi.getCart(userId);
      setCart(response);
    } catch (err: any) {
      if (err.response?.status === 404) {
        setError({
          title: "Ваша корзина не найдена",
          detail: err.response?.data.detail,
          status: "404",
        });
      }
    }
  };

  const fetchProducts = async () => {
    const products = await ProductApi.bulkGetProducts(
      cart?.items.map((item) => item.productId) || []
    );

    if (products.length === 0) {
      return;
    }

    try {
      const productMap: Record<number, ProductDto> = {};
      products.forEach((product: ProductDto) => {
        productMap[product.id] = product;
      });
      setProductsMap(productMap);
    } catch (err) {
      console.error("Ошибка при загрузке продуктов:", err);
      throw new Error("Не удалось загрузить продукты.");
    }
  };
  
  const initializeCart = useCallback(async () => {
    if (!isOpened) return;

    if (!userId) {
      setError({
        title: "Пользователь не авторизован",
        detail: "Пользователь не авторизован",
        status: "401",
      });
      setLoading(false);
      return;
    }

    try {
      setLoading(true);
      await fetchCart();
    } catch (err) {
      console.error("Ошибка при инициализации корзины:", err);
    } finally {
      setLoading(false);
    }
  }, [userId, isOpened]);

  useEffect(() => {
    if (cart && cart.items.length > 0) {
      fetchProducts();
    }
  }, [cart]);

  useEffect(() => {
    initializeCart();
  }, [initializeCart]);

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
      showNotification(
        "В корзине максимальное количество данных блюд!",
        NotificationType.ERROR
      );
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
      showNotification(
        "Не удалось убрать товар из корзины",
        NotificationType.ERROR
      );
    }
  };

  if (!isOpened) {
    return null;
  }

  if (loading) {
    return (
      <div
        id="cart-panel"
        className={`cart-panel fixed top-0 right-0 w-full md:w-96 h-full bg-white shadow-lg z-50 overflow-y-auto ${
          isOpened ? "open" : "hidden fixed inset-0 bg-black bg-opacity-50 z-40"
        }`}
      >
        <LoadingSpinner />
      </div>
    );
  }

  if (error) {
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
        <div className="flex flex-col items-center justify-center h-full">
          <ErrorBanner error={error} />
        </div>
      </div>
    );
  }

  if (!cart || cart.items.length === 0) {
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
        <div className="flex flex-col items-center justify-center h-full">
          <div className="h-screen flex items-center justify-center">
            <div
              id="empty-state"
              className="flex flex-col items-center justify-center"
            >
              <div className="w-20 h-20 flex items-center justify-center mb-4">
                <i className="fas fa-shopping-cart text-gray-400 text-2xl"></i>
              </div>
              <h3 className="text-lg font-medium text-gray-700 mb-1">
                Корзина пуста
              </h3>
              <p className="text-gray-500 text-center max-w-xs">
                У вас пока нет товаров в корзине
              </p>
            </div>
          </div>
        </div>
      </div>
    );
  }

  const totalItems = cart.items.reduce(
    (total, item) => total + item.quantity,
    0
  );

  const totalPrice = cart.items.reduce((sum, item) => {
    const product = productsMap[item.productId];
    return sum + (product ? (product.price * item.quantity) / 100 : 0);
  }, 0);

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
            ₽{totalPrice.toFixed(2)}
          </span>
        </div>
        <div className="flex justify-between mb-4">
          <span className="font-medium text-gray-700">Товаров:</span>
          <span id="cart-total-items" className="font-medium">
            {totalItems} шт.
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
      <PopupNotification
        key={notification.id}
        message={notification.message}
        type={notification.type}
        isVisible={notification.isVisible}
      />
    </div>
  );
};

export default Cart;
