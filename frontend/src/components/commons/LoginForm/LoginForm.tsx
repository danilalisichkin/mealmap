import React, { useState } from "react";
import { UserLoginDto } from "../../../api/auth/dto/UserLoginDto";
import { AuthApi } from "../../../api/auth/AuthApi";
import { useAuth } from "../../../contexts/AuthContext";

interface LoginFormProps {
  isOpen: boolean;
  onClose: () => void;
}

const LoginForm: React.FC<LoginFormProps> = ({ isOpen, onClose }) => {
  const [loginData, setLoginData] = useState<UserLoginDto>({
    identifier: "",
    password: "",
  });

  const { setAuth } = useAuth();

  if (!isOpen) return null;

  const handleLoginSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    try {
      const tokens = await AuthApi.signIn(loginData);
      console.log("Получены токены:", tokens);

      setAuth(tokens);

      onClose();
    } catch (err: any) {
      console.error("Ошибка авторизации:", err);
    }
  };

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { id, value } = e.target;
    setLoginData((prev) => ({
      ...prev,
      [id]: value,
    }));
  };

  return (
    <form id="login-form" className="p-6" onSubmit={handleLoginSubmit}>
      <div className="mb-4">
        <label
          htmlFor="identifier"
          className="block text-sm font-medium text-gray-700 mb-1"
        >
          Телефон или email
        </label>
        <input
          type="text"
          id="identifier"
          value={loginData.identifier}
          onChange={handleInputChange}
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
          id="password"
          value={loginData.password}
          onChange={handleInputChange}
          className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-green-500"
          required
        />
      </div>
      <button
        type="submit"
        className="w-full bg-green-500 text-white py-2 px-4 rounded-md hover:bg-green-600 focus:outline-none focus:ring-2 focus:ring-green-500 focus:ring-offset-2"
      >
        Войти
      </button>
      <div className="mt-4 text-center">
        <a href="#" className="text-sm text-green-500 hover:text-green-600">
          Забыли пароль?
        </a>
      </div>
    </form>
  );
};

export default LoginForm;
