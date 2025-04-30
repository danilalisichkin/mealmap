import React, { useMemo } from "react";
import { ProductDto } from "../../api/product/dto/ProductDto";
import { mockProducts } from "../../mock/products";
import CartCheckoutItem from "../../components/features/CartCheckoutItem/CartCheckoutItem";
import NutrientChart from "../../components/features/NutrientChart/NutrientChart";
import NutrientBar from "../../components/features/NutrientBar/NutrientBar";
import CartDeliveryForm from "../../components/features/CartDeliveryForm/CartDeliveryForm";
import CartCheckoutSidebar from "../../components/features/CartCheckoutSidebar/CartCheckoutSidebar";
import { mockCart } from "../../mock/cart";

interface CartCheckoutPagePageProps {
}

const CartCheckoutPage: React.FC<CartCheckoutPagePageProps> = () => {
  // TODO: API CALL
  const cart = mockCart;
  const responsibleProducts = mockProducts;
  
  const productsMap = useMemo(() => {
    const map: Record<number, ProductDto> = {};
    responsibleProducts.forEach((p) => {
      map[p.id] = p;
    });
    return map;
  }, []);

  const totalCarbs = responsibleProducts.reduce(
    (sum, item) => sum + item.nutrients.carbs,
    0
  );
  const totalFats = responsibleProducts.reduce(
    (sum, item) => sum + item.nutrients.fats,
    0
  );
  const totalProteins = responsibleProducts.reduce(
    (sum, item) => sum + item.nutrients.proteins,
    0
  );
  const totalFiber = responsibleProducts.reduce(
    (sum, item) => sum + item.nutrients.fibers,
    0
  );
  const totalSugar = responsibleProducts.reduce(
    (sum, item) => sum + item.nutrients.sugars,
    0
  );

  const totalWeight = cart.items.reduce((sum, item) => {
    const product = productsMap[item.productId];
    return sum + (product ? product.weight * item.quantity : 0);
  }, 0);
  const totalCalories = cart.items.reduce((sum, item) => {
    const product = productsMap[item.productId];
    return sum + (product ? product.nutrients.calories * item.quantity : 0);
  }, 0);
  const totalPrice = cart.items.reduce((sum, item) => {
    const product = productsMap[item.productId];
    return sum + (product ? (product.price * item.quantity) / 100 : 0);
  }, 0);

  const maxValue = Math.max(
    totalProteins,
    totalFats,
    totalCarbs,
    totalFiber,
    totalSugar
  );
  const scaleFactor = 150 / maxValue;

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
                {cart.items.length === 0 ? (
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
              <CartDeliveryForm />
            </div>
          </div>

          <div>
            <CartCheckoutSidebar totalPrice={totalPrice} />
          </div>
        </div>
      </div>
    </main>
  );
};

export default CartCheckoutPage;
