import React from "react";
import { StatusHistoryDto } from "../../../api/user/dto/StatusHistoryDto";
import { StatusEvent } from "../../../api/user/enums/StatusEvent";
import "./UserProfileHistory.css";
import UserProfileHistoryItem from "../UserProfileHistoryItem/UserProfileHistoryItem";

interface UserProfileHistoryProps {
  history: StatusHistoryDto[];
}

const statusIcons: Record<string, string> = {
  ACTIVATE: "fas fa-user-plus",
  DEACTIVATE: "fas fa-user-slash",
  BLOCK: "fas fa-user-lock",
  TEMPORARY_BLOCK: "fas fa-user-clock",
  UNBLOCK: "fas fa-check",
};

const statusColors: Record<string, string> = {
  ACTIVATE: "bg-green-100 text-green-500",
  DEACTIVATE: "bg-gray-200 text-gray-500",
  BLOCK: "bg-red-100 text-red-500",
  TEMPORARY_BLOCK: "bg-yellow-100 text-yellow-500",
  UNBLOCK: "bg-blue-100 text-blue-500",
};

const statuses = [
  { key: StatusEvent.ACTIVATE, label: "Аккаунт активирован" },
  { key: StatusEvent.DEACTIVATE, label: "Аккаунт деактивирован" },
  { key: StatusEvent.BLOCK, label: "Аккаунт заблокирован" },
  { key: StatusEvent.TEMPORARY_BLOCK, label: "Аккаунт временно заблокирован" },
  { key: StatusEvent.UNBLOCK, label: "Аккаунт разблокирован" },
];

const getStatusLabel = (status: string) =>
  statuses.find((s) => s.key === status)?.label ?? status;

const UserProfileHistory: React.FC<UserProfileHistoryProps> = ({ history }) => (
  <div className="bg-white rounded-lg shadow-sm p-6">
    <h3 className="text-lg font-bold text-gray-800 mb-6">История профиля</h3>
    <div className="space-y-4 relative">
      {history.length === 0 ? (
        <div className="text-left text-gray-500 mt-4">
          История изменения профиля пуста
        </div>
      ) : (
        history.map((item) => (
          <UserProfileHistoryItem
            key={item.id}
            item={item}
            statusIcons={statusIcons}
            statusColors={statusColors}
            getStatusLabel={getStatusLabel}
          />
        ))
      )}
    </div>
  </div>
);

export default UserProfileHistory;
