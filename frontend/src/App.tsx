import React from "react";
import Header from "./components/layout/Header/Header";
import ProductPage from "./pages/ProductPage/ProductPage";
import Footer from "./components/layout/Footer/Footer";
import CartCheckoutPage from "./pages/CartCheckoutPage/CartCheckoutPage";
import UserOrdersPage from "./pages/UserOrdersPage/UserOrdersPage";
import UserProfilePage from "./pages/UserProfilePage/UserProfilePage";
import UserNotificationsPage from "./pages/UserNotificationsPage/UserNotificationsPage";
import CatalogPage from "./pages/CatalogPage/CatalogPage";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import UserHealthPage from "./pages/UserHealthPage/UserHealthPage";

const App: React.FC = () => {
  return (
    <BrowserRouter>
      <div className="w-full min-h-screen flex flex-col">
        <Header title="Meal Map" />
        <Routes>
          <Route path="/" element={<CatalogPage />} />
          <Route path="/profile" element={<UserProfilePage />} />
          <Route path="/user/health" element={<UserHealthPage />} />
          <Route path="/orders" element={<UserOrdersPage />} />
          <Route path="/cart/checkout" element={<CartCheckoutPage />} />
          <Route path="/notifications" element={<UserNotificationsPage />} />
          <Route path="/product/:id" element={<ProductPage />} />
        </Routes>
        <Footer />
      </div>
    </BrowserRouter>
  );
};

export default App;
