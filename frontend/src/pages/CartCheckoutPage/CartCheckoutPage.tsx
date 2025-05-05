import React, { useEffect, useMemo, useState } from "react";
import { ProductDto } from "../../api/product/dto/ProductDto";
import CartCheckoutItem from "../../components/features/CartCheckoutItem/CartCheckoutItem";
import NutrientChart from "../../components/features/NutrientChart/NutrientChart";
import NutrientBar from "../../components/features/NutrientBar/NutrientBar";
import CartDeliveryForm from "../../components/features/CartDeliveryForm/CartDeliveryForm";
import CartCheckoutSidebar from "../../components/features/CartCheckoutSidebar/CartCheckoutSidebar";
import { useLocation } from "react-router-dom";
import { CartDto } from "../../api/cart/dto/CartDto";
import { CartApi } from "../../api/cart/UserCartApi";
import { ProductApi } from "../../api/product/ProductApi";
import { useAuth } from "../../contexts/AuthContext";
import { UserOrderApi } from "../../api/order/UserOrderApi";
import { PromoCodeApi } from "../../api/promo/PromoCodeApi";

interface CartCheckoutPagePageProps {}

const CartCheckoutPage: React.FC<CartCheckoutPagePageProps> = () => {
  const location = useLocation();
  const { userId } = useAuth() ?? null;
  const [cart, setCart] = useState<CartDto | null>(
    location.state?.cart ?? null
  );
  const [products, setProducts] = useState<ProductDto[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);
  const [successMessage, setSuccessMessage] = useState<string | null>(null);

  const [deliveryData, setDeliveryData] = useState({
    name: "",
    phone: "",
    address: "",
    comment: "",
  });
  const [promoCodeStatus, setPromoCodeStatus] = useState<
    "valid" | "invalid" | null
  >(null);
  const [discount, setDiscount] = useState<number>(0);
  const [appliedPromoCode, setAppliedPromoCode] = useState<string | null>(null);

  const fetchCart = async () => {
    if (!userId) {
      setError("Пользователь не авторизован");
      setLoading(false);
      return;
    }

    try {
      setLoading(true);
      setError(null);

      let fetchedCart = cart;
      if (!fetchedCart) {
        fetchedCart = await CartApi.getCart(userId);
        setCart(fetchedCart);
      }
    } catch (err) {
      console.error("Ошибка при загрузке корзины:", err);
      setError("Не удалось загрузить данные. Попробуйте снова.");
    } finally {
      setLoading(false);
    }
  };

  const fetchProducts = async () => {
    if (!userId) {
      setError("Пользователь не авторизован");
      setLoading(false);
      return;
    }

    try {
      setLoading(true);
      setError(null);

      const productIds = cart?.items.map((item) => item.productId) || [];
      const fetchedProducts = await ProductApi.bulkGetProducts(productIds);
      setProducts(fetchedProducts);
    } catch (err) {
      console.error("Ошибка при загрузке продуктов:", err);
      setError("Не удалось загрузить продукты. Попробуйте снова.");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchCart();
    fetchProducts();
  }, [cart]);

  const handleDeliveryChange = (field: string, value: string) => {
    setDeliveryData((prev) => ({ ...prev, [field]: value }));
  };

  const handleApplyPromoCode = async (promoCode: string) => {
    try {
      const promo = await PromoCodeApi.getPromoCodeByValue(promoCode);
      setPromoCodeStatus("valid");
      setDiscount(promo.discountPercentage);
      setAppliedPromoCode(promoCode);
    } catch (err) {
      console.error("Ошибка при применении промокода:", err);
      setPromoCodeStatus("invalid");
      setDiscount(0);
      setAppliedPromoCode(null);
    }
  };

  const handlePlaceOrder = async () => {
    if (!userId || !cart) {
      setError("Пользователь не авторизован или корзина пуста.");
      return;
    }

    try {
      setLoading(true);
      setError(null);
      setSuccessMessage(null);

      const orderItems = cart.items.map((item) => ({
        productId: item.productId,
        quantity: item.quantity,
      }));

      const createdOrder = await UserOrderApi.createUserOrder(userId, {
        deliveryAddress: {
          coordinates: [0, 0],
          fullAddress: deliveryData.address,
        },
        promoCode: appliedPromoCode ?? "",
        items: orderItems,
      });
      setSuccessMessage(
        `Заказ успешно оформлен! Номер заказа: ${createdOrder.id}`
      );
    } catch (err) {
      console.error("Ошибка при оформлении заказа:", err);
      setError("Не удалось оформить заказ. Попробуйте снова.");
    } finally {
      setLoading(false);
    }
  };

  const productsMap = useMemo(() => {
    const map: Record<number, ProductDto> = {};
    products.forEach((p) => {
      map[p.id] = p;
    });
    return map;
  }, [products]);

  const totalCarbs = products.reduce(
    (sum, item) => sum + item.nutrients.carbs,
    0
  );
  const totalFats = products.reduce(
    (sum, item) => sum + item.nutrients.fats,
    0
  );
  const totalProteins = products.reduce(
    (sum, item) => sum + item.nutrients.proteins,
    0
  );
  const totalFiber = products.reduce(
    (sum, item) => sum + item.nutrients.fibers,
    0
  );
  const totalSugar = products.reduce(
    (sum, item) => sum + item.nutrients.sugars,
    0
  );

  const totalWeight =
    cart?.items.reduce((sum, item) => {
      const product = productsMap[item.productId];
      return sum + (product ? product.weight * item.quantity : 0);
    }, 0) ?? 0;

  const totalCalories =
    cart?.items.reduce((sum, item) => {
      const product = productsMap[item.productId];
      return sum + (product ? product.nutrients.calories * item.quantity : 0);
    }, 0) ?? 0;

  const totalPrice =
    cart?.items.reduce((sum, item) => {
      const product = products.find((p) => p.id === item.productId);
      return sum + (product ? (product.price * item.quantity) / 100 : 0);
    }, 0) ?? 0;

  const maxValue = Math.max(
    totalProteins,
    totalFats,
    totalCarbs,
    totalFiber,
    totalSugar
  );
  const scaleFactor = 150 / maxValue;

  if (loading) {
    return <div className="text-center py-12">Загрузка...</div>;
  }

  if (error) {
    return <div className="text-center py-12 text-red-500">{error}</div>;
  }

  if (successMessage) {
    return (
      <div className="text-center py-12 text-green-500">{successMessage}</div>
    );
  }

  return (
    <main id="checkout-form" className="checkout-form inset-0 bg-white z-5 p-6">
      <div className="max-w-4xl mx-auto">
        <div className="flex justify-between items-center mb-8">
          <h2 className="text-2xl font-bold text-gray-800">
            Оформление заказа
          </h2>
        </div>

        <div className="grid md:grid-cols-3 gap-8">
          <div className="md:col-span-2">
            <div className="bg-white rounded-lg shadow-md p-6 mb-6">
              <h3 className="text-lg font-semibold text-gray-800 mb-4">
                Ваш заказ
              </h3>
              <div id="checkout-items" className="divide-y divide-gray-200">
                {!cart || cart.items.length === 0 ? (
                  <div className="text-center py-8 text-gray-500">
                    <i className="fas fa-shopping-cart text-4xl mb-3 opacity-30"></i>
                    <p>Ваша корзина пуста</p>
                  </div>
                ) : (
                  cart.items.map((cartItem) => {
                    const product = productsMap[cartItem.productId];
                    return product ? (
                      <CartCheckoutItem
                        key={cartItem.productId}
                        item={cartItem}
                        product={product}
                      />
                    ) : null;
                  })
                )}
              </div>

              <div className="mt-6">
                <h3 className="text-lg font-semibold text-gray-800 mb-4">
                  Пищевая ценность
                </h3>
                <NutrientChart
                  totalProteins={totalProteins}
                  totalFats={totalFats}
                  totalCarbs={totalCarbs}
                  totalFiber={totalFiber}
                  totalSugar={totalSugar}
                  scaleFactor={scaleFactor}
                />
                <NutrientBar
                  totalProteins={totalProteins}
                  totalFats={totalFats}
                  totalCarbs={totalCarbs}
                  totalFiber={totalFiber}
                  totalSugar={totalSugar}
                />
                <div className="grid grid-cols-2 gap-4">
                  <div className="bg-gray-50 p-3 rounded-lg border border-gray-100">
                    <span className="text-gray-700">
                      <i className="fas fa-fire text-gray-400 mr-2"></i>{" "}
                      Калории: <span id="total-calories">{totalCalories}</span>{" "}
                      ккал
                    </span>
                  </div>
                  <div className="bg-gray-50 p-3 rounded-lg border border-gray-100">
                    <span className="text-gray-700">
                      <i className="fas fa-weight-hanging text-gray-400 mr-2"></i>{" "}
                      Вес: <span id="total-weight">{totalWeight}</span> г
                    </span>
                  </div>
                </div>
              </div>
            </div>

            <div className="bg-white rounded-lg shadow-md p-6">
              <h3 className="text-lg font-semibold text-gray-800 mb-4">
                Данные для доставки
              </h3>
              <CartDeliveryForm
                deliveryData={deliveryData}
                onChange={handleDeliveryChange}
              />
            </div>
          </div>

          <div>
            <CartCheckoutSidebar
              totalPrice={totalPrice}
              onApplyPromoCode={handleApplyPromoCode}
              promoCodeStatus={promoCodeStatus}
              discount={discount}
              onPlaceOrder={handlePlaceOrder}
            />
          </div>
        </div>
      </div>
    </main>
  );
};

export default CartCheckoutPage;
