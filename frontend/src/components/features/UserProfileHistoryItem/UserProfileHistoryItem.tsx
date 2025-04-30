import React from "react";
import { formatDistanceToNow } from "date-fns";
import { ru } from "date-fns/locale";
import { StatusHistoryDto } from "../../../api/user/dto/StatusHistoryDto";
import "./UserProfileHistoryItem.css";

interface UserProfileHistoryItemProps {
  item: StatusHistoryDto;
  statusIcons: Record<string, string>;
  statusColors: Record<string, string>;
  getStatusLabel: (status: string) => string;
}

const UserProfileHistoryItem: React.FC<UserProfileHistoryItemProps> = ({
  item,
  statusIcons,
  statusColors,
  getStatusLabel,
}) => (
  <div className="profile-history-item relative pl-12" key={item.id}>
    <div
      className={`absolute left-0 top-0 w-8 h-8 rounded-full flex items-center justify-center ${
        statusColors[item.newStatus] || "bg-blue-100 text-blue-500"
      }`}
    >
      <i className={statusIcons[item.newStatus] || "fas fa-user-edit"}></i>
    </div>
    <div className="bg-gray-50 p-4 rounded-lg">
      <div className="flex justify-between items-start mb-1">
        <p className="font-medium text-gray-800">
          {getStatusLabel(item.newStatus)}
        </p>
        <span className="text-xs text-gray-400">
          {formatDistanceToNow(new Date(item.eventAt), {
            addSuffix: true,
            locale: ru,
          })}
        </span>
      </div>
      <p className="text-sm text-gray-600">{item.reason}</p>
    </div>
  </div>
);

export default UserProfileHistoryItem;
