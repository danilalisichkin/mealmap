import React, { useState } from "react";
import { DietDto } from "../../../api/health/dto/DietDto";
import { DietType } from "../../../api/health/enums/DietType";

interface UserHealthDietTabProps {
  diet: DietDto | null;
  currentWeight: number;
  onDeleteDiet: () => void;
  onCreateDiet: (dietType: DietType, goalWeight: number) => void;
}

const DIET_LABELS: Record<string, string> = {
  WEIGHT_LOSS: "Похудение",
  WEIGHT_GAIN: "Набор массы",
  MAINTENANCE: "Поддержка текущего веса",
};

const UserHealthDietTab: React.FC<UserHealthDietTabProps> = ({
  diet,
  currentWeight,
  onDeleteDiet,
  onCreateDiet,
}) => {
  const [newDietType, setNewDietType] = useState<DietType>(
    diet?.type ?? DietType.MAINTENANCE
  );
  
  const formattedCurrentWeight = currentWeight / 1000;
  const formattedDietGoalWeight = diet ? diet.goalWeight / 1000 : 0;

  const [newGoalWeight, setNewGoalWeight] = useState<number>(
    formattedDietGoalWeight || formattedCurrentWeight
  );

  const handleCreateDiet = () => {
    onCreateDiet(newDietType, newGoalWeight);
  };

  return (
    <div id="diet-tab" className="tab-panel active">
      {!diet ? (
        <div className="health-card bg-white rounded-lg shadow-sm p-4 mb-4">
          <h3 className="font-medium text-gray-800">Текущий план диеты</h3>
          <p className="text-gray-600 mb-2">не найден</p>
          <p className="text-gray-600">Вы еще не выбрали диету</p>
        </div>
      ) : (
        <div className="health-card bg-white rounded-lg shadow-sm p-4 mb-4">
          <h3 className="font-medium text-gray-800 mb-4">Текущий план диеты</h3>
          <div className="mb-6">
            <div className="flex justify-between items-center mb-1">
              <span className="text-gray-600">Тип диеты</span>
              <span className="font-medium" id="current-diet-type">
                {DIET_LABELS[diet.type]}
              </span>
            </div>
            <div className="flex justify-between items-center mb-1">
              <span className="text-gray-600">Желаемый вес</span>
              <span className="font-medium" id="current-goal-weight">
                {formattedDietGoalWeight} кг
              </span>
            </div>
            <div className="flex justify-between items-center">
              <span className="text-gray-600">Начало</span>
              <span className="font-medium" id="current-diet-start">
                {new Date(diet.startDate).toLocaleDateString()}
              </span>
            </div>
          </div>
          <div className="flex space-x-3">
            <button
              className="flex-1 py-2 bg-red-100 text-red-600 rounded-lg font-medium"
              id="cancel-diet-btn"
              onClick={(e) => {
                e.preventDefault();
                onDeleteDiet();
              }}
            >
              Завершить диету
            </button>
          </div>
        </div>
      )}

      <div className="health-card bg-white rounded-lg shadow-sm p-4">
        <h3 className="font-medium text-gray-800 mb-4">
          Создать новый план диеты
        </h3>
        <form
          id="new-diet-form"
          onSubmit={(e) => {
            e.preventDefault();
            handleCreateDiet();
          }}
        >
          <div className="mb-4">
            <label
              className="block text-gray-700 text-sm font-medium mb-2"
              htmlFor="diet-type-select"
            >
              Тип диеты
            </label>
            <select
              id="diet-type-select"
              className="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-green-500 focus:border-green-500"
              value={newDietType}
              onChange={(e) => setNewDietType(e.target.value as DietType)}
            >
              <option value="MAINTENANCE">{DIET_LABELS["MAINTENANCE"]}</option>
              <option value="WEIGHT_LOSS">{DIET_LABELS["WEIGHT_LOSS"]}</option>
              <option value="WEIGHT_GAIN">{DIET_LABELS["WEIGHT_GAIN"]}</option>
            </select>
          </div>
          <div className="mb-4">
            <label
              className="block text-gray-700 text-sm font-medium mb-2"
              htmlFor="goal-weight-input"
            >
              Желаемый вес (кг)
            </label>
            <input
              type="number"
              id="goal-weight-input"
              min="30"
              max="200"
              step="0.1"
              defaultValue={newGoalWeight}
              value={newGoalWeight}
              onChange={(e) => setNewGoalWeight(parseFloat(e.target.value))}
              className="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-green-500 focus:border-green-500"
            />
          </div>
          <button
            type="submit"
            className="w-full py-2 px-4 bg-green-500 text-white font-medium rounded-md hover:bg-green-600 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-green-500"
          >
            Начать диету
          </button>
        </form>
      </div>
    </div>
  );
};

export default UserHealthDietTab;
