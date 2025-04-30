import React from "react";

interface ButtonProps {
  label: string;
  onClick: () => void;
}

const FilterToggleButton: React.FC<ButtonProps> = ({ label, onClick }) => {
  return (
    <button
      id="filter-toggle"
      className="flex items-center justify-center gap-2 text-green-600 hover:text-green-800 border border-green-600 hover:border-green-800 px-4 py-2 rounded-lg transition-colors"
      onClick={onClick}
    >
      <i className="fas fa-filter"></i>
      <span>{label}</span>
    </button>
  );
};

export default FilterToggleButton;
