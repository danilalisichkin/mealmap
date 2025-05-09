import React, { useEffect, useState } from "react";
import "./AuthModal.css";
import LoginForm from "../../commons/LoginForm/LoginForm";
import RegisterForm from "../../commons/RegisterForm/RegisterForm";

interface AuthModalProps {
  isOpen: boolean;
  onClose: () => void;
}

const AuthModal: React.FC<AuthModalProps> = ({ isOpen, onClose }) => {
  const [activeTab, setActiveTab] = useState<"login" | "register">("login");
  const [isVisible, setIsVisible] = useState(false);

  useEffect(() => {
    if (isOpen) {
      setIsVisible(true)
    } else {
      const timeout = setTimeout(() => setIsVisible(false), 1000);
      return () => clearTimeout(timeout);
    }
  }, [isOpen]);

  if (!isVisible) return null;

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

        <LoginForm isOpen={activeTab === "login"} onClose={() => onClose()} />

        <RegisterForm
          isOpen={activeTab === "register"}
          onClose={() => onClose()}
        />

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
