import React, { useState } from "react";
import { UserPreferencesDto } from "../../../api/preference/dto/UserPreferencesDto";
import ProductPreferencesTab from "../ProductPreferencesTab/ProductPreferencesTab";
import CategoryPreferencesTab from "../CategoryPreferencesTab/CategoryPreferencesTab";

interface UserPreferencesProps {
  userPreferences: UserPreferencesDto;
  onProductPreferenceRemove: (productId: number) => void;
  onCategoryPreferenceRemove: (productId: number) => void;
}

const UserPreferences: React.FC<UserPreferencesProps> = ({
  userPreferences,
  onProductPreferenceRemove,
  onCategoryPreferenceRemove,
}) => {
  const [activeTab, setActiveTab] = useState<"products" | "categories">(
    "products"
  );

  return (
    <div className="bg-white rounded-lg shadow-sm p-6 mb-8">
      <div className="flex justify-between items-center mb-6">
        <h3 className="text-lg font-bold text-gray-800">Мои предпочтения</h3>
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
        <ProductPreferencesTab
          productPreferences={userPreferences.productPreferences}
          onRemovePreference={onProductPreferenceRemove}
        />
      )}

      {activeTab === "categories" && (
        <CategoryPreferencesTab
          categoryPreferences={userPreferences.categoryPreferences}
          onRemovePreference={onCategoryPreferenceRemove}
        />
      )}
    </div>
  );
};

export default UserPreferences;
