import React from "react";
import "./AllergenChip.css";

interface AllergenChipProps {
  text: string;
  onRemove: () => void;
}

const AllergenChip: React.FC<AllergenChipProps> = ({ text, onRemove }) => (
  <div className="allergen-chip flex items-center justify-between bg-red-50 rounded-full px-4 py-2">
    <span className="font-medium text-gray-800 mr-2">{text}</span>
    <button
      className="ml-2 text-gray-400 hover:text-red-500"
      onClick={onRemove}
    >
      <i className="fas fa-times"></i>
    </button>
  </div>
);

export default AllergenChip;
