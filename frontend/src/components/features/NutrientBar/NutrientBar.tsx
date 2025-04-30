import React from "react";
import "./NutrientBar.css";

interface NutrientBarProps {
  totalProteins: number;
  totalFats: number;
  totalCarbs: number;
  totalFiber: number;
  totalSugar: number;
}

const NutrientBar: React.FC<NutrientBarProps> = ({
  totalProteins,
  totalFats,
  totalCarbs,
  totalFiber,
  totalSugar,
}) => {
  const sumNutrients =
    totalProteins + totalFats + totalCarbs + totalFiber + totalSugar;

  const proteinsPercent = sumNutrients
    ? (totalProteins / sumNutrients) * 100
    : 0;
  const fatsPercent = sumNutrients ? (totalFats / sumNutrients) * 100 : 0;
  const carbsPercent = sumNutrients ? (totalCarbs / sumNutrients) * 100 : 0;
  const fiberPercent = sumNutrients ? (totalFiber / sumNutrients) * 100 : 0;
  const sugarPercent = sumNutrients ? (totalSugar / sumNutrients) * 100 : 0;

  const percentLabel = (value: number) => {
    if (value >= 8) {
      return `${value.toFixed(0)}%`;
    } else if (value >= 3) {
      return `${value.toFixed(0)}%`;
    } else {
      return "";
    }
  };

  return (
    <div className="w-full flex h-6 rounded-full overflow-hidden mb-6 bg-gray-100">
      <div
        className="nutrient-bar proteins flex items-center justify-center text-white font-medium"
        style={{ width: `${proteinsPercent}%` }}
        title={`Белки: ${proteinsPercent.toFixed(1)}%`}
      >
        {percentLabel(proteinsPercent)}
      </div>
      <div
        className="nutrient-bar fats flex items-center justify-center text-white font-medium"
        style={{ width: `${fatsPercent}%` }}
        title={`Жиры: ${fatsPercent.toFixed(1)}%`}
      >
        {percentLabel(fatsPercent)}
      </div>
      <div
        className="nutrient-bar carbs flex items-center justify-center text-white font-medium"
        style={{ width: `${carbsPercent}%` }}
        title={`Углеводы: ${carbsPercent.toFixed(1)}%`}
      >
        {percentLabel(carbsPercent)}
      </div>
      <div
        className="nutrient-bar fiber flex items-center justify-center text-white font-medium"
        style={{ width: `${fiberPercent}%` }}
        title={`Клетчатка: ${fiberPercent.toFixed(1)}%`}
      >
        {percentLabel(fiberPercent)}
      </div>
      <div
        className="nutrient-bar sugar flex items-center justify-center text-white font-medium"
        style={{ width: `${sugarPercent}%` }}
        title={`Сахар: ${sugarPercent.toFixed(1)}%`}
      >
        {percentLabel(sugarPercent)}
      </div>
    </div>
  );
};

export default NutrientBar;
