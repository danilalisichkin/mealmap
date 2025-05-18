import React, { useState } from "react";
import { PhysicHealthDto } from "../../../api/health/dto/PhysicHealthDto";

interface UserHealthPhysicalTabProps {
  physicHealth: PhysicHealthDto;
  age: number | string;
  onSaveWeight: (newWeight: number) => void;
}

const GENDER_LABELS: Record<string, string> = {
  MALE: "Мужчина",
  FEMALE: "Женщина",
};

const UserHealthPhysicalTab: React.FC<UserHealthPhysicalTabProps> = ({
  physicHealth,
  age,
  onSaveWeight,
}) => {
  const formattedCurrentWeight = physicHealth.weight / 1000;

  const [currentWeight, setCurrentWeight] = useState<number>(
    formattedCurrentWeight
  );

  const handleWeightChange = (newWeight: number) => {
    setCurrentWeight(newWeight);
  };

  const handleWeightChangeSubmit = () => {
    onSaveWeight(currentWeight);
  };

  return (
    <div id="physical-tab" className="tab-panel active">
      <div className="health-card bg-white rounded-lg shadow-sm p-4 mb-4">
        <h3 className="font-medium text-gray-800 mb-4">
          Текущие физические показатели
        </h3>
        <div className="mb-4">
          <div className="flex justify-between items-center mb-1">
            <span className="text-gray-600">Вес</span>
            <span className="font-medium" id="current-physical-weight">
              {formattedCurrentWeight.toFixed(1)} кг
            </span>
          </div>
          <div className="flex justify-between items-center mb-1">
            <span className="text-gray-600">Рост</span>
            <span className="font-medium" id="current-physical-height">
              {physicHealth.height} см
            </span>
          </div>
          <div className="flex justify-between items-center mb-1">
            <span className="text-gray-600">Пол</span>
            <span className="font-medium" id="current-gender">
              {GENDER_LABELS[physicHealth.gender]}
            </span>
          </div>
          <div className="flex justify-between items-center">
            <span className="text-gray-600">Возраст</span>
            <span className="font-medium" id="current-current-age">
              {age} лет
            </span>
          </div>
        </div>
      </div>
      <div className="health-card bg-white rounded-lg shadow-sm p-4">
        <h3 className="font-medium text-gray-800 mb-2">Зафиксировать вес</h3>
        <p className="text-sm text-gray-500 mb-4">Отслеживайте свой прогресс</p>
        <form
          id="new-weight-form"
          className="flex items-end"
          onSubmit={(e) => {
            e.preventDefault();
            handleWeightChangeSubmit();
          }}
        >
          <div className="flex-1 mr-2">
            <label
              className="block text-gray-700 text-sm font-medium mb-1"
              htmlFor="new-weight-input"
            >
              Вес (кг)
            </label>
            <input
              type="number"
              id="new-weight-input"
              min="30"
              max="200"
              step="0.1"
              defaultValue={formattedCurrentWeight.toFixed(1)}
              onChange={(e) =>
                handleWeightChange(parseFloat(e.target.value) * 1000)
              }
              className="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-green-500 focus:border-green-500"
            />
          </div>
          <button
            type="submit"
            className="py-2 px-4 bg-green-500 text-white font-medium rounded-md hover:bg-green-600 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-green-500"
          >
            Сохранить
          </button>
        </form>
      </div>
    </div>
  );
};

export default UserHealthPhysicalTab;
