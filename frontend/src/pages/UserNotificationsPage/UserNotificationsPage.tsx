import React from "react";
import { NotificationStatus } from "../../api/notification/enums/NotificationStatus";
import { Channel } from "../../api/notification/enums/Channel";
import "./UserNotificationsPage.css";
import UserNotificationItem from "../../components/features/UserNotificationItem/UserNotificationItem";
import { mockNotifications } from "../../mock/notifications";

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
  // TODO: API CALL
  const notifications = mockNotifications;

  return (
    <main className="container mx-auto px-4 py-2">
      <div className="flex overflow-x-auto mb-4 pb-2 hide-scrollbar">
        <button
          className="filter-tab active px-4 py-2 text-sm font-medium whitespace-nowrap"
          data-filter="all"
        >
          Все
        </button>
        <button
          className="filter-tab px-4 py-2 text-sm font-medium whitespace-nowrap"
          data-filter="unread"
        >
          Непрочитанные
        </button>
      </div>

      <div id="notification-list" className="space-y-2">
        {notifications.length > 0 ? (
          notifications.map((notification) => {
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
          })
        ) : (
          <div
            id="empty-state"
            className="flex flex-col items-center justify-center py-10"
          >
            <div className="w-20 h-20 rounded-full bg-gray-100 flex items-center justify-center mb-4">
              <i className="fas fa-bell-slash text-gray-400 text-2xl"></i>
            </div>
            <h3 className="text-lg font-medium text-gray-700 mb-1">
              Нет уведомлений
            </h3>
            <p className="text-gray-500 text-center max-w-xs">
              У вас пока нет уведомлений.
            </p>
          </div>
        )}
      </div>
    </main>
  );
};

export default UserNotificationsPage;
