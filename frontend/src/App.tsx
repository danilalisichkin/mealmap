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
import { AuthProvider } from "./contexts/AuthContext";
import FaqPage from "./pages/FaqPage/FaqPage";
import GreetingPage from "./pages/GreetingPage/GreetingPage";

const App: React.FC = () => {
  return (
    <AuthProvider>
      <BrowserRouter>
        <div className="w-full min-h-screen flex flex-col">
          <Header title="Meal Map" />
          <Routes>
            <Route path="" element={<GreetingPage />} />
            <Route path="/faq" element={<FaqPage />} />
            <Route path="/catalog" element={<CatalogPage />} />
            <Route path="/catalog/products/:id" element={<ProductPage />} />
            <Route path="/user/:userId/profile" element={<UserProfilePage />} />
            <Route path="/user/:userId/health" element={<UserHealthPage />} />
            <Route path="/user/:userId/orders" element={<UserOrdersPage />} />
            <Route
              path="/user/:userId/notifications"
              element={<UserNotificationsPage />}
            />
            <Route path="/cart/checkout" element={<CartCheckoutPage />} />
          </Routes>
          <Footer />
        </div>
      </BrowserRouter>
    </AuthProvider>
  );
};

export default App;
