import React from "react";
import "../Placeholders.css";
import "./ProductCardPlaceholder.css";

interface ProductCardPlaceholderProps {}

const ProductCardPlaceholder: React.FC<ProductCardPlaceholderProps> = () => {
  return (
    <div className="placeholder bg-white rounded-lg shadow-sm overflow-hidden">
      {/* Product image */}
      <div className="dish-image relative bg-gray-200 skeleton-animation"></div>

      {/* Product info */}
      <div className="p-2">
        {/* Name & Price */}
        <div className="flex justify-between items-start mt-1 mb-3">
          <div className="product-name bg-gray-200 skeleton-animation"></div>
          <div className="product-price bg-gray-200 skeleton-animation"></div>
        </div>

        {/* Weight & Calories */}
        <div className="flex justify-between text-xs text-gray-500 mb-3">
          <div className="product-weight bg-gray-200 skeleton-animation"></div>
          <div className="product-calories bg-gray-200 skeleton-animation"></div>
        </div>

        {/* Nutrients */}
        <div className="flex justify-end space-x-2 text-xs mb-2">
          <div className="flex items-center">
            <div className="nutrient-dot bg-gray-200 skeleton-animation"></div>
            <div className="nutrient-value bg-gray-200 skeleton-animation"></div>
          </div>
          <div className="flex items-center">
            <div className="nutrient-dot bg-gray-200 skeleton-animation"></div>
            <div className="nutrient-value bg-gray-200 skeleton-animation"></div>
          </div>
          <div className="flex items-center">
            <div className="nutrient-dot bg-gray-200 skeleton-animation"></div>
            <div className="nutrient-value bg-gray-200 skeleton-animation"></div>
          </div>
          <div className="flex items-center">
            <div className="nutrient-dot bg-gray-200 skeleton-animation"></div>
            <div className="nutrient-value bg-gray-200 skeleton-animation"></div>
          </div>
        </div>

        {/* Add to cart button */}
        <div className="button w-full bg-gray-200 py-2 rounded-md flex items-center justify-center skeleton-animation">
        </div>
      </div>
    </div>
  );
};

export default ProductCardPlaceholder;
