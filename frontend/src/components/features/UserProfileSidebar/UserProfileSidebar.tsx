import React from "react";
import { UserDto } from "../../../api/user/dto/UserDto";
import "./UserProfileSidebar.css";
import { Role } from "../../../api/user/enums/Role";

interface UserProfileSidebarProps {
  user: UserDto;
  tgChatId?: number;
}

const roles = {
  [Role.OPERATOR]: "оператор сервиса",
  [Role.ADMIN]: "администратор",
  [Role.SUPPLIER]: "поставщик",
  [Role.CUSTOMER]: "клиент сервиса",
};

const UserProfileSidebar: React.FC<UserProfileSidebarProps> = ({
  user,
  tgChatId,
}) => {
  const isTelegramLinked = () => !!tgChatId;

  const handleGoToTelegram = () => {
    if (isTelegramLinked()) {
      //TODO: REDIRECT
      console.log("Redirect to Telegram chat");
    } else {
      //TODO: API CALL, REDIRECT
      console.log("Generate Telegram link");
    }
  };

  return (
    <div className="bg-white rounded-lg shadow-sm p-6 sticky top-24">
      <div className="relative mb-6">
        <div className="w-24 h-24 rounded-full profile-avatar mx-auto flex items-center justify-center text-white text-4xl bg-green-500 font-bold">
          {`${user.firstName?.[0] || ""}${
            user.lastName?.[0] || ""
          }`.toUpperCase()}
        </div>
        <div className="user-status-badge bg-green-500 text-white rounded-full p-1.5 absolute right-4 bottom-4">
          <i className="fas fa-check text-xs"></i>
        </div>
      </div>
      <h2 className="text-xl font-bold text-center text-gray-800 mb-1">
        {user.firstName} {user.lastName}
      </h2>
      <p className="text-gray-500 text-center mb-6">
        {user.roles.map((role) => roles[role] || role).join(", ")}
      </p>
      <div className="space-y-4">
        <div>
          <p className="text-xs text-gray-400 mb-1">Email</p>
          <p className="text-gray-700">{user.email}</p>
        </div>
        <div>
          <p className="text-xs text-gray-400 mb-1">Телефон</p>
          <p className="text-gray-700">+{user.phoneNumber}</p>
        </div>
        <div>
          <p className="text-xs text-gray-400 mb-1">Подключен с</p>
          <p className="text-gray-700">
            {new Date(user.createdAt).toLocaleDateString("ru-RU", {
              year: "numeric",
              month: "long",
              day: "numeric",
            })}
          </p>
        </div>
        <button
          className="text-sm text-blue-500 hover:text-blue-600 flex items-center"
          onClick={handleGoToTelegram}
        >
          <i className="fab fa-telegram mr-1"></i>
          {isTelegramLinked() ? "Перейти в Telegram-чат" : "Привязать Telegram"}
        </button>
      </div>
      <div className="mt-8 pt-6 border-t border-gray-100">
        <button className="w-full bg-green-500 hover:bg-green-600 text-white py-2 px-4 rounded-lg transition flex items-center justify-center">
          <i className="fas fa-pencil-alt mr-2"></i>
          Редактировать
        </button>
      </div>
    </div>
  );
};

export default UserProfileSidebar;
