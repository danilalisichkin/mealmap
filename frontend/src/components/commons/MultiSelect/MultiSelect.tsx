import React, { useState } from "react";
import "./MultiSelect.css";

interface MultiSelectProps {
  title: string;
  options: Option[];
  onSelect: (selectedValues: string[]) => void;
}

interface Option {
  label: string;
  value: string;
}

const MultiSelect: React.FC<MultiSelectProps> = ({
  title,
  options,
  onSelect,
}) => {
  const [selectedValues, setSelectedValues] = useState<string[]>([]);

  const handleSelect = (value: string) => {
    if (value === "all") {
      setSelectedValues(["all"]);
      onSelect(["all"]);
    } else {
      setSelectedValues((prev) => {
        const newValues = prev.includes(value)
          ? prev.filter((v) => v !== value)
          : [...prev.filter((v) => v !== "all"), value];
        onSelect(newValues);
        return newValues;
      });
    }
  };

  return (
    <div className="mb-6">
      <h3 className="text-sm font-semibold text-gray-700 mb-1">{title}</h3>
      <div className="flex overflow-x-auto space-x-2 pb-1 hide-scrollbar">
        {options.map((option) => (
          <button
            key={option.value}
            className={`px-3 py-1 rounded-full whitespace-nowrap text-xs text-gray-700 transition ${
              selectedValues.includes(option.value)
                ? "text-white bg-green-500 hover:bg-green-600"
                : "bg-gray-100 hover:bg-gray-200"
            }`}
            onClick={() => handleSelect(option.value)}
          >
            {option.label}
          </button>
        ))}
      </div>
    </div>
  );
};

export default MultiSelect;
