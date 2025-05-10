import React from "react";
import { UserRecommendationDto } from "../../../api/recommendation/dto/UserRecommendationDto";
import ProductCard from "../ProductCard/ProductCard";
import { ProductDto } from "../../../api/product/dto/ProductDto";
import { PreferenceType } from "../../../api/preference/enums/PreferenceType";
import { useNavigate } from "react-router-dom";
import { ProductPreferenceDto } from "../../../api/preference/dto/ProductPreferenceDto";
import { RecommendationItem } from "../../../api/recommendation/dto/RecommendationItem";

interface RecommendationListProps {
  recommendations: UserRecommendationDto;
  recommendedProducts: ProductDto[];
  preferredProducts: ProductPreferenceDto[];
  onAddToCart: (item: RecommendationItem) => void;
  onAddToPreference: (
    productId: number,
    preferenceType: PreferenceType
  ) => void;
  onRemoveFromPreference: (productId: number) => void;
}

const RecommendationList: React.FC<RecommendationListProps> = ({
  recommendations,
  recommendedProducts,
  preferredProducts,
  onAddToCart,
  onAddToPreference,
  onRemoveFromPreference,
}) => {
  const navigate = useNavigate();

  const handleNavigateToProductPage = (productId: number) => {
    const product = recommendedProducts.find(
      (product) => product.id === productId
    );
    navigate(`/catalog/products/${productId}`, { state: { product } });
  };

  return (
    <div id="recommendations-result">
      <div
        className="grid grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-3"
        id="recommended-products"
      >
        {recommendedProducts.map((product) => {
          const recommendationItem = recommendations.items.find(
            (item) => item.productId === product.id
          );
          return (
            <div
              className="relative"
              key={`recommendation-${recommendationItem?.productId}-${recommendationItem?.quantity}`}
            >
              <ProductCard
                key={product.id}
                product={product}
                preferenceType={
                  preferredProducts.find(
                    (pref) => pref.productId === product.id
                  )?.preferenceType ?? null
                }
                onAddToCart={() => onAddToCart(recommendationItem!)}
                onNavigateToProductPage={() =>
                  handleNavigateToProductPage(product.id)
                }
                onAddToPreference={(preferenceType: PreferenceType) =>
                  onAddToPreference(product.id, preferenceType)
                }
                onRemoveFromPreference={() =>
                  onRemoveFromPreference(product.id)
                }
              />
              {recommendationItem && recommendationItem.quantity > 0 && (
                <span className="absolute top-1 left-1 bg-blue-500 text-white text-xs px-1 py-0.5 rounded-full">
                  {recommendationItem.quantity} шт.
                </span>
              )}
            </div>
          );
        })}
      </div>
    </div>
  );
};

export default RecommendationList;
