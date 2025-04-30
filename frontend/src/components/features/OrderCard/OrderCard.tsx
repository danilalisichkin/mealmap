import React, { useState } from "react";
import { OrderDto } from "../../../api/order/dto/OrderDto";
import OrderProductList from "../OrderProductList/OrderProductList";
import OrderDetails from "../OrderDetails/OrderDetails";
import OrderStatusTimeline from "../OrderStatusTimeline/OrderStatusTimeline";
import "./OrderCard.css";

interface OrderCardProps {
  order: OrderDto;
}

const steps = [
  { key: "NEW", label: "Заказ оформлен" },
  {
    key: "IN_PROGRESS",
    label: "В процессе приготовления",
  },
  { key: "READY", label: "Приготовлен" },
  { key: "ON_THE_WAY", label: "Передан курьеру" },
  { key: "COMPLETED", label: "Доставлен" },
];

const OrderCard: React.FC<OrderCardProps> = ({ order }) => {
  const [expanded, setExpanded] = useState(false);

  let currentStatus = steps.find((s) => s.key === order.status);

  return (
    <div className="order-card bg-white rounded-lg shadow overflow-hidden">
      <div className="p-6">
        <div className="flex flex-col md:flex-row md:items-center md:justify-between gap-4">
          <div className="container w-full">
            <div className="flex flex-row items-center gap-3 mb-2 flex-wrap">
              <h2 className="text-xl font-semibold text-gray-800">
                Заказ №{order.id}
              </h2>
              <span
                className={`status-badge status-${order.status} flex-shrink-0`}
              >
                {currentStatus?.label ?? "Неизвестно"}
              </span>
            </div>
            <p className="text-gray-600">
              <i className="far fa-calendar-alt mr-2"></i>
              Оформлен: {new Date(order.orderedAt).toLocaleDateString()} в{" "}
              {new Date(order.orderedAt).toLocaleTimeString()}
            </p>
          </div>
          <div className="flex items-center gap-4">
            <span className="text-xl font-bold text-gray-800">
              {order.paymentInfo.totalAmount}₽
            </span>
            <button
              className="expand-btn flex items-center justify-center w-10 h-10 rounded-full bg-gray-100 hover:bg-gray-200 text-gray-700 transition-transform"
              onClick={() => setExpanded((prev) => !prev)}
              aria-expanded={expanded}
              aria-label={expanded ? "Свернуть заказ" : "Развернуть заказ"}
            >
              <i
                className={`fas fa-chevron-down transition-transform duration-200 ${
                  expanded ? "rotate-180" : ""
                }`}
              ></i>
            </button>
          </div>
        </div>
      </div>
      <div
        className={`order-details border-t border-gray-200${
          expanded ? " expanded" : ""
        }`}
      >
        {expanded && (
          <div className="grid md:grid-cols-3 gap-6 p-6">
            <div className="md:col-span-2">
              <h3 className="text-lg font-semibold text-gray-800 mb-4">
                Состав заказа
              </h3>
              <OrderProductList items={order.items} />
              <OrderDetails order={order} />
            </div>
            <div>
              <h3 className="text-lg font-semibold text-gray-800 mb-4">
                Статус заказа
              </h3>
              <OrderStatusTimeline
                status={order.status}
                createdAt={order.orderedAt}
              />
              <div className="mt-6 pt-6 border-t border-gray-200">
                <h4 className="font-medium text-gray-800 mb-2">
                  Адрес доставки
                </h4>
                <p className="text-gray-600">
                  <i className="fas fa-map-marker-alt mr-2"></i>
                  {order.deliveryAddress.fullAddress}
                </p>
                <h4 className="font-medium text-gray-800 mt-4 mb-2">
                  ID оплаты
                </h4>
                <p className="text-gray-600">
                  <i className="fas fa-credit-card mr-2"></i>
                  {order.paymentInfo.paymentId}
                </p>
                {order.promoCode && (
                  <>
                    <h4 className="font-medium text-gray-800 mt-4 mb-2">
                      Промокод
                    </h4>
                    <p className="text-gray-600">
                      <i className="fas fa-tag mr-2"></i>
                      {order.promoCode}
                    </p>
                  </>
                )}
              </div>
              <div className="mt-6 pt-6 border-t border-gray-200 flex flex-col gap-3">
                <button className="w-full bg-green-600 hover:bg-green-700 text-white py-2 rounded-md transition-colors">
                  Повторить заказ
                </button>
                <button className="w-full bg-gray-100 hover:bg-gray-200 text-gray-800 py-2 rounded-md transition-colors">
                  Отменить заказ
                </button>
                <button className="w-full border border-gray-300 hover:bg-gray-50 text-gray-800 py-2 rounded-md transition-colors">
                  Связаться с поддержкой
                </button>
              </div>
            </div>
          </div>
        )}
      </div>
    </div>
  );
};

export default OrderCard;
