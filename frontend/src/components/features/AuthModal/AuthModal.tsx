import React, { useState } from "react";
import "./AuthModal.css";

interface AuthModalProps {
  isOpen: boolean;
  onClose: () => void;
}

const AuthModal: React.FC<AuthModalProps> = ({ isOpen, onClose }) => {
  const [activeTab, setActiveTab] = useState<"login" | "register">("login");

  if (!isOpen) return null;

  return (
    <div
      className={`modal fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-20 ${
        isOpen ? "active" : ""
      }`}
    >
      <div className="bg-white rounded-lg shadow-xl w-full max-w-md mx-4">
        <div className="flex border-b">
          <button
            className={`form-tab flex-1 py-3 font-medium text-gray-700 hover:bg-green-600 hover:text-white ${
              activeTab === "login" ? "bg-green-500 active" : ""
            }`}
            onClick={() => setActiveTab("login")}
          >
            Вход
          </button>
          <button
            className={`form-tab flex-1 py-3 font-medium text-gray-700 hover:bg-green-600 hover:text-white ${
              activeTab === "register" ? "bg-green-500 active" : ""
            }`}
            onClick={() => setActiveTab("register")}
          >
            Регистрация
          </button>
        </div>

        {activeTab === "login" && (
          <form id="login-form" className="p-6">
            <div className="mb-4">
              <label
                htmlFor="login-email"
                className="block text-sm font-medium text-gray-700 mb-1"
              >
                Телефон или email
              </label>
              <input
                type="text"
                id="login-email"
                className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-green-500"
                required
              />
            </div>
            <div className="mb-4">
              <label
                htmlFor="login-password"
                className="block text-sm font-medium text-gray-700 mb-1"
              >
                Пароль
              </label>
              <input
                type="password"
                id="login-password"
                className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-green-500"
                required
              />
            </div>
            <div className="mb-4 flex items-center">
              <input
                type="checkbox"
                id="remember-me"
                className="h-4 w-4 text-green-500 focus:ring-green-600 border-gray-300 rounded"
              />
              <label
                htmlFor="remember-me"
                className="ml-2 block text-sm text-gray-700"
              >
                Запомнить меня
              </label>
            </div>
            <button
              type="submit"
              className="w-full bg-green-500 text-white py-2 px-4 rounded-md hover:bg-green-600 focus:outline-none focus:ring-2 focus:ring-green-500 focus:ring-offset-2"
            >
              Войти
            </button>
            <div className="mt-4 text-center">
              <a
                href="#"
                className="text-sm text-green-500 hover:text-green-600"
              >
                Забыли пароль?
              </a>
            </div>
          </form>
        )}

        {activeTab === "register" && (
          <form id="register-form" className="p-6">
            <div className="mb-4">
              <label
                htmlFor="register-firstname"
                className="block text-sm font-medium text-gray-700 mb-1"
              >
                Имя
              </label>
              <input
                type="text"
                id="register-firstname"
                className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-green-500"
                required
              />
            </div>
            <div className="mb-4">
              <label
                htmlFor="register-lastname"
                className="block text-sm font-medium text-gray-700 mb-1"
              >
                Фамилия
              </label>
              <input
                type="text"
                id="register-lastname"
                className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-green-500"
                required
              />
            </div>
            <div className="mb-4">
              <label
                htmlFor="register-email"
                className="block text-sm font-medium text-gray-700 mb-1"
              >
                Email
              </label>
              <input
                type="email"
                id="register-email"
                className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-green-500"
                required
              />
            </div>
            <div className="mb-4">
              <label
                htmlFor="register-phone"
                className="block text-sm font-medium text-gray-700 mb-1"
              >
                Телефон
              </label>
              <input
                type="tel"
                id="register-phone"
                className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-green-500"
                required
              />
            </div>
            <div className="mb-4">
              <label
                htmlFor="register-password"
                className="block text-sm font-medium text-gray-700 mb-1"
              >
                Пароль
              </label>
              <input
                type="password"
                id="register-password"
                className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-green-500"
                required
              />
            </div>
            <div className="mb-4">
              <label
                htmlFor="register-confirm-password"
                className="block text-sm font-medium text-gray-700 mb-1"
              >
                Подтвердите пароль
              </label>
              <input
                type="password"
                id="register-confirm-password"
                className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-green-500"
                required
              />
            </div>
            <div className="mb-4 flex items-center">
              <input
                type="checkbox"
                id="terms"
                className="h-4 w-4 text-bg-green-500 focus:ring-green-500 border-gray-300 rounded"
                required
              />
              <label
                htmlFor="terms"
                className="ml-2 block text-sm text-gray-700"
              >
                Я согласен с{" "}
                <a href="#" className="text-green-500 hover:text-green-500">
                  условиями использования
                </a>
              </label>
            </div>
            <button
              type="submit"
              className="w-full bg-green-500 text-white py-2 px-4 rounded-md hover:bg-green-700 focus:outline-none focus:ring-2 focus:ring-green-500 focus:ring-offset-2"
            >
              Зарегистрироваться
            </button>
          </form>
        )}

        <div className="p-4 text-center">
          <button
            id="close-auth-modal"
            className="text-gray-500 hover:text-gray-700"
            onClick={() => onClose()}
          >
            <i className="fas fa-times">Закрыть</i>
          </button>
        </div>
      </div>
    </div>
  );
};

export default AuthModal;
