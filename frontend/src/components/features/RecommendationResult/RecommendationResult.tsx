import React from "react";
import { UserRecommendationDto } from "../../../api/recommendation/dto/UserRecommendationDto";
import ProductCard from "../ProductCard/ProductCard";
import { ProductDto } from "../../../api/product/dto/ProductDto";
import "./RecommendationResult.css";
import { PreferenceType } from "../../../api/preference/enums/PreferenceType";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../../../contexts/AuthContext";
import { CartApi } from "../../../api/cart/UserCartApi";
import { PreferenceApi } from "../../../api/preference/UserPreferenceApi";
import { ProductPreferenceDto } from "../../../api/preference/dto/ProductPreferenceDto";

interface RecommendationsShufflingProps {
  recommendations: UserRecommendationDto;
  recommendedProducts: ProductDto[];
  preferredProducts: ProductPreferenceDto[];
  onRegenerate: () => void;
  onAddToCart: () => void;
}

const RecommendationResult: React.FC<RecommendationsShufflingProps> = ({
  recommendations,
  recommendedProducts,
  preferredProducts,
  onRegenerate,
  onAddToCart,
}) => {
  const navigate = useNavigate();
  const { userId } = useAuth();

  const handleNavigateToProductPage = (productId: number) => {
    const product = recommendedProducts.find(
      (product) => product.id === productId
    );
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

  const handleRemoveFromPreference = async (productId: number) => {
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
    <div id="recommendations-result">
      <div className="ai-message">
        <div className="flex items-start">
          <div className="mr-3 text-green-500">
            <i className="fas fa-robot"></i>
          </div>
          <div>
            <p className="text-sm text-gray-800">{recommendations.message}</p>
          </div>
        </div>
      </div>

      <div className="flex justify-center">
        <button
          className="mt-4 px-4 py-2 bg-gray-100 text-green-700 rounded-full font-medium hover:bg-green-50 transition-all shadow"
          onClick={onRegenerate}
        >
          <i className="fas fa-sync-alt mr-2"></i> Сгенерировать заново
        </button>
      </div>

      <div className="grid grid-cols-2 gap-4 mt-6" id="recommended-products">
        {recommendedProducts.map((product) => (
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

      <div className="mt-6 text-center">
        <button
          className="px-6 py-3 bg-gradient-to-r from-green-500 to-green-600 text-white rounded-full font-medium hover:from-green-600 hover:to-green-700 transition-all shadow-md hover:shadow-lg"
          onClick={onAddToCart}
        >
          <i className="fas fa-basket-shopping mr-2"></i> Добавить все в корзину
        </button>
      </div>
    </div>
  );
};

export default RecommendationResult;
