import React from "react";
import "./RecommendationShuffling.css";

interface RecommendationsShufflingProps {
  typedText: string;
}

const RecommendationsShuffling: React.FC<RecommendationsShufflingProps> = ({
  typedText,
}) => (
  <div id="ai-processing" className="text-center py-8">
    <div className="plate-container">
      <div className="plate pulse">
        <i className="fas fa-utensils text-gray-300 text-4xl z-10"></i>
      </div>
      <div className="food-item food-yellow" id="food-1">
        <i className="fas fa-apple-alt"></i>
      </div>
      <div className="food-item food-green" id="food-2">
        <i className="fas fa-carrot"></i>
      </div>
      <div className="food-item food-red" id="food-3">
        <i className="fas fa-drumstick-bite"></i>
      </div>
      <div className="food-item food-blue" id="food-4">
        <i className="fas fa-fish"></i>
      </div>
    </div>
    <div className="inline-block mb-4">
      <div className="flex items-center justify-center space-x-2 mb-3">
        <div className="w-2 h-2 rounded-full bg-green-400 animate-pulse"></div>
        <div className="w-2 h-2 rounded-full bg-green-400 animate-pulse delay-100"></div>
        <div className="w-2 h-2 rounded-full bg-green-400 animate-pulse delay-200"></div>
      </div>
      <p className="text-gray-700 font-medium">{typedText}</p>
    </div>
  </div>
);

export default RecommendationsShuffling;
