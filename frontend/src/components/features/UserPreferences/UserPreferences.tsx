import React, { useState } from "react";
import { UserPreferencesDto } from "../../../api/preference/dto/UserPreferencesDto";
import {
  mockPreferenceCategories,
  mockPreferenceProducts,
} from "../../../mock/preferences";
import PreferenceChip from "../PreferenceChip/PreferenceChip";
import { PreferenceType } from "../../../api/preference/enums/PreferenceType";

interface UserPreferencesProps {
  userPreferences: UserPreferencesDto;
  onCategoryPreferencesRemove: (id: number) => void;
  onProductPreferencesRemove: (id: number) => void;
}

const UserPreferences: React.FC<UserPreferencesProps> = ({
  userPreferences,
  onCategoryPreferencesRemove,
  onProductPreferencesRemove,
}) => {
  const [activeTab, setActiveTab] = useState<"products" | "categories">(
    "products"
  );

  //TODO: API CALL
  const products = mockPreferenceProducts;
  const categories = mockPreferenceCategories;

  const likedProducts = userPreferences.productPreferences
    .filter((p) => p.preferenceType === PreferenceType.LIKED)
    .map((p) => products.find((prod) => prod.id === p.productId))
    .filter(Boolean);

  const dislikedProducts = userPreferences.productPreferences
    .filter((p) => p.preferenceType === PreferenceType.DISLIKED)
    .map((p) => products.find((prod) => prod.id === p.productId))
    .filter(Boolean);

  const likedCategories = userPreferences.categoryPreferences
    .filter((c) => c.preferenceType === PreferenceType.LIKED)
    .map((c) => categories.find((cat) => cat.id === c.categoryId))
    .filter(Boolean);

  const dislikedCategories = userPreferences.categoryPreferences
    .filter((c) => c.preferenceType === PreferenceType.DISLIKED)
    .map((c) => categories.find((cat) => cat.id === c.categoryId))
    .filter(Boolean);

  return (
    <div className="bg-white rounded-lg shadow-sm p-6 mb-8">
      <div className="flex justify-between items-center mb-6">
        <h3 className="text-lg font-bold text-gray-800">Ваши предпочтения</h3>
      </div>

      <div className="border-b border-gray-200 mb-6">
        <ul className="flex flex-wrap -mb-px" id="preferenceTabs">
          <li className="mr-2">
            <button
              className={`inline-block p-4 border-b-2 rounded-t-lg font-medium ${
                activeTab === "products"
                  ? "border-green-500 text-green-500 active"
                  : "border-transparent hover:text-gray-600 hover:border-gray-300"
              }`}
              onClick={() => setActiveTab("products")}
            >
              Продукты
            </button>
          </li>
          <li className="mr-2">
            <button
              className={`inline-block p-4 border-b-2 rounded-t-lg font-medium ${
                activeTab === "categories"
                  ? "border-green-500 text-green-500 active"
                  : "border-transparent hover:text-gray-600 hover:border-gray-300"
              }`}
              onClick={() => setActiveTab("categories")}
            >
              Категории
            </button>
          </li>
        </ul>
      </div>

      {activeTab === "products" && (
        <div id="productsTab" className="preference-tab-content">
          <h4 className="text-md font-semibold text-gray-700 mb-4">
            Любимые продукты
          </h4>
          <div className="flex flex-wrap gap-3 mb-8" id="likedProducts">
            {likedProducts.map((product) => (
              <PreferenceChip
                key={product!.id}
                text={product!.name}
                type={PreferenceType.LIKED}
                onRemove={() => onProductPreferencesRemove(product!.id)}
              />
            ))}
          </div>

          <h4 className="text-md font-semibold text-gray-700 mb-4">
            Нелюбимые продукты
          </h4>
          <div className="flex flex-wrap gap-3" id="dislikedProducts">
            {dislikedProducts.map((product) => (
              <PreferenceChip
                key={product!.id}
                text={product!.name}
                type={PreferenceType.DISLIKED}
                onRemove={() => onProductPreferencesRemove(product!.id)}
              />
            ))}
          </div>
        </div>
      )}

      {activeTab === "categories" && (
        <div id="categoriesTab" className="preference-tab-content">
          <h4 className="text-md font-semibold text-gray-700 mb-4">
            Любимые категории
          </h4>
          <div className="flex flex-wrap gap-3 mb-8" id="likedCategories">
            {likedCategories.map((category) => (
              <PreferenceChip
                key={category!.id}
                text={category!.name}
                type={PreferenceType.LIKED}
                onRemove={() => onCategoryPreferencesRemove(category!.id)}
              />
            ))}
          </div>

          <h4 className="text-md font-semibold text-gray-700 mb-4">
            Нелюбимые категории
          </h4>
          <div className="flex flex-wrap gap-3" id="dislikedCategories">
            {dislikedCategories.map((category) => (
              <PreferenceChip
                key={category!.id}
                text={category!.name}
                type={PreferenceType.DISLIKED}
                onRemove={() => onCategoryPreferencesRemove(category!.id)}
              />
            ))}
          </div>
        </div>
      )}
    </div>
  );
};

export default UserPreferences;
