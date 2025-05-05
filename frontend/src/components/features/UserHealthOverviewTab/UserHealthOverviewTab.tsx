import React from "react";
import { differenceInCalendarDays } from "date-fns";
import UserHealthWeightChart from "../UserHealthWeightChart/UserHealthWeightChart";
import { PhysicHealthDto } from "../../../api/health/dto/PhysicHealthDto";
import { DietDto } from "../../../api/health/dto/DietDto";
import { PhysicHealthHistoryDto } from "../../../api/health/dto/PhysicHealthHistoryDto";
import { DietType } from "../../../api/health/enums/DietType";

const DIET_LABELS: Record<string, string> = {
  WEIGHT_LOSS: "Похудение",
  WEIGHT_GAIN: "Набор массы",
  MAINTENANCE: "Поддержка текущего веса",
};

interface UserHealthOverviewTabProps {
  physicHealth: PhysicHealthDto;
  diet: DietDto | null;
  weightHistory: PhysicHealthHistoryDto[];
  bmi: string;
  age: number | string;
}

const UserHealthOverviewTab: React.FC<UserHealthOverviewTabProps> = ({
  physicHealth,
  diet,
  weightHistory,
  bmi,
  age,
}) => {
  let bmiLabel: string;
  if (Number(bmi) < 18.5) {
    bmiLabel = "Недостаточный вес";
  } else if (Number(bmi) < 25) {
    bmiLabel = "Нормальный вес";
  } else if (Number(bmi) < 30) {
    bmiLabel = "Избыточный вес";
  } else {
    bmiLabel = "Ожирение";
  }

  const formattedCurrentWeight = physicHealth.weight / 1000;
  const formattedPreviousWeight =
    weightHistory.length > 1
      ? weightHistory[weightHistory.length - 2].weight / 1000
      : 0;
  const formattedWeightDifference =
    formattedCurrentWeight - formattedPreviousWeight;

  const formattedDietGoalWeight = diet ? diet.goalWeight / 1000 : 0;
  const formattedDietStartWeight =
    weightHistory.length > 0 ? weightHistory[0].weight / 1000 : 0;
  const formattedRemainingWeight =
    formattedCurrentWeight - formattedDietGoalWeight;

  return (
    <div id="overview-tab" className="tab-panel active">
      <div className="health-card bg-white rounded-lg shadow-sm p-4 mb-4">
        <div className="flex justify-between items-center mb-2">
          <h3 className="font-medium text-gray-800">Текущий вес</h3>
        </div>
        <div className="flex items-end">
          <span
            className="text-3xl font-bold text-gray-800"
            id="current-weight"
          >
            {formattedCurrentWeight.toFixed(1)}
          </span>
          <span className="text-lg text-gray-500 ml-1">кг</span>
          {formattedWeightDifference !== 0 && (
            <span
              className={`ml-auto text-sm px-2 py-1 rounded-full ${
                formattedWeightDifference !== 0 &&
                diet &&
                ((formattedWeightDifference > 0 &&
                  diet.type !== DietType.WEIGHT_GAIN) ||
                  (formattedWeightDifference < 0 &&
                    diet.type !== DietType.WEIGHT_LOSS))
                  ? "text-red-500"
                  : "text-green-500"
              }`}
              id="weight-change-indicator"
            >
              <i
                className={`fas fa-arrow-${
                  formattedWeightDifference > 0 ? "up" : "down"
                } mr-1`}
              ></i>
              <span>{formattedWeightDifference.toFixed(1)} кг</span>
            </span>
          )}
        </div>
      </div>

      <div className="health-card bg-white rounded-lg shadow-sm p-4 mb-4">
        {!diet ? (
          <div className="flex-column items-center mb-2">
            <h3 className="font-medium text-gray-800">План диеты</h3>
            <p className="text-gray-600 mb-2">не найден</p>
            <p className="text-gray-600">Вы еще не выбрали диету</p>
          </div>
        ) : (
          <>
            <div className="flex justify-between items-center mb-2">
              <h3 className="font-medium text-gray-800">План диеты</h3>
              <span className="text-sm text-gray-500" id="diet-days">
                {`День ${Math.max(
                  1,
                  1 +
                    differenceInCalendarDays(
                      new Date(),
                      new Date(diet.startDate)
                    )
                )}`}
              </span>
            </div>
            <div className="mb-3 flex flex-col">
              <span className="text-l font-bold text-gray-800" id="diet-type">
                {DIET_LABELS[diet.type]}
              </span>
              <span id="goal-weight" className="text-gray-500">
                {`Цель: ${formattedDietStartWeight} → ${formattedDietGoalWeight} кг`}
              </span>
            </div>
            <div className="mb-1 flex justify-between text-sm text-gray-600">
              <span id="diet-start-date">
                {` Начало: ${new Date(diet.startDate).toLocaleDateString()}`}
              </span>
              <span id="diet-remaining">
                {` Осталось до цели: ${formattedRemainingWeight.toFixed(1)} кг`}
              </span>
            </div>
            <div className="w-full bg-gray-200 rounded-full h-2.5">
              <div
                className="progress-bar bg-green-500 h-2.5 rounded-full"
                style={{
                  width: `${
                    100 *
                    Math.min(
                      1,
                      Math.max(
                        0,
                        (formattedCurrentWeight - formattedDietStartWeight) /
                          (formattedDietGoalWeight - formattedDietStartWeight)
                      )
                    )
                  }%`,
                }}
              />
            </div>
          </>
        )}
      </div>
      <div className="health-card bg-white rounded-lg shadow-sm p-4 mb-4">
        <h3 className="font-medium text-gray-800 mb-3">
          Физические показатели
        </h3>
        <div className="grid grid-cols-3 gap-4 text-center">
          <div>
            <div className="text-sm text-gray-500">Рост</div>
            <div className="font-bold text-gray-800" id="height-value">
              {physicHealth.height}
            </div>
            <div className="text-xs text-gray-500">см</div>
          </div>
          <div>
            <div className="text-sm text-gray-500">ИМТ</div>
            <div className="font-bold text-gray-800" id="bmi-value">
              {bmi}
            </div>
            <div className="text-xs text-gray-500">
              {bmi !== "-" && bmiLabel}
            </div>
          </div>
          <div>
            <div className="text-sm text-gray-500">Возраст</div>
            <div className="font-bold text-gray-800" id="age-value">
              {age}
            </div>
            <div className="text-xs text-gray-500">лет</div>
          </div>
        </div>
      </div>

      <div className="health-card bg-white rounded-lg shadow-sm p-4">
        <h3 className="font-medium text-gray-800">История веса</h3>
        <div className="h-64">
          <UserHealthWeightChart history={weightHistory} />
        </div>
      </div>
    </div>
  );
};

export default UserHealthOverviewTab;
