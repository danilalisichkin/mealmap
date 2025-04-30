import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import AuthModal from "../../features/AuthModal/AuthModal";
import ShoppingCart from "../../features/Cart/Cart";
import { mockCart } from "../../../mock/cart";

interface HeaderProps {
  title: string;
}

const Header: React.FC<HeaderProps> = ({ title }) => {
  const [isAuthModalOpen, setAuthModalOpen] = useState(false);
  const openAuthModal = () => setAuthModalOpen(true);
  const closeAuthModal = () => setAuthModalOpen(false);

  const [isCartOpen, setCartOpen] = useState(false);
  const openCart = () => setCartOpen(true);
  const closeCart = () => setCartOpen(false);

  const navigate = useNavigate();

  return (
    <header className="bg-white shadow-sm sticky top-0 z-10">
      <nav
        className="container mx-auto px-4 py-3 flex justify-between items-center"
        aria-label="Главная навигация"
      >
        <a
          href="/"
          className="flex items-center focus:outline-none"
          aria-label="Главная"
          onClick={(e) => {
            e.preventDefault();
            navigate("/");
          }}
        >
          <span className="w-10 h-10 rounded-full bg-green-500 flex items-center justify-center mr-2">
            <i className="fas fa-utensils text-white text-xl"></i>
          </span>
          <span className="text-xl font-bold text-green-500">{title}</span>
        </a>
        <ul className="flex items-center space-x-4">
          <li>
            <button className="text-gray-600" aria-label="Поиск">
              <i className="fas fa-search text-lg"></i>
            </button>
          </li>
          <li>
            <button
              className="text-gray-600 relative"
              onClick={openCart}
              aria-label="Корзина"
            >
              <i className="fas fa-shopping-cart text-lg"></i>
              <span className="absolute -top-2 -right-2 bg-orange-500 text-white text-xs rounded-full w-5 h-5 flex items-center justify-center">
                {mockCart.items.length}
              </span>
            </button>
          </li>
          <li>
            <button
              className="text-gray-600"
              onClick={() => navigate("/orders")}
              aria-label="Мои заказы"
            >
              <i className="fas fa-list-alt text-lg"></i>
            </button>
          </li>
          <li>
            <button
              className="text-gray-600 relative"
              onClick={() => navigate("/notifications")}
              aria-label="Уведомления"
            >
              <i className="fas fa-bell text-lg"></i>
            </button>
          </li>
          <li>
            <button
              className="text-gray-600"
              onClick={() => navigate("/profile")}
              aria-label="Профиль"
            >
              <i className="fas fa-user-circle text-lg"></i>
            </button>
          </li>
          <li>
            <button
              id="auth-btn"
              className="text-gray-600"
              onClick={openAuthModal}
              aria-label="Вход или регистрация"
            >
              <i className="fas fa-sign-in-alt text-lg"></i>
            </button>
          </li>
        </ul>
      </nav>

      <AuthModal isOpen={isAuthModalOpen} onClose={closeAuthModal} />

      <ShoppingCart data={mockCart} isOpened={isCartOpen} onClose={closeCart} />
    </header>
  );
};

export default Header;
