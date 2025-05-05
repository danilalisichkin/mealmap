import React from "react";
import { ProductDto } from "../../../api/product/dto/ProductDto";
import ProductCard from "../ProductCard/ProductCard";
import "./Catalog.css";
import { useAuth } from "../../../contexts/AuthContext";
import { CartApi } from "../../../api/cart/UserCartApi";
import { useNavigate } from "react-router-dom";
import { PreferenceApi } from "../../../api/preference/UserPreferenceApi";
import { PreferenceType } from "../../../api/preference/enums/PreferenceType";
import { ProductPreferenceDto } from "../../../api/preference/dto/ProductPreferenceDto";

interface CatalogProps {
  products: ProductDto[];
  preferredProducts: ProductPreferenceDto[];
}

const Catalog: React.FC<CatalogProps> = ({ products, preferredProducts }) => {
  const navigate = useNavigate();
  const { userId } = useAuth();

  const handleNavigateToProductPage = (productId: number) => {
    const product = products.find((product) => product.id === productId);
    navigate(`/catalog/products/${productId}`, { state: { product } });
  };

  const handleAddToCart = async (productId: number) => {
    if (!userId) {
      console.error("Пользователь не авторизован");
      return;
    }

    try {
      await CartApi.addItemToCart(userId, { productId, quantity: 1 });
      console.log(`Товар с ID ${productId} добавлен в корзину`);
    } catch (error) {
      console.error("Ошибка при добавлении товара в корзину:", error);
    }
  };

  const handleAddToPreference = async (
    productId: number,
    preferenceType: PreferenceType
  ) => {
    if (!userId) {
      console.error("Пользователь не авторизован");
      return;
    }

    try {
      await PreferenceApi.addProductPreference(userId, {
        productId: productId,
        preferenceType: preferenceType,
      });
      console.log(`Товар с ID ${productId} добавлен в предпочтения`);
    } catch (error) {
      console.error("Ошибка при добавлении товара в предпочтения:", error);
    }
  };

  const handleRemoveFromPreference = async (
    productId: number
  ) => {
    if (!userId) {
      console.error("Пользователь не авторизован");
      return;
    }

    try {
      await PreferenceApi.removeProductPreference(userId, productId);
      console.log(`Товар с ID ${productId} убран из предпочтений`);
    } catch (error) {
      console.error("Ошибка при удалении товара из предпочтений:", error);
    }
  };

  return (
    <div className="grid grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-3">
      {products.map((product) => (
        <ProductCard
          key={product.id}
          product={product}
          preferenceType={
            preferredProducts.find((pref) => pref.productId === product.id)
              ?.preferenceType ?? null
          }
          onAddToCart={() => handleAddToCart(product.id)}
          onNavigateToProductPage={() =>
            handleNavigateToProductPage(product.id)
          }
          onAddToPreference={(preferenceType: PreferenceType) =>
            handleAddToPreference(product.id, preferenceType)
          }
          onRemoveFromPreference={() =>
            handleRemoveFromPreference(product.id)
          }
        />
      ))}
    </div>
  );
};

export default Catalog;
