import React, { useState } from "react";
import { UserAllergenDto } from "../../../api/health/dto/UserAllergenDto";
import { AllergenDto } from "../../../api/product/dto/AllergenDto";
import AllergenChip from "../../AllergenChip/AllergenChip";

interface UserHealthAllergensTabProps {
  userAllergens: UserAllergenDto[];
  allAllergens: AllergenDto[];
  onRemoveAllergen: (id: number) => void;
  onAddAllergen: (allergenId: number) => void;
}

const UserHealthAllergensTab: React.FC<UserHealthAllergensTabProps> = ({
  userAllergens,
  allAllergens,
  onRemoveAllergen,
  onAddAllergen,
}) => {
  const [selectedAllergenId, setSelectedAllergenId] = useState<number | "">("");

  const userProductAllergens = userAllergens
    .map((allergen) => allAllergens.find((a) => a.id === allergen.allergenId))
    .filter(Boolean) as AllergenDto[];

  const availableAllergens = allAllergens.filter(
    (a) => !userAllergens.some((ua) => ua.allergenId === a.id)
  );

  const handleAddAllergen = () => {
    if (selectedAllergenId && typeof selectedAllergenId === "number") {
      onAddAllergen(selectedAllergenId);
      setSelectedAllergenId("");
    }
  };

  return (
    <div id="allergens-tab" className="tab-panel active">
      <div className="health-card bg-white rounded-lg shadow-sm p-4 mb-4">
        <h4 className="text-md font-semibold text-gray-700 mb-4">
          У Вас аллергия на продукты:
        </h4>
        <div className="flex flex-wrap gap-3 mb-4" id="likedProducts">
          {userProductAllergens.length === 0 ? (
            <p className="text-m text-green-400">
              Кажется, у Вас нет аллергий. Будьте здоровы!
            </p>
          ) : (
            userAllergens.map((allergen) => {
              const productAllergen = allAllergens.find(
                (a) => a.id === allergen.allergenId
              );
              if (!productAllergen) return null;
              return (
                <AllergenChip
                  key={allergen.id}
                  text={productAllergen.name}
                  onRemove={() => onRemoveAllergen(allergen.allergenId)}
                />
              );
            })
          )}
        </div>
        <div className="flex items-center gap-2 mt-2">
          <select
            className="border rounded-lg px-3 py-2"
            value={selectedAllergenId}
            onChange={(e) =>
              setSelectedAllergenId(
                e.target.value ? Number(e.target.value) : ""
              )
            }
          >
            <option value="">Добавить аллерген...</option>
            {availableAllergens.map((allergen) => (
              <option key={allergen.id} value={allergen.id}>
                {allergen.name}
              </option>
            ))}
          </select>
          <button
            className="bg-green-500 hover:bg-green-600 text-white px-4 py-2 rounded-lg transition disabled:opacity-50"
            onClick={handleAddAllergen}
            disabled={!selectedAllergenId}
          >
            Добавить
          </button>
        </div>
      </div>
    </div>
  );
};

export default UserHealthAllergensTab;
