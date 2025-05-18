import React, { useEffect, useState } from "react";
import { ProductDto } from "../../../api/product/dto/ProductDto";
import "./ProductCard.css";
import { PreferenceType } from "../../../api/preference/enums/PreferenceType";
import { useAuth } from "../../../contexts/AuthContext";
import { FileApi } from "../../../api/file/FileApi";
import LoadingSpinner from "../../commons/LoadingSpinner/LoadingSpinner";

interface ProductCardProps {
  product: ProductDto;
  preferenceType: PreferenceType | null;
  isAllergic: boolean | null;
  onAddToCart: () => void;
  onNavigateToProductPage: () => void;
  onAddToPreference: (preferenceType: PreferenceType) => void;
  onRemoveFromPreference: () => void;
}

const ProductCard: React.FC<ProductCardProps> = ({
  product,
  preferenceType,
  isAllergic,
  onAddToCart,
  onNavigateToProductPage,
  onAddToPreference,
  onRemoveFromPreference,
}) => {
  const { userId } = useAuth();

  const [currentPreferenceType, setCurrentPreferenceType] =
    useState<PreferenceType | null>(preferenceType);

  const [isMounted, setIsMounted] = useState(false);
  const [imageSrc, setImageSrc] = useState<string | null>(null);

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
      if (userId !== null) {
        setCurrentPreferenceType(null);
      }
    } else {
      if (userId !== null) {
        setCurrentPreferenceType(type);
      }
      onAddToPreference(type);
    }
  };

  useEffect(() => {
    const fetchImage = async () => {
      try {
        const imageBlob = await FileApi.downloadFile(product.imageUrl);
        const imageUrl = URL.createObjectURL(imageBlob);
        setImageSrc(imageUrl);
      } catch (error) {
        console.error("Ошибка при загрузке изображения:", error);
      }
    };

    if (product.imageUrl) {
      fetchImage();
    }
  }, [product.imageUrl]);

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
      {/* Product Image & isNew & Preference choice */}
      <div className="relative dish-image bg-gray-200 flex items-center justify-center">
        {imageSrc ? (
          <img
            src={imageSrc}
            alt={product.name}
            className="w-full h-full object-cover"
          />
        ) : (
          <LoadingSpinner />
        )}
        {product.isNew && (
          <span className="absolute top-1 right-1 bg-orange-500 text-white text-xs px-1 py-0.5 rounded-full">
            новинка
          </span>
        )}
        {isAllergic && (
          <span className="absolute bottom-1 right-1 bg-red-500 text-white text-xs px-1 py-0.5 rounded-full">
            аллергенный
          </span>
        )}

        <div className="absolute bottom-1 left-4 flex space-x-2">
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
      </div>

      {/* Product info */}
      <div className="p-2">
        {/* Name & Price */}
        <div className="flex justify-between items-start mb-2">
          <h3 className="text-sm font-semibold text-gray-800 truncate">
            {product.name}
          </h3>
          <span className="text-green-600 font-bold text-sm">
            {(product.price / 100).toFixed(2)}₽
          </span>
        </div>

        {/* Weight & Calories */}
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

        {/* Nutrients */}
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

        {/* Add to cart button */}
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
