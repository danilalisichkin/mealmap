import React from "react";
import { DietDto } from "../../../api/health/dto/DietDto";

interface UserHealthDietTabProps {
  diet: DietDto;
  formattedCurrentWeight: number;
  formattedDietGoalWeight: number;
}

const DIET_LABELS: Record<string, string> = {
  WEIGHT_LOSS: "Похудение",
  WEIGHT_GAIN: "Набор массы",
  MAINTENANCE: "Поддержка текущего веса",
};

const UserHealthDietTab: React.FC<UserHealthDietTabProps> = ({
  diet,
  formattedCurrentWeight,
  formattedDietGoalWeight,
}) => {
  return (
    <div id="diet-tab" className="tab-panel active">
      <div className="health-card bg-white rounded-lg shadow-sm p-4 mb-4">
        <h3 className="font-medium text-gray-800 mb-4">Текущий план диеты</h3>
        <div className="mb-6">
          <div className="flex justify-between items-center mb-1">
            <span className="text-gray-600">Тип диеты</span>
            <span className="font-medium" id="current-diet-type">
              {DIET_LABELS[diet.dietType]}
            </span>
          </div>
          <div className="flex justify-between items-center mb-1">
            <span className="text-gray-600">Желаемый вес</span>
            <span className="font-medium" id="current-goal-weight">
              {formattedCurrentWeight} кг
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
            className="flex-1 py-2 bg-gray-100 text-gray-800 rounded-lg font-medium"
            id="edit-diet-btn"
          >
            Изменить план
          </button>
          <button
            className="flex-1 py-2 bg-red-100 text-red-600 rounded-lg font-medium"
            id="cancel-diet-btn"
          >
            Завершить диету
          </button>
        </div>
      </div>
      <div className="health-card bg-white rounded-lg shadow-sm p-4">
        <h3 className="font-medium text-gray-800 mb-4">
          Создать новый план диеты
        </h3>
        <form id="new-diet-form">
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
              defaultValue={diet.dietType}
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
              defaultValue={formattedDietGoalWeight}
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
