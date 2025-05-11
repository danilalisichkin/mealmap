import React, { useEffect, useState } from "react";
import { OrderItemDto } from "../../../api/order/dto/OrderItemDto";
import { ProductDto } from "../../../api/product/dto/ProductDto";
import { ProductApi } from "../../../api/product/ProductApi";
import OrderItem from "../OrderItem/OrderItem";
import { useNavigate } from "react-router-dom";

interface OrderItemListProps {
  items: OrderItemDto[];
}

const OrderItemList: React.FC<OrderItemListProps> = ({ items }) => {
  const navigate = useNavigate();

  const [products, setProducts] = useState<ProductDto[]>([]);

  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  const fetchProducts = async () => {
    try {
      setLoading(true);
      setError(null);

      const productIds = items.map((item) => item.productId);

      const fetchedProducts = await ProductApi.bulkGetProducts(productIds);
      setProducts(fetchedProducts);
    } catch (err) {
      console.error("Ошибка при загрузке продуктов:", err);
      setError("Не удалось загрузить информацию о продуктах.");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchProducts();
  }, [items]);

  const handleNavigateToProductPage = (productId: number) => {
    const product = products.find((product) => product.id === productId);
    navigate(`/catalog/products/${productId}`, { state: { product } });
  };

  const productsMap = products.reduce<Record<number, ProductDto>>(
    (map, product) => {
      map[product.id] = product;
      return map;
    },
    {}
  );

  if (loading) {
    return <div className="text-center py-4">Загрузка продуктов...</div>;
  }

  if (error) {
    return <div className="text-center py-4 text-red-500">{error}</div>;
  }

  return (
    <div className="space-y-4">
      {items.map((item) => {
        const product = productsMap[item.productId];
        return (
          <OrderItem
            key={`order-item-${item.productId}-${item.quantity}`}
            item={item}
            product={product}
            onNavigateToProductPage={handleNavigateToProductPage}
          />
        );
      })}
    </div>
  );
};

export default OrderItemList;
