import React from "react";
import { formatDistanceToNow } from "date-fns";
import { ru } from "date-fns/locale";
import { NotificationDto } from "../../../api/notification/dto/NotificationDto";
import { NotificationStatus } from "../../../api/notification/enums/NotificationStatus";

interface UserNotificationItemProps {
  notification: NotificationDto;
  channelInfo: { icon: string; class: string; label: string };
  statusInfo: { class: string; label: string };
}

const UserNotificationItem: React.FC<UserNotificationItemProps> = ({
  notification,
  channelInfo,
  statusInfo,
}) => (
  <div
    className={`p-3 bg-white rounded-lg shadow-sm ${
      notification.status !== NotificationStatus.SENT
        ? "notification-item unread"
        : "notification-item"
    }`}
  >
    <div className="flex items-start">
      <div className={`channel-icon ${channelInfo.class}`}>
        <i className={`${channelInfo.icon} text-sm`}></i>
      </div>
      <div className="flex-1">
        <div className="flex justify-between items-start">
          <h4 className="font-medium text-gray-800">{notification.subject}</h4>
          <span className="text-xs text-gray-400">
            {formatDistanceToNow(new Date(notification.sentAt), {
              addSuffix: true,
              locale: ru,
            })}
          </span>
        </div>
        <p className="text-sm text-gray-600 mt-1">{notification.message}</p>
        <div className="mt-2 flex items-center text-xs text-gray-500">
          <span className={`status-dot ${statusInfo.class} mr-2`}></span>
          <span>
            {statusInfo.label} через {channelInfo.label}
          </span>
        </div>
      </div>
      {notification.status !== NotificationStatus.SENT && (
        <div className="ml-2 w-2 h-2 rounded-full bg-green-500"></div>
      )}
    </div>
  </div>
);

export default UserNotificationItem;
