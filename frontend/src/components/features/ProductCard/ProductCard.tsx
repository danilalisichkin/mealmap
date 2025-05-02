import React from "react";
import { ProductDto } from "../../../api/product/dto/ProductDto";
import "./ProductCard.css";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../../../contexts/AuthContext";
import { CartApi } from "../../../api/cart/UserCartApi";

interface ProductCardProps {
  product: ProductDto;
}

const ProductCard: React.FC<ProductCardProps> = ({ product }) => {
  const navigate = useNavigate();
  const { userId } = useAuth();

  const navigateToProductPage = (
    e: React.MouseEvent<HTMLAnchorElement, MouseEvent>
  ) => {
    e.preventDefault();
    navigate(`/product/${product.id}`, { state: { product } });
  };

  const handleAddToCart = async (e: React.MouseEvent<HTMLButtonElement>) => {
    e.stopPropagation();
    e.preventDefault();

    if (!userId) {
      console.error("Пользователь не авторизован");
      return;
    }

    try {
      await CartApi.addItemToCart(userId, {
        productId: product.id,
        quantity: 1,
      });
      console.log(`Товар с ID ${product.id} добавлен в корзину`);
    } catch (error) {
      console.error("Ошибка при добавлении товара в корзину:", error);
    }
  };

  return (
    <a
      className="dish-card bg-white rounded-lg shadow-sm overflow-hidden transition duration-300 cursor-pointer z-100"
      href={`/product/${product.id}`}
      onClick={navigateToProductPage}
    >
      <div className="relative">
        <div className="dish-image bg-gray-200 flex items-center justify-center">
          <img
            src="https://images.unsplash.com/photo-1546069901-ba9599a7e63c?ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=80"
            alt="Салат Цезарь"
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
          className="w-full bg-green-500 hover:bg-green-600 text-white py-2 rounded-md font-medium transition flex items-center justify-center text-sm z-50"
          onClick={handleAddToCart}
        >
          <i className="fas fa-plus mr-1"></i> В корзину
        </button>
      </div>
    </a>
  );
};

export default ProductCard;
