import React from "react";

interface RecommendationPromptProps {
  onGenerate: () => void;
}

const RecommendationPrompt: React.FC<RecommendationPromptProps> = ({
  onGenerate,
}) => (
  <div id="recommendations-prompt" className="text-center py-6">
    <div className="w-20 h-20 mx-auto mb-4 rounded-full bg-green-50 flex items-center justify-center text-green-500">
      <i className="fas fa-utensils text-3xl"></i>
    </div>
    <p className="text-gray-600 mb-6">
      Я проанализирую Ваши предпочтения, здоровье, цели и подберу подходящие
      блюда для Вас
    </p>
    <button
      id="generate-recommendations-btn"
      className="px-6 py-3 bg-gradient-to-r from-green-500 to-green-600 text-white rounded-full font-medium hover:from-green-600 hover:to-green-700 transition-all shadow-md hover:shadow-lg flex items-center mx-auto"
      onClick={onGenerate}
    >
      <i className="fas fa-magic mr-2"></i> Подобрать блюда
    </button>
  </div>
);

export default RecommendationPrompt;
