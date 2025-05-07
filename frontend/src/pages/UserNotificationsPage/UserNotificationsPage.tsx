import React, { useCallback, useEffect, useState } from "react";
import { NotificationStatus } from "../../api/notification/enums/NotificationStatus";
import { Channel } from "../../api/notification/enums/Channel";
import "./UserNotificationsPage.css";
import UserNotificationItem from "../../components/features/UserNotificationItem/UserNotificationItem";
import { useLocation } from "react-router-dom";
import { NotificationDto } from "../../api/notification/dto/NotificationDto";
import { UserNotificationApi } from "../../api/notification/UserNotificationApi";
import { PageDto } from "../../api/common/dto/PageDto";
import { NotificationSortField } from "../../api/notification/enums/NotificationSortField";
import Pagination from "../../components/commons/Pagination/Pagination";
import NotificationSort from "../../components/features/NotificationSort/NotificatonSort";
import { ErrorDetail } from "../../api/common/dto/ErrorDetail";
import ErrorBanner from "../../components/commons/ErrorBanner/ErrorBanner";
import LoadingSpinner from "../../components/commons/LoadingSpinner/LoadingSpinner";

interface UserNotificationsPageProps {}

const channelMap: Record<
  Channel,
  { icon: string; class: string; label: string }
> = {
  [Channel.EMAIL]: {
    icon: "fas fa-envelope",
    class: "channel-email",
    label: "Email",
  },
  [Channel.SMS]: {
    icon: "fas fa-sms",
    class: "channel-sms",
    label: "SMS",
  },
  [Channel.TELEGRAM]: {
    icon: "fab fa-telegram-plane",
    class: "channel-telegram",
    label: "Telegram",
  },
};

const DEFAULT_PAGINATION_OPTIONS = {
  PAGE_NUMBER: 1,
  PAGE_SIZE: 10,
};

const statusMap: Record<NotificationStatus, { class: string; label: string }> =
  {
    [NotificationStatus.SENT]: { class: "status-sent", label: "Отправлено" },
    [NotificationStatus.PENDING]: {
      class: "status-pending",
      label: "В ожидании",
    },
    [NotificationStatus.FAILED]: { class: "status-failed", label: "Ошибка" },
  };

const UserNotificationsPage: React.FC<UserNotificationsPageProps> = () => {
  const location = useLocation();
  const userId = location.state?.userId ?? null;

  const [notificationPage, setNotificationPage] =
    useState<PageDto<NotificationDto> | null>(null);

  const [sortField, setSortField] = useState<NotificationSortField>(
    NotificationSortField.SENT_AT
  );
  const [sortOrder, setSortOrder] = useState<"asc" | "desc">("desc");
  const [currentPage, setCurrentPage] = useState(
    DEFAULT_PAGINATION_OPTIONS.PAGE_NUMBER
  );

  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<ErrorDetail | null>(null);

  const fetchNotifications = useCallback(async () => {
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
      const response = await UserNotificationApi.getUserNotifications(
        userId,
        currentPage - 1,
        DEFAULT_PAGINATION_OPTIONS.PAGE_SIZE,
        sortField,
        sortOrder.toUpperCase() as "ASC" | "DESC"
      );
      setNotificationPage(response);
    } catch (err: any) {
      if (err.response?.status === 404) {
        console.error("Ошибка при загрузке уведомлений:", err);
        setError({
          title: "Упс! Кажется, уведомления не найдены",
          detail: err.response?.data.detail,
          status: "404",
        });
      } else {
        console.error("Ошибка при загрузке уведомлений:", err);
        setError({
          title: "Что-то пошло не так",
          detail: err.response?.data.detail,
          status: "500",
        });
      }
    } finally {
      setLoading(false);
    }
  }, [userId, currentPage, sortField, sortOrder]);

  useEffect(() => {
    fetchNotifications();
  }, [fetchNotifications]);

  const handlePageChange = (page: number) => {
    setCurrentPage(page);
  };

  const handleSortChange = (
    field: NotificationSortField,
    order: "asc" | "desc"
  ) => {
    setSortField(field);
    setSortOrder(order);
    setCurrentPage(1);
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
      <div className="h-screen">
        <ErrorBanner error={error} />;
      </div>
    );
  }

  if (!notificationPage || notificationPage.items.length === 0) {
    return (
      <div className="h-screen flex items-center justify-center">
        <div
          id="empty-state"
          className="flex flex-col items-center justify-center"
        >
          <div className="w-20 h-20 rounded-full bg-gray-100 flex items-center justify-center mb-4">
            <i className="fas fa-bell-slash text-gray-400 text-2xl"></i>
          </div>
          <h3 className="text-lg font-medium text-gray-700 mb-1">
            Нет уведомлений
          </h3>
          <p className="text-gray-500 text-center max-w-xs">
            У вас пока нет уведомлений
          </p>
        </div>
      </div>
    );
  }

  return (
    <main className="container h-screen mx-auto px-4 py-8">
      <div className="flex flex-col md:flex-row justify-between items-start md:items-center mb-8 gap-4">
        <h1 className="text-3xl font-bold text-gray-900">Мои уведомления</h1>
        <div className="w-full md:w-auto flex flex-col md:flex-row gap-3">
          <NotificationSort
            sortField={sortField}
            sortOrder={sortOrder}
            onSortChange={handleSortChange}
          />
        </div>
      </div>
      <div id="notification-list" className="space-y-2">
        {notificationPage.items.map((notification) => {
          const channelInfo = channelMap[notification.channel] || {
            icon: "fas fa-question",
            class: "bg-gray-200 text-gray-500",
            label: notification.channel,
          };
          const statusInfo = statusMap[notification.status] || {
            class: "bg-gray-300",
            label: notification.status,
          };
          return (
            <UserNotificationItem
              key={notification.id}
              notification={notification}
              channelInfo={channelInfo}
              statusInfo={statusInfo}
            />
          );
        })}
      </div>
      <Pagination
        page={currentPage}
        pageSize={
          notificationPage.pageSize ?? DEFAULT_PAGINATION_OPTIONS.PAGE_SIZE
        }
        totalPages={notificationPage.totalPages || 1}
        totalElements={notificationPage.totalElements || 0}
        onPageChange={handlePageChange}
      />
    </main>
  );
};

export default UserNotificationsPage;
