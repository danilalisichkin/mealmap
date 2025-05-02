import React, { useCallback, useEffect, useState } from "react";
import OrderCard from "../../components/features/OrderCard/OrderCard";
import Pagination from "../../components/commons/Pagination/Pagination";
import { OrderDto } from "../../api/order/dto/OrderDto";
import { PageDto } from "../../api/common/dto/PageDto";
import { UserOrderApi } from "../../api/order/UserOrderApi";
import { useLocation } from "react-router-dom";

interface UserOrdersPageProps {}

const DEFAULT_PAGINATION_OPTIONS = {
  PAGE_NUMBER: 1,
  PAGE_SIZE: 4,
};

const UserOrdersPage: React.FC<UserOrdersPageProps> = () => {
  // TODO: добавить фильтрацию и сортировку

  //const [filter, setFilter] = useState<OrderFilter>(DEFAULT_FILTER);
  // const [isFilterOpened, setFilterOpened] = useState(false);
  // const [sortField, setSortField] = useState<OrderSortField>(
  //   OrderSortField.ID
  // );
  //const [sortOrder, setSortOrder] = useState<"asc" | "desc">("asc");

  const location = useLocation();
  const userId = location.state?.userId ?? null;

  const [orderPage, setOrderPage] = useState<PageDto<OrderDto> | null>(null);
  const [currentPage, setCurrentPage] = useState(
    DEFAULT_PAGINATION_OPTIONS.PAGE_NUMBER
  );

  const fetchOrders = useCallback(async () => {
    if (!userId) {
      return;
    }

    try {
      const response = await UserOrderApi.getUserOrders(
        userId,
        currentPage - 1,
        DEFAULT_PAGINATION_OPTIONS.PAGE_SIZE
      );
      setOrderPage(response);
    } catch (err) {
      console.error("Ошибка при загрузке заказов:", err);
    }
  }, [userId, currentPage]);

  useEffect(() => {
    fetchOrders();
  }, [fetchOrders]);

  const handlePageChange = (page: number) => {
    setCurrentPage(page);
  };

  return (
    <main className="container mx-auto px-4 py-8">
      <div className="flex flex-col md:flex-row justify-between items-start md:items-center mb-8 gap-4">
        <h1 className="text-3xl font-bold text-gray-900">Мои заказы</h1>
        <div className="w-full md:w-auto flex flex-col md:flex-row gap-3">
          {/* //TODO: фильтры, сортировка */}
        </div>
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
        {orderPage &&
        Array.isArray(orderPage.items) &&
        orderPage.items.length > 0 ? (
          <>
            {orderPage.items.map((order) => (
              <OrderCard key={order.id} order={order} />
            ))}
            <Pagination
              page={currentPage}
              pageSize={
                orderPage.pageSize ?? DEFAULT_PAGINATION_OPTIONS.PAGE_SIZE
              }
              totalPages={orderPage.totalPages || 1}
              totalElements={orderPage.totalElements || 0}
              onPageChange={handlePageChange}
            />
          </>
        ) : (
          <p>У вас пока нет заказов.</p>
        )}
      </div>
    </main>
  );
};

export default UserOrdersPage;
