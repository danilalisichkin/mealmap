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
import ScrollToTop from "./components/commons/ScrollToTop/ScrollToTop";
import CategoryPage from "./pages/CategoryPage/CategoryPage";
import RecommendationPage from "./pages/RecommendationPage/RecommendationPage";
import SupplierRegistrationPage from "./pages/SupplierRegistrationPage/SupplierRegistrationPage";
import ClientRegistrationPage from "./pages/ClientRegistrationPage/ClientRegistrationPage";

const App: React.FC = () => {
  return (
    <AuthProvider>
      <BrowserRouter>
        <ScrollToTop />
        <div className="w-full min-h-screen flex flex-col">
          <Header title="Meal Map" />
          <Routes>
            <Route path="" element={<GreetingPage />} />
            <Route
              path="/ai-recommendation-system"
              element={<RecommendationPage />}
            />
            <Route path="/faq" element={<FaqPage />} />
            <Route path="/catalog" element={<CatalogPage />} />
            <Route path="/catalog/products/:id" element={<ProductPage />} />
            <Route
              path="/catalog/categories/:categoryId"
              element={<CategoryPage />}
            />
            <Route
              path="/client-registration"
              element={<ClientRegistrationPage />}
            />
            <Route
              path="/supplier-registration"
              element={<SupplierRegistrationPage />}
            />
            <Route
              path="/users/:userId/profile"
              element={<UserProfilePage />}
            />
            <Route path="/users/:userId/health" element={<UserHealthPage />} />
            <Route path="/users/:userId/orders" element={<UserOrdersPage />} />
            <Route
              path="/users/:userId/notifications"
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
