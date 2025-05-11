import React, { useEffect, useState } from "react";
import { CartItemDto } from "../../../api/cart/dto/CartItemDto";
import { ProductDto } from "../../../api/product/dto/ProductDto";
import "./CartItem.css";
import LoadingSpinner from "../../commons/LoadingSpinner/LoadingSpinner";
import { FileApi } from "../../../api/file/FileApi";

interface CartItemProps {
  cartItem: CartItemDto;
  product: ProductDto;
  onRemove: () => void;
  onQuantityChange: (newQuantity: number) => void;
}

const LIMITS = {
  QUANTITY_PER_ITEM: 20,
};

const CartItem: React.FC<CartItemProps> = ({
  cartItem,
  product,
  onRemove,
  onQuantityChange,
}) => {
  const [imageSrc, setImageSrc] = useState<string | null>(null);

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

  const handleQuantityChange = (delta: number) => {
    const newQuantity = Math.max(
      1,
      Math.min(LIMITS.QUANTITY_PER_ITEM, cartItem.quantity + delta)
    );
    console.log("NEW QUANTITY:", newQuantity);
    if (newQuantity !== cartItem.quantity) {
      onQuantityChange(newQuantity);
    }
  };

  const isReachedLowBorder = () => {
    return cartItem.quantity <= 1;
  };

  const isReachedUpBorder = () => {
    return cartItem.quantity >= LIMITS.QUANTITY_PER_ITEM;
  };

  return (
    <div className="cart-item p-4 flex items-center relative">
      {imageSrc ? (
        <img
          src={imageSrc}
          alt={product.name}
          className="w-16 h-16 object-cover rounded-lg mr-4"
        />
      ) : (
        <div className="w-16 h-16 object-cover rounded-lg mr-4">
          <LoadingSpinner />
        </div>
      )}

      <div className="flex-1">
        <h4 className="font-medium text-gray-800">{product.name}</h4>
        <div className="flex justify-between items-center mt-1">
          <span className="text-gray-600">
            {(product.price / 100).toFixed(2)}₽ × {cartItem.quantity}
          </span>
          <span className="font-medium">
            {((product.price * cartItem.quantity) / 100).toFixed(2)}₽
          </span>
        </div>
        <div className="flex items-center mt-2">
          <button
            className={`cart-item-quantity-minus ${
              isReachedLowBorder()
                ? "bg-gray-100 text-gray-400"
                : "bg-gray-100 hover:bg-gray-200 text-gray-800"
            } w-6 h-6 rounded-full flex items-center justify-center text-xs mr-2`}
            onClick={() => handleQuantityChange(-1)}
            disabled={isReachedLowBorder()}
          >
            <i className="fas fa-minus"></i>
          </button>
          <span className="quantity">{cartItem.quantity}</span>
          <button
            className={`cart-item-quantity-minus ${
              isReachedUpBorder()
                ? "bg-gray-100 text-gray-400"
                : "bg-gray-100 hover:bg-gray-200 text-gray-800"
            } w-6 h-6 rounded-full flex items-center justify-center text-xs mr-2`}
            onClick={() => handleQuantityChange(1)}
            disabled={isReachedUpBorder()}
          >
            <i className="fas fa-plus"></i>
          </button>
        </div>
      </div>
      <button
        className="cart-item-remove ml-4 text-gray-400 hover:text-red-500"
        onClick={onRemove}
      >
        <i className="fas fa-trash"></i>
      </button>
    </div>
  );
};

export default CartItem;
