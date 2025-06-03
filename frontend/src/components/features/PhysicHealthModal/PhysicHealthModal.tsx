import React, { useState } from "react";
import "./PhysicHealthModal.css";
import { PhysicHealthCreationDto } from "../../../api/health/dto/PhysicHealthCreationDto";
import { Gender } from "../../../api/health/enums/Gender";

interface PhysicHealthModalProps {
  isOpen: boolean;
  onClose: () => void;
  onSubmit: (data: PhysicHealthCreationDto) => void;
}

const GENDER_LABELS: Record<string, string> = {
  MALE: "Мужчина",
  FEMALE: "Женщина",
};

const PhysicHealthModal: React.FC<PhysicHealthModalProps> = ({
  isOpen,
  onClose,
  onSubmit,
}) => {
  const [weight, setWeight] = useState(72.5);
  const [height, setHeight] = useState(175);
  const [gender, setGender] = useState("MALE");
  const [birthDate, setBirthDate] = useState("");

  if (!isOpen) return null;

  const handleSubmit = () => {
    onSubmit({
      weight: weight * 1000,
      height,
      birthDate,
      gender: gender as Gender,
    });
  };

  return (
    <div
      className="modal fixed inset-0 z-50 flex items-center justify-center"
      style={{ opacity: 1, pointerEvents: "auto" }}
    >
      <div className="modal-container bg-white w-full max-w-md mx-auto rounded-lg shadow-lg z-50 overflow-y-auto max-h-screen relative">
        <div className="modal-content py-4 px-6">
          <div className="flex justify-between items-center mb-4">
            <h3 className="text-lg font-medium text-gray-800">
              Добавить сведения о здоровье
            </h3>
            <button
              id="close-physical-modal"
              className="text-gray-400 hover:text-gray-500"
              onClick={onClose}
              type="button"
            >
              <i className="fas fa-times"></i>
            </button>
          </div>

          <form
            id="update-physical-form"
            onSubmit={(e) => {
              e.preventDefault();
              handleSubmit();
            }}
          >
            <div className="mb-4">
              <label
                className="block text-gray-700 text-sm font-medium mb-2"
                htmlFor="update-weight-input"
              >
                Вес (кг)
              </label>
              <input
                type="number"
                id="update-weight-input"
                min="30"
                max="200"
                step="0.1"
                value={weight}
                onChange={(e) => setWeight(Number(e.target.value))}
                className="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-green-500 focus:border-green-500"
              />
            </div>

            <div className="mb-4">
              <label
                className="block text-gray-700 text-sm font-medium mb-2"
                htmlFor="update-height-input"
              >
                Рост (см)
              </label>
              <input
                type="number"
                id="update-height-input"
                min="100"
                max="250"
                step="1"
                value={height}
                onChange={(e) => setHeight(Number(e.target.value))}
                className="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-green-500 focus:border-green-500"
              />
            </div>

            <div className="mb-4">
              <label
                className="block text-gray-700 text-sm font-medium mb-2"
                htmlFor="update-gender-select"
              >
                Пол
              </label>
              <select
                id="update-gender-select"
                value={gender}
                onChange={(e) => setGender(e.target.value)}
                className="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-green-500 focus:border-green-500"
              >
                <option value="MALE">{GENDER_LABELS["MALE"]}</option>
                <option value="FEMALE">{GENDER_LABELS["FEMALE"]}</option>
              </select>
            </div>

            <div className="mb-4">
              <label
                className="block text-gray-700 text-sm font-medium mb-2"
                htmlFor="update-birthdate-input"
              >
                Дата рождения
              </label>
              <input
                type="date"
                id="update-birthdate-input"
                value={birthDate}
                onChange={(e) => setBirthDate(e.target.value)}
                className="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-green-500 focus:border-green-500"
              />
            </div>

            <div className="flex space-x-3">
              <button
                type="button"
                id="cancel-physical-update"
                className="flex-1 py-2 bg-gray-100 text-gray-800 rounded-lg font-medium"
                onClick={onClose}
              >
                Отменить
              </button>
              <button
                type="submit"
                className="flex-1 py-2 bg-green-500 text-white font-medium rounded-md hover:bg-green-600"
              >
                Сохранить
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}

export default PhysicHealthModal;
