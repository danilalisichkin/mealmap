import React, { useEffect, useState } from "react";
import { ProductDto } from "../../../api/product/dto/ProductDto";
import "./ProductCard.css";
import { PreferenceType } from "../../../api/preference/enums/PreferenceType";

interface ProductCardProps {
  product: ProductDto;
  preferenceType: PreferenceType | null;
  onAddToCart: () => void;
  onNavigateToProductPage: () => void;
  onAddToPreference: (preferenceType: PreferenceType) => void;
  onRemoveFromPreference: () => void;
}

const ProductCard: React.FC<ProductCardProps> = ({
  product,
  preferenceType,
  onAddToCart,
  onNavigateToProductPage,
  onAddToPreference,
  onRemoveFromPreference,
}) => {
  const [currentPreferenceType, setCurrentPreferenceType] =
    useState<PreferenceType | null>(preferenceType);

  const [isMounted, setIsMounted] = useState(false);

  useEffect(() => {
    setCurrentPreferenceType(preferenceType);
  }, [preferenceType]);

  useEffect(() => {
    const timeout = setTimeout(() => setIsMounted(true), 50);
    return () => clearTimeout(timeout);
  }, []);

  const handleAddToPreference = (type: PreferenceType) => {
    if (currentPreferenceType === type) {
      onRemoveFromPreference();
      setCurrentPreferenceType(null);
    } else {
      setCurrentPreferenceType(type);
      onAddToPreference(type);
    }
  };

  return (
    <a
      className={`dish-card bg-white rounded-lg shadow-sm overflow-hidden cursor-pointer z-100
      ${
        isMounted
          ? "transition-transform duration-700 ease-out translate-y-0 scale-100"
          : "translate-y-4 scale-95"
      }`}
      href={`/product/${product.id}`}
      onClick={(e) => {
        e.preventDefault();
        onNavigateToProductPage();
      }}
    >
      <div className="relative">
        <div className="action-buttons">
          {currentPreferenceType !== PreferenceType.DISLIKED && (
            <button
              className={`like-btn bg-white p-1 rounded-full shadow-md transition z-5 ${
                currentPreferenceType === PreferenceType.LIKED ? "active" : ""
              }`}
              onClick={(e) => {
                e.preventDefault();
                e.stopPropagation();
                handleAddToPreference(PreferenceType.LIKED);
              }}
            >
              <i className="far fa-thumbs-up"></i>
            </button>
          )}
          {currentPreferenceType !== PreferenceType.LIKED && (
            <button
              className={`dislike-btn bg-white p-1 rounded-full shadow-md transition z-5 ${
                currentPreferenceType === PreferenceType.DISLIKED
                  ? "active"
                  : ""
              }`}
              onClick={(e) => {
                e.preventDefault();
                e.stopPropagation();
                handleAddToPreference(PreferenceType.DISLIKED);
              }}
            >
              <i className="far fa-thumbs-down"></i>
            </button>
          )}
        </div>
        <div className="dish-image bg-gray-200 flex items-center justify-center">
          <img
            src="https://images.unsplash.com/photo-1546069901-ba9599a7e63c?ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=80"
            alt={product.name}
            className="w-full h-full object-cover"
          />
        </div>
        {product.isNew && (
          <span className="absolute top-1 right-1 bg-orange-500 text-white text-xs px-1 py-0.5 rounded-full">
            новинка
          </span>
        )}
      </div>
      <div className="p-2">
        <div className="flex justify-between items-start mb-2">
          <h3 className="text-sm font-semibold text-gray-800 truncate">
            {product.name}
          </h3>
          <span className="text-green-600 font-bold text-sm">
            {(product.price / 100).toFixed(2)}₽
          </span>
        </div>

        <div className="flex justify-between text-xs text-gray-500 mb-2">
          <span>
            <i className="fas fa-weight-hanging mr-1"></i>
            {product.weight} г
          </span>
          <span>
            <i className="fas fa-fire mr-1"></i>
            {product.nutrients.calories} ккал
          </span>
        </div>

        <div className="flex justify-end space-x-2 text-xs mb-2">
          <span className="text-gray-600">
            <span className="nutrient-dot bg-blue-500"></span>
            {product.nutrients.proteins} б
          </span>
          <span className="text-gray-600">
            <span className="nutrient-dot bg-yellow-500"></span>
            {product.nutrients.fats} ж
          </span>
          <span className="text-gray-600">
            <span className="nutrient-dot bg-red-500"></span>
            {product.nutrients.carbs} у
          </span>
          <span className="text-gray-600">
            <span className="nutrient-dot bg-green-500"></span>
            {product.nutrients.fibers} кл
          </span>
        </div>

        <button
          className="w-full bg-green-500 hover:bg-green-600 text-white py-2 rounded-md font-medium transition flex items-center justify-center text-sm"
          onClick={(e) => {
            e.preventDefault();
            e.stopPropagation();
            onAddToCart();
          }}
        >
          <i className="fas fa-plus mr-1"></i> В корзину
        </button>
      </div>
    </a>
  );
};

export default ProductCard;
