import React, { useCallback, useEffect, useState } from "react";
import { CategoryPreferenceDto } from "../../../api/preference/dto/CategoryPreferenceDto";
import { PreferenceType } from "../../../api/preference/enums/PreferenceType";
import PreferenceChip from "../PreferenceChip/PreferenceChip";
import { CategoryDto } from "../../../api/product/dto/CategoryDto";
import { CategoryApi } from "../../../api/product/CategoryApi";

interface CategoryPreferencesTabProps {
  categoryPreferences: CategoryPreferenceDto[];
  onRemovePreference: (preferenceId: number) => void;
}

const CategoryPreferencesTab: React.FC<CategoryPreferencesTabProps> = ({
  categoryPreferences,
  onRemovePreference,
}) => {
  const [preferences, setPreferences] =
    useState<CategoryPreferenceDto[]>(categoryPreferences);
  const [categoriesMap, setCategoriesMap] = useState<
    Record<number, CategoryDto>
  >({});
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  const fetchCategories = useCallback(async () => {
    if (preferences.length === 0) {
      setLoading(false);
      return;
    }
    
    try {
      setLoading(true);

      const categoriesId = categoryPreferences.map((c) => c.categoryId);
      const categories = await CategoryApi.bulkGetCategories(categoriesId);

      const categoryMap: Record<number, CategoryDto> = {};
      categories.forEach((category) => {
        categoryMap[category.id] = category;
      });

      setCategoriesMap(categoryMap);
    } catch (err) {
      console.error("Ошибка при загрузке категорий:", err);
      setError("Не удалось загрузить категории.");
    } finally {
      setLoading(false);
    }
  }, [categoryPreferences]);

  const handleRemovePreference = async (id: number) => {
    setPreferences((prev) => prev.filter((pref) => pref.categoryId !== id));
    setCategoriesMap((prev) => {
      const updatedMap = { ...prev };
      delete updatedMap[id];
      return updatedMap;
    });
    onRemovePreference(id);
  };

  useEffect(() => {
    fetchCategories();
  }, [fetchCategories]);

  if (loading) {
    return <div className="text-center py-12">Загрузка...</div>;
  }

  if (error) {
    return <div className="text-center py-12 text-red-500">{error}</div>;
  }

  const likedCategories = preferences
    .filter((c) => c.preferenceType === PreferenceType.LIKED)
    .map((c) => categoriesMap[c.categoryId])
    .filter(Boolean);

  const dislikedCategories = preferences
    .filter((c) => c.preferenceType === PreferenceType.DISLIKED)
    .map((c) => categoriesMap[c.categoryId])
    .filter(Boolean);

  return (
    <div>
      <h4 className="text-md font-semibold text-gray-700 mb-4">
        Любимые категории
      </h4>
      <div className="flex flex-wrap gap-3 mb-8" id="likedCategories">
        {likedCategories.map((category) => (
          <PreferenceChip
            key={category.id}
            text={category.name}
            type={PreferenceType.LIKED}
            onRemove={() => handleRemovePreference(category.id)}
          />
        ))}
      </div>

      <h4 className="text-md font-semibold text-gray-700 mb-4">
        Нелюбимые категории
      </h4>
      <div className="flex flex-wrap gap-3" id="dislikedCategories">
        {dislikedCategories.map((category) => (
          <PreferenceChip
            key={category.id}
            text={category.name}
            type={PreferenceType.DISLIKED}
            onRemove={() => handleRemovePreference(category.id)}
          />
        ))}
      </div>
    </div>
  );
};

export default CategoryPreferencesTab;
