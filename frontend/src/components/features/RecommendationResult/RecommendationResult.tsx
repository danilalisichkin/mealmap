import React from "react";
import { UserRecommendationDto } from "../../../api/recommendation/dto/UserRecommendationDto";
import ProductCard from "../ProductCard/ProductCard";
import { ProductDto } from "../../../api/product/dto/ProductDto";
import "./RecommendationResult.css";

interface RecommendationsShufflingProps {
  recommendations: UserRecommendationDto;
  recommendedProducts: ProductDto[];
  onRegenerate: () => void;
  onAddToCart: () => void;
}

const RecommendationResult: React.FC<RecommendationsShufflingProps> = ({
  recommendations,
  recommendedProducts,
  onRegenerate,
  onAddToCart,
}) => (
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
        <ProductCard product={product} key={product.id} />
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

export default RecommendationResult;
