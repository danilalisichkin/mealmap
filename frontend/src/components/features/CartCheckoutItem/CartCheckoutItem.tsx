import React, { useEffect, useState } from "react";
import { CartItemDto } from "../../../api/cart/dto/CartItemDto";
import { ProductDto } from "../../../api/product/dto/ProductDto";
import { FileApi } from "../../../api/file/FileApi";
import LoadingSpinner from "../../commons/LoadingSpinner/LoadingSpinner";

interface CartCheckoutItemProps {
  item: CartItemDto;
  product: ProductDto;
}

const CartCheckoutItem: React.FC<CartCheckoutItemProps> = ({
  item,
  product,
}) => {
  const [imageSrc, setImageSrc] = useState<string | null>(null);

  const itemTotal = (product.price * item.quantity) / 100;

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
    <div className="py-4 flex items-center">
      {imageSrc ? (
        <img
          src={imageSrc}
          alt={product.name}
          className="w-16 h-16 object-cover rounded-lg mr-4"
        />
      ) : (
        <LoadingSpinner />
      )}
      <div className="flex-1">
        <h4 className="font-medium text-gray-800">{product.name}</h4>
        <div className="flex justify-between items-center mt-1">
          <span className="text-gray-600">
            {(product.price / 100).toFixed(2)}₽ × {item.quantity} шт.
          </span>
          <span className="font-medium">{itemTotal.toFixed(2)}₽</span>
        </div>
      </div>
    </div>
  );
};

export default CartCheckoutItem;
