import React from "react";
import "./NutrientChart.css";

interface NutrientChartProps {
  totalProteins: number;
  totalFats: number;
  totalCarbs: number;
  totalFiber: number;
  totalSugar: number;
  scaleFactor: number;
}

const NutrientChart: React.FC<NutrientChartProps> = ({
  totalProteins,
  totalFats,
  totalCarbs,
  totalFiber,
  totalSugar,
  scaleFactor,
}) => (
  <>
    <div className="nutrient-chart mb-4" id="nutrition-chart">
      <div
        className="nutrient-chart-bar proteins"
        style={{ height: `${totalProteins * scaleFactor}px` }}
      />
      <div
        className="nutrient-chart-bar fats"
        style={{ height: `${totalFats * scaleFactor}px` }}
      />
      <div
        className="nutrient-chart-bar carbs"
        style={{ height: `${totalCarbs * scaleFactor}px` }}
      />
      <div
        className="nutrient-chart-bar fiber"
        style={{ height: `${totalFiber * scaleFactor}px` }}
      />
      <div
        className="nutrient-chart-bar sugar"
        style={{ height: `${totalSugar * scaleFactor}px` }}
      />
    </div>
    <div className="flex justify-between text-xs text-gray-500 mb-4 px-1">
      <span className="w-1/5 text-center flex flex-col items-center">
        <span>Белки</span>
        <span>{totalProteins} г</span>
      </span>
      <span className="w-1/5 text-center flex flex-col items-center">
        <span>Жиры</span>
        <span>{totalFats} г</span>
      </span>
      <span className="w-1/5 text-center flex flex-col items-center">
        <span>Углеводы</span>
        <span>{totalCarbs} г</span>
      </span>
      <span className="w-1/5 text-center flex flex-col items-center">
        <span>Клетчатка</span>
        <span>{totalFiber} г</span>
      </span>
      <span className="w-1/5 text-center flex flex-col items-center">
        <span>Сахар</span>
        <span>{totalSugar} г</span>
      </span>
    </div>
  </>
);

export default NutrientChart;