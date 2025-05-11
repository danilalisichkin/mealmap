import React, { useEffect, useState } from "react";
import { OrderItemDto } from "../../../api/order/dto/OrderItemDto";
import { ProductDto } from "../../../api/product/dto/ProductDto";
import LoadingSpinner from "../../commons/LoadingSpinner/LoadingSpinner";
import { FileApi } from "../../../api/file/FileApi";

interface OrderItemProps {
  item: OrderItemDto;
  product: ProductDto;
  onNavigateToProductPage: (productId: number) => void;
}

const OrderItem: React.FC<OrderItemProps> = ({
  item,
  product,
  onNavigateToProductPage,
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

  return (
    <div className="flex items-start gap-4" key={item.productId}>
      {imageSrc ? (
        <a
          href={`/product/${product.id}`}
          onClick={(e) => {
            e.preventDefault();
            onNavigateToProductPage(product.id);
          }}
        >
          <img
            src={imageSrc}
            alt={product.name}
            className="product-image w-16 h-16 object-cover rounded-lg"
          />
        </a>
      ) : (
        <div className="w-16 h-16 flex items-center justify-center bg-gray-100 rounded-lg">
          <LoadingSpinner />
        </div>
      )}
      <div className="flex-1">
        <h4 className="font-medium text-gray-800">{product.name}</h4>
        <p className="text-sm text-gray-600">{product.weight} г</p>

        <div className="flex justify-between items-center mt-1">
          <span className="text-gray-600">
            {item.quantity} × {item.priceWhenOrdered}₽
          </span>
          <span className="font-medium">
            {((item.quantity * item.priceWhenOrdered) / 100).toFixed(2)}₽
          </span>
        </div>
      </div>
    </div>
  );
};

export default OrderItem;
