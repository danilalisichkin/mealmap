import React, { useCallback, useEffect, useState } from "react";
import { ProductDto } from "../../../api/product/dto/ProductDto";
import { ProductApi } from "../../../api/product/ProductApi";
import { PreferenceType } from "../../../api/preference/enums/PreferenceType";
import { ProductPreferenceDto } from "../../../api/preference/dto/ProductPreferenceDto";
import PreferenceChip from "../PreferenceChip/PreferenceChip";
import { PreferenceApi } from "../../../api/preference/UserPreferenceApi";

interface ProductPreferencesTabProps {
  productPreferences: ProductPreferenceDto[];
  userId: string;
}

const ProductPreferencesTab: React.FC<ProductPreferencesTabProps> = ({
  productPreferences,
  userId,
}) => {
  const [preferences, setPreferences] =
    useState<ProductPreferenceDto[]>(productPreferences);
  const [productsMap, setProductsMap] = useState<Record<number, ProductDto>>(
    {}
  );
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  const fetchProducts = useCallback(async () => {
    try {
      setLoading(true);

      const productIds = productPreferences.map((p) => p.productId);
      const products = await ProductApi.bulkGetProducts(productIds);

      const productMap: Record<number, ProductDto> = {};
      products.forEach((product) => {
        productMap[product.id] = product;
      });

      setProductsMap(productMap);
    } catch (err) {
      console.error("Ошибка при загрузке продуктов:", err);
      setError("Не удалось загрузить продукты.");
    } finally {
      setLoading(false);
    }
  }, [productPreferences]);

  const handleRemovePreference = async (id: number) => {
    try {
      await PreferenceApi.removeProductPreference(userId, id);
      setPreferences((prev) => prev.filter((pref) => pref.id !== id));
    } catch (err) {
      console.error("Ошибка при удалении предпочтения продукта:", err);
    }
  };

  useEffect(() => {
    fetchProducts();
  }, [fetchProducts]);

  if (loading) {
    return <div className="text-center py-12">Загрузка...</div>;
  }

  if (error) {
    return <div className="text-center py-12 text-red-500">{error}</div>;
  }

  const likedProducts = preferences
    .filter((p) => p.preferenceType === PreferenceType.LIKED)
    .map((p) => productsMap[p.productId])
    .filter(Boolean);

  const dislikedProducts = preferences
    .filter((p) => p.preferenceType === PreferenceType.DISLIKED)
    .map((p) => productsMap[p.productId])
    .filter(Boolean);

  return (
    <div>
      <h4 className="text-md font-semibold text-gray-700 mb-4">
        Любимые продукты
      </h4>
      <div className="flex flex-wrap gap-3 mb-8" id="likedProducts">
        {likedProducts.map((product) => (
          <PreferenceChip
            key={product.id}
            text={product.name}
            type={PreferenceType.LIKED}
            onRemove={() => handleRemovePreference(product.id)}
          />
        ))}
      </div>

      <h4 className="text-md font-semibold text-gray-700 mb-4">
        Нелюбимые продукты
      </h4>
      <div className="flex flex-wrap gap-3" id="dislikedProducts">
        {dislikedProducts.map((product) => (
          <PreferenceChip
            key={product.id}
            text={product.name}
            type={PreferenceType.DISLIKED}
            onRemove={() => handleRemovePreference(product.id)}
          />
        ))}
      </div>
    </div>
  );
};

export default ProductPreferencesTab;
