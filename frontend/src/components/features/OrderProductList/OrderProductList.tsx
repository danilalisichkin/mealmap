import React, { useEffect, useState } from "react";
import { OrderItemDto } from "../../../api/order/dto/OrderItemDto";
import { ProductDto } from "../../../api/product/dto/ProductDto";
import { ProductApi } from "../../../api/product/ProductApi";

interface OrderProductListProps {
  items: OrderItemDto[];
}

const OrderProductList: React.FC<OrderProductListProps> = ({ items }) => {
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
          <div className="flex items-start gap-4" key={item.productId}>
            <img
              src="https://images.unsplash.com/photo-1490645935967-10de6ba17061?ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=80"
              alt="DEBUG"
              className="product-image w-16 h-16"
            />
            <div className="flex-1">
              <h4 className="font-medium text-gray-800">
                {product?.name || "Неизвестный продукт"}
              </h4>
              <p className="text-sm text-gray-600">
                {product ? `${product.weight} г` : "Вес неизвестен"}
              </p>

              <div className="flex justify-between items-center mt-1">
                <span className="text-gray-600">
                  {item.quantity} × {item.priceWhenOrdered}₽
                </span>
                <span className="font-medium">
                  {(item.quantity * item.priceWhenOrdered).toLocaleString()}₽
                </span>
              </div>
            </div>
          </div>
        );
      })}
    </div>
  );
};

export default OrderProductList;
