import React, { useState } from "react";
import { UserRegisterDto } from "../../../api/auth/dto/UserRegisterDto";
import { AuthApi } from "../../../api/auth/AuthApi";
import { useAuth } from "../../../contexts/AuthContext";
import { Role } from "../../../api/auth/enums/Role";

interface RegisterFormProps {
  isOpen: boolean;
  onClose: () => void;
}

const RegisterForm: React.FC<RegisterFormProps> = ({ isOpen, onClose }) => {
  const [registerData, setRegisterData] = useState<UserRegisterDto>({
    phoneNumber: "",
    email: "",
    password: "",
    firstName: "",
    lastName: "",
    organizationId: 1, //TODO: allow to choose
    role: Role.CUSTOMER, //TODO: allow to choose
  });

  const [confirmPassword, setConfirmPassword] = useState("");
  const [error, setError] = useState<string | null>(null);
  const { setToken } = useAuth();

  if (!isOpen) return null;

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { id, value } = e.target;
    setRegisterData((prev) => ({
      ...prev,
      [id]: value,
    }));
  };

  const handleConfirmPasswordChange = (
    e: React.ChangeEvent<HTMLInputElement>
  ) => {
    setConfirmPassword(e.target.value);
  };

  const handleRegisterSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    if (registerData.password !== confirmPassword) {
      setError("Пароли не совпадают");
      return;
    }

    try {
      const user = await AuthApi.signUp(registerData);
      console.log("Пользователь успешно зарегистрирован:", user);

      const token = await AuthApi.signIn({
        identifier: registerData.email,
        password: registerData.password,
      });

      setToken(token);
      onClose();
    } catch (err: any) {
      console.error("Ошибка регистрации:", err);
      setError("Ошибка регистрации. Попробуйте снова.");
    }
  };

  return (
    <form id="register-form" className="p-6" onSubmit={handleRegisterSubmit}>
      <h2 className="text-xl font-bold mb-4">Регистрация</h2>
      {error && <p className="text-red-500 mb-4">{error}</p>}
      <div className="mb-4">
        <label
          htmlFor="firstName"
          className="block text-sm font-medium text-gray-700 mb-1"
        >
          Имя
        </label>
        <input
          type="text"
          id="firstName"
          value={registerData.firstName}
          onChange={handleInputChange}
          className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-green-500"
          required
        />
      </div>
      <div className="mb-4">
        <label
          htmlFor="lastName"
          className="block text-sm font-medium text-gray-700 mb-1"
        >
          Фамилия
        </label>
        <input
          type="text"
          id="lastName"
          value={registerData.lastName}
          onChange={handleInputChange}
          className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-green-500"
          required
        />
      </div>
      <div className="mb-4">
        <label
          htmlFor="email"
          className="block text-sm font-medium text-gray-700 mb-1"
        >
          Email
        </label>
        <input
          type="email"
          id="email"
          value={registerData.email}
          onChange={handleInputChange}
          className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-green-500"
          required
        />
      </div>
      <div className="mb-4">
        <label
          htmlFor="phoneNumber"
          className="block text-sm font-medium text-gray-700 mb-1"
        >
          Телефон
        </label>
        <input
          type="tel"
          id="phoneNumber"
          value={registerData.phoneNumber}
          onChange={handleInputChange}
          className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-green-500"
          required
        />
      </div>
      <div className="mb-4">
        <label
          htmlFor="password"
          className="block text-sm font-medium text-gray-700 mb-1"
        >
          Пароль
        </label>
        <input
          type="password"
          id="password"
          value={registerData.password}
          onChange={handleInputChange}
          className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-green-500"
          required
        />
      </div>
      <div className="mb-4">
        <label
          htmlFor="confirmPassword"
          className="block text-sm font-medium text-gray-700 mb-1"
        >
          Подтвердите пароль
        </label>
        <input
          type="password"
          id="confirmPassword"
          value={confirmPassword}
          onChange={handleConfirmPasswordChange}
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
        <label htmlFor="terms" className="ml-2 block text-sm text-gray-700">
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
  );
};

export default RegisterForm;
