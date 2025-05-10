import React, { useCallback, useEffect, useState } from "react";
import OrderCard from "../../components/features/OrderCard/OrderCard";
import Pagination from "../../components/commons/Pagination/Pagination";
import { OrderDto } from "../../api/order/dto/OrderDto";
import { PageDto } from "../../api/common/dto/PageDto";
import { UserOrderApi } from "../../api/order/UserOrderApi";
import { useParams } from "react-router-dom";
import LoadingSpinner from "../../components/commons/LoadingSpinner/LoadingSpinner";
import { ErrorDetail } from "../../api/common/dto/ErrorDetail";
import ErrorBanner from "../../components/commons/ErrorBanner/ErrorBanner";

interface UserOrdersPageProps {}

const DEFAULT_PAGINATION_OPTIONS = {
  PAGE_NUMBER: 1,
  PAGE_SIZE: 4,
};

const UserOrdersPage: React.FC<UserOrdersPageProps> = () => {
  const { userId } = useParams<{ userId: string }>();

  const [orderPage, setOrderPage] = useState<PageDto<OrderDto> | null>(null);
  const [currentPage, setCurrentPage] = useState(
    DEFAULT_PAGINATION_OPTIONS.PAGE_NUMBER
  );

  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<ErrorDetail | null>(null);

  const fetchOrders = useCallback(async () => {
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
      const response = await UserOrderApi.getUserOrders(
        userId,
        currentPage - 1,
        DEFAULT_PAGINATION_OPTIONS.PAGE_SIZE
      );
      setOrderPage(response);
      setLoading(false);
    } catch (err) {
      console.error("Ошибка при загрузке заказов:", err);
      setLoading(false);
    }
  }, [userId, currentPage]);

  useEffect(() => {
    fetchOrders();
  }, [fetchOrders]);

  const handlePageChange = (page: number) => {
    setCurrentPage(page);
  };

  if (loading) {
    return (
      <div className="h-screen flex items-center justify-center">
        <LoadingSpinner />
      </div>
    );
  }

  if (error) {
    return (
      <div className="h-screen flex items-center justify-center">
        <ErrorBanner error={error} />
      </div>
    );
  }

  if (!orderPage || orderPage.items.length === 0) {
    return (
      <div className="h-screen flex items-center justify-center">
        <div
          id="empty-state"
          className="flex flex-col items-center justify-center"
        >
          <div className="w-20 h-20 rounded-full bg-gray-100 flex items-center justify-center mb-4">
            <i className="fas fa-box-open text-gray-400 text-2xl"></i>
          </div>
          <h3 className="text-lg font-medium text-gray-700 mb-1">
            Нет заказов
          </h3>
          <p className="text-gray-500 text-center max-w-xs">
            У вас пока нет заказов.
          </p>
        </div>
      </div>
    );
  }

  return (
    <main className="container h-screen mx-auto px-4 py-8">
      <div className="flex flex-col md:flex-row justify-between items-start md:items-center mb-8 gap-4">
        <h1 className="text-3xl font-bold text-gray-900">Мои заказы</h1>
      </div>

      <div className="space-y-4">
        {orderPage.items.map((order) => (
          <OrderCard key={order.id} order={order} />
        ))}
        <Pagination
          page={currentPage}
          pageSize={orderPage.pageSize ?? DEFAULT_PAGINATION_OPTIONS.PAGE_SIZE}
          totalPages={orderPage.totalPages || 1}
          totalElements={orderPage.totalElements || 0}
          onPageChange={handlePageChange}
        />
      </div>
    </main>
  );
};

export default UserOrdersPage;
