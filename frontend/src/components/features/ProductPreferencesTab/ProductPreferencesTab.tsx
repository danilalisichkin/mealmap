import React, { useCallback, useEffect, useState } from "react";
import { ProductDto } from "../../../api/product/dto/ProductDto";
import { ProductApi } from "../../../api/product/ProductApi";
import { PreferenceType } from "../../../api/preference/enums/PreferenceType";
import { ProductPreferenceDto } from "../../../api/preference/dto/ProductPreferenceDto";
import PreferenceChip from "../PreferenceChip/PreferenceChip";

interface ProductPreferencesTabProps {
  productPreferences: ProductPreferenceDto[];
  onRemovePreference: (preferenceId: number) => void;
}

const ProductPreferencesTab: React.FC<ProductPreferencesTabProps> = ({
  productPreferences,
  onRemovePreference,
}) => {
  const [preferences, setPreferences] =
    useState<ProductPreferenceDto[]>(productPreferences);
  const [productsMap, setProductsMap] = useState<Record<number, ProductDto>>(
    {}
  );
  const [loading, setLoading] = useState<boolean>(true);

  const fetchProducts = useCallback(async () => {
    if (preferences.length === 0) {
      setLoading(false);
      return;
    }
    
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
    } finally {
      setLoading(false);
    }
  }, []);

  const handleRemovePreference = async (id: number) => {
    setPreferences((prev) => prev.filter((pref) => pref.productId !== id));
    setProductsMap((prev) => {
      const updatedMap = { ...prev };
      delete updatedMap[id];
      return updatedMap;
    });
    onRemovePreference(id);
  };

  useEffect(() => {
    fetchProducts();
  }, [fetchProducts]);

  if (loading) {
    return <div className="text-center py-12">Загрузка...</div>;
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
