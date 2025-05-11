import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import AuthModal from "../../features/AuthModal/AuthModal";
import ShoppingCart from "../../features/Cart/Cart";
import { useAuth } from "../../../contexts/AuthContext";
import LogoutModal from "../../features/LogoutModal/LogoutModal";

interface HeaderProps {
  title: string;
}

const Header: React.FC<HeaderProps> = ({ title }) => {
  const { userId, logout } = useAuth();

  const [isAuthModalOpen, setAuthModalOpen] = useState(false);
  const openAuthModal = () => setAuthModalOpen(true);
  const closeAuthModal = () => setAuthModalOpen(false);

  const [isCartOpen, setCartOpen] = useState(false);
  const openCart = () => setCartOpen(true);
  const closeCart = () => setCartOpen(false);

  const [isLogoutModalOpen, setLogoutModalOpen] = useState(false);
  const openLogoutModal = () => setLogoutModalOpen(true);
  const closeLogoutModal = () => setLogoutModalOpen(false);

  const [isMobileMenuOpen, setMobileMenuOpen] = useState(false);
  const toggleMobileMenu = () => setMobileMenuOpen((prev) => !prev);

  const [isUserMenuOpen, setUserMenuOpen] = useState(false);
  const toggleUserMenu = () => setUserMenuOpen((prev) => !prev);

  const navigate = useNavigate();

  const ITEMS_IN_CART = 0; // TODO: API CALL / FROM LOCAL STORAGE

  const handleLogout = () => {
    logout();
    closeLogoutModal();
    navigate("/");
  };

  return (
    <header className="bg-white shadow-sm sticky top-0 z-10">
      {/* Desktop Header */}
      <div className="hidden md:block">
        <DesktopHeader
          title={title}
          userId={userId}
          isUserMenuOpen={isUserMenuOpen}
          toggleUserMenu={toggleUserMenu}
          openAuthModal={openAuthModal}
          openCart={openCart}
          openLogoutModal={openLogoutModal}
          ITEMS_IN_CART={ITEMS_IN_CART}
        />
      </div>

      {/* Mobile Header */}
      <div className="block md:hidden">
        <MobileHeader
          title={title}
          userId={userId}
          ITEMS_IN_CART={ITEMS_IN_CART}
          isMobileMenuOpen={isMobileMenuOpen}
          openCart={openCart}
          toggleMobileMenu={toggleMobileMenu}
          openAuthModal={openAuthModal}
          openLogoutModal={openLogoutModal}
        />
      </div>

      <AuthModal isOpen={isAuthModalOpen} onClose={closeAuthModal} />
      <LogoutModal
        isOpen={isLogoutModalOpen}
        onClose={closeLogoutModal}
        onConfirm={handleLogout}
      />

      <ShoppingCart isOpened={isCartOpen} onClose={closeCart} />
    </header>
  );
};

const DesktopHeader: React.FC<{
  title: string;
  userId: string | null;
  isUserMenuOpen: boolean;
  toggleUserMenu: () => void;
  openAuthModal: () => void;
  openCart: () => void;
  openLogoutModal: () => void;
  ITEMS_IN_CART: number;
}> = ({
  title,
  userId,
  isUserMenuOpen,
  toggleUserMenu,
  openAuthModal,
  openCart,
  openLogoutModal,
  ITEMS_IN_CART,
}) => {
  return (
    <nav
      className="container mx-auto px-4 py-3 flex justify-between items-center"
      aria-label="Главная навигация"
    >
      {/* Title */}
      <div className="flex items-center space-x-2">
        <span className="w-10 h-10 rounded-full bg-green-500 flex items-center justify-center">
          <i className="fas fa-utensils text-white text-xl"></i>
        </span>
        <span className="text-xl font-bold text-green-500">{title}</span>
      </div>

      {/* Navigation Links */}
      <div className="flex items-center space-x-6">
        <Link to="/" className="text-gray-700 hover:text-green-500 transition">
          <i className="fas fa-home text-lg mr-1"></i> Главная
        </Link>
        {userId && (
          <>
            <Link
              to="/catalog"
              className="text-gray-700 hover:text-green-500 transition"
            >
              <i className="fas fa-th-large text-lg mr-1"></i> Каталог
            </Link>
            <Link
              to="/ai-recommendation-system"
              className="text-gray-700 hover:text-green-500 transition"
            >
              <i className="fas fa-robot text-lg mr-1"></i>ИИ-рекомендации
            </Link>
          </>
        )}
        <Link
          to="/faq"
          className="text-gray-700 hover:text-green-500 transition"
        >
          <i className="fas fa-question-circle text-lg mr-1"></i> FAQ
        </Link>
      </div>

      {/* Cart and User Info */}
      <div className="flex items-center space-x-4">
        {userId ? (
          <>
            {/* Cart */}
            <button
              className="relative text-gray-700 hover:text-green-500 transition flex items-center"
              onClick={openCart}
            >
              <i className="fas fa-shopping-cart text-lg mr-1"></i> Корзина
              {ITEMS_IN_CART > 0 && (
                <span className="absolute badge bg-orange-500 text-white rounded-full w-5 h-5 flex items-center justify-center">
                  {ITEMS_IN_CART}
                </span>
              )}
            </button>

            {/* User Info */}
            <div className="relative">
              <div className="relative" onMouseEnter={() => toggleUserMenu()}>
                <button className="text-gray-700 hover:text-green-500 transition flex items-center">
                  <i className="fas fa-user text-lg mr-1"></i> Профиль
                </button>
                {isUserMenuOpen && (
                  <div
                    className="absolute right-0 mt-2 w-48 bg-white rounded-md shadow-lg py-1 z-20"
                    onMouseEnter={() => toggleUserMenu()}
                    onMouseLeave={() => toggleUserMenu()}
                  >
                    <Link
                      to={`/users/${userId}/profile`}
                      className="block px-4 py-2 text-gray-700 hover:bg-green-500 hover:text-white"
                    >
                      <i className="fas fa-user text-lg mr-1"></i> Мой профиль
                    </Link>
                    <Link
                      to={`/users/${userId}/orders`}
                      className="block px-4 py-2 text-gray-700 hover:bg-green-500 hover:text-white"
                    >
                      <i className="fas fa-clipboard-list text-lg mr-1"></i> Мои
                      заказы
                    </Link>
                    <Link
                      to={`/users/${userId}/notifications`}
                      className="block px-4 py-2 text-gray-700 hover:bg-green-500 hover:text-white"
                    >
                      <i className="fas fa-bell text-lg mr-1"></i> Уведомления
                    </Link>
                    <div className="border-t border-gray-200 my-1"></div>
                    <button
                      className="block w-full text-left px-4 py-2 text-gray-700 hover:bg-green-500 hover:text-white"
                      onClick={openLogoutModal}
                    >
                      <i className="fas fa-sign-out-alt text-lg mr-1"></i> Выйти
                    </button>
                  </div>
                )}
              </div>
            </div>
          </>
        ) : (
          <button
            className="text-gray-700 hover:text-green-500 transition flex items-center"
            onClick={openAuthModal}
          >
            <i className="fas fa-sign-in-alt text-lg mr-1"></i> Войти
          </button>
        )}
      </div>
    </nav>
  );
};

const MobileHeader: React.FC<{
  title: string;
  userId: string | null;
  ITEMS_IN_CART: number;
  isMobileMenuOpen: boolean;
  openCart: () => void;
  toggleMobileMenu: () => void;
  openAuthModal: () => void;
  openLogoutModal: () => void;
}> = ({
  title,
  userId,
  ITEMS_IN_CART,
  isMobileMenuOpen,
  openCart,
  toggleMobileMenu,
  openAuthModal,
  openLogoutModal,
}) => {
  return (
    <>
      <div className="container mx-auto px-4 py-3 flex justify-between items-center">
        {/* Title */}
        <div className="flex items-center space-x-2">
          <span className="w-10 h-10 rounded-full bg-green-500 flex items-center justify-center">
            <i className="fas fa-utensils text-white text-xl"></i>
          </span>
          <span className="text-xl font-bold text-green-500">{title}</span>
        </div>

        {/* Cart */}
        {userId && (
          <button
            className="relative text-gray-700 hover:text-green-500 transition flex items-center"
            onClick={openCart}
          >
            <i className="fas fa-shopping-cart text-lg mr-1"></i> Корзина
            {ITEMS_IN_CART > 0 && (
              <span className="absolute badge bg-orange-500 text-white rounded-full w-5 h-5 flex items-center justify-center">
                {ITEMS_IN_CART}
              </span>
            )}
          </button>
        )}

        {/* Hamburger Menu */}
        <button
          className="text-gray-700 hover:text-green-500 transition"
          onClick={toggleMobileMenu}
        >
          <i className="fas fa-bars text-xl"></i>
        </button>
      </div>

      {/* Mobile Menu */}
      {isMobileMenuOpen && (
        <div className="fixed inset-0 bg-black bg-opacity-50 z-50">
          <div className="mobile-menu fixed inset-y-0 right-0 w-64 bg-white shadow-lg z-50 overflow-y-auto">
            <div className="p-4">
              {/* Close Button */}
              <div className="flex justify-end mb-4">
                <button
                  className="text-gray-700 hover:text-green-500 transition"
                  onClick={toggleMobileMenu}
                >
                  <i className="fas fa-times text-xl"></i>
                </button>
              </div>

              {/* User Info */}
              {userId ? (
                <div className="py-4 border-b border-gray-200">
                  <p className="font-medium">Профиль</p>
                  <p className="text-sm text-gray-500">Авторизован</p>
                </div>
              ) : (
                <div className="py-4 border-b border-gray-200">
                  <p className="font-medium">Гость</p>
                  <p className="text-sm text-gray-500">Не авторизован</p>
                </div>
              )}

              {userId && (
                <div className="py-4 border-b border-gray-200">
                  <ul className="space-y-2">
                    <li>
                      <Link
                        to={`/users/${userId}/profile`}
                        className="flex items-center px-3 py-2 rounded-lg text-gray-700 hover:bg-gray-100"
                        onClick={() => toggleMobileMenu()}
                      >
                        <i className="fas fa-user mr-3 text-gray-500"></i>
                        <span>Мой профиль</span>
                      </Link>
                      <Link
                        to={`/users/${userId}/orders`}
                        className="flex items-center px-3 py-2 rounded-lg text-gray-700 hover:bg-gray-100"
                        onClick={() => toggleMobileMenu()}
                      >
                        <i className="fas fa-clipboard-list mr-3 text-gray-500"></i>{" "}
                        <span>Мои заказы</span>
                      </Link>
                      <Link
                        to={`/users/${userId}/notifications`}
                        className="flex items-center px-3 py-2 rounded-lg text-gray-700 hover:bg-gray-100"
                        onClick={() => toggleMobileMenu()}
                      >
                        <i className="fas fa-bell mr-3 text-gray-500"></i>
                        <span>Уведомления</span>
                      </Link>
                    </li>
                  </ul>
                </div>
              )}

              {/* Menu */}
              <nav className="py-4">
                <ul className="space-y-2">
                  <li>
                    <Link
                      to="/"
                      className="flex items-center px-3 py-2 rounded-lg text-gray-700 hover:bg-gray-100"
                      onClick={() => toggleMobileMenu()}
                    >
                      <i className="fas fa-home mr-3 text-gray-500"></i>
                      <span>Главная</span>
                    </Link>
                  </li>
                  <li>
                    <Link
                      to="/faq"
                      className="flex items-center px-3 py-2 rounded-lg text-gray-700 hover:bg-gray-100"
                      onClick={() => toggleMobileMenu()}
                    >
                      <i className="fas fa-question-circle mr-3 text-gray-500"></i>
                      <span>FAQ</span>
                    </Link>
                  </li>
                  {userId && (
                    <>
                      <li>
                        <Link
                          to="/catalog"
                          className="flex items-center px-3 py-2 rounded-lg text-gray-700 hover:bg-gray-100"
                          onClick={() => toggleMobileMenu()}
                        >
                          <i className="fas fa-th-large mr-3 text-gray-500"></i>
                          <span>Каталог</span>
                        </Link>
                      </li>
                      <li>
                        <Link
                          to="/ai-recommendation-system"
                          className="flex items-center px-3 py-2 rounded-lg text-gray-700 hover:bg-gray-100"
                        >
                          <i className="fas fa-robot mr-3 text-gray-500"></i>
                          <span>ИИ-рекомендации</span>
                        </Link>
                      </li>
                    </>
                  )}
                  {!userId ? (
                    <li>
                      <button
                        className="w-full flex items-center px-3 py-2 rounded-lg text-white bg-green-500 hover:bg-green-600"
                        onClick={() => {
                          openAuthModal();
                          toggleMobileMenu();
                        }}
                      >
                        <i className="fas fa-sign-in-alt mr-3"></i>
                        <span>Войти</span>
                      </button>
                    </li>
                  ) : (
                    <li>
                      <button
                        className="w-full flex items-center px-3 py-2 rounded-lg text-white bg-green-500 hover:bg-green-600"
                        onClick={() => {
                          openLogoutModal();
                          toggleMobileMenu();
                        }}
                      >
                        <i className="fas fa-sign-out-alt mr-3"></i>
                        <span>Выйти</span>
                      </button>
                    </li>
                  )}
                </ul>
              </nav>
            </div>
          </div>
        </div>
      )}
    </>
  );
};

export default Header;
