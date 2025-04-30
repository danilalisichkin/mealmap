import React from "react";
import OrderCard from "../../components/features/OrderCard/OrderCard";
import Pagination from "../../components/commons/Pagination/Pagination";
import { mockOrderPage } from "../../mock/orders";

interface UserOrdersPageProps {}

const UserOrdersPage: React.FC<UserOrdersPageProps> = () => {
  // TODO: API CALL
  const orders = mockOrderPage;

  return (
    <main className="container mx-auto px-4 py-8">
      <div className="mb-8">
        <h1 className="text-3xl font-bold text-gray-900">Мои заказы</h1>
        <p className="text-gray-600 mt-2">
          Здесь вы можете просмотреть историю ваших заказов
        </p>
      </div>

      <div className="mb-6 bg-white rounded-lg shadow p-4">
        <div className="flex flex-wrap items-center gap-4">
          <div>
            <label
              htmlFor="status-filter"
              className="block text-sm font-medium text-gray-700 mb-1"
            >
              Статус заказа
            </label>
            <select
              id="status-filter"
              className="border border-gray-300 rounded-md px-3 py-2 focus:outline-none focus:ring-2 focus:ring-green-500"
            >
              <option value="ALL">Все заказы</option>
              <option value="NEW">Новые</option>
              <option value="IN_PROGRESS">В процессе</option>
              <option value="READY">Готовы</option>
              <option value="ON_THE_WAY">В пути</option>
              <option value="COMPLETED">Завершённые</option>
              <option value="CANCELED">Отменённые</option>
            </select>
          </div>
          <div>
            <label
              htmlFor="date-filter"
              className="block text-sm font-medium text-gray-700 mb-1"
            >
              Период
            </label>
            <select
              id="date-filter"
              className="border border-gray-300 rounded-md px-3 py-2 focus:outline-none focus:ring-2 focus:ring-green-500"
            >
              <option value="ALL">За всё время</option>
              <option value="LAST_MONTH">Последний месяц</option>
              <option value="LAST_3_MONTHS">Последние 3 месяца</option>
              <option value="LAST_6_MONTHS">Последние 6 месяцев</option>
              <option value="LAST_YEAR">Последний год</option>
            </select>
          </div>
          <button className="mt-6 bg-green-600 hover:bg-green-700 text-white px-4 py-2 rounded-md transition-colors">
            Применить фильтры
          </button>
        </div>
      </div>

      <div className="space-y-4">
        {orders.items.map((order) => (
          <OrderCard key={order.id} order={order} />
        ))}
      </div>

      <Pagination
        page={1}
        pageSize={5}
        totalPages={1}
        totalElements={2}
        onPageChange={() => console.log("DEBUG")}
      />
    </main>
  );
};

export default UserOrdersPage;
