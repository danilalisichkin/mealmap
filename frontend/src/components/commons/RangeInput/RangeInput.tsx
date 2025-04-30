import React from "react";
import "./RangeInput.css";

interface RangeInputProps {
  label: string;
  minValue: number | undefined;
  maxValue: number | undefined;
  onChange: (
    newMinValue: number | undefined,
    newMaxValue: number | undefined
  ) => void;
}

const RangeInput: React.FC<RangeInputProps> = ({
  label,
  minValue,
  maxValue,
  onChange,
}) => {
  const handleMinChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const value = e.target.value === "" ? undefined : Number(e.target.value);
    console.log("NEW MIN VALUE:", value);
    onChange(value, maxValue);
  };

  const handleMaxChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const value = e.target.value === "" ? undefined : Number(e.target.value);
    console.log("NEW MAX VALUE:", value);
    onChange(minValue, value);
  };

  return (
    <div className="space-y-2">
      <label className="block text-sm font-medium text-gray-700">{label}</label>
      <div className="flex items-center space-x-2">
        <input
          type="number"
          value={minValue ?? ""}
          placeholder="От"
          min="0"
          className="w-full px-2 py-1 border border-gray-300 rounded-md text-sm"
          onChange={handleMinChange}
        />
        <span>-</span>
        <input
          type="number"
          value={maxValue ?? ""}
          placeholder="До"
          min="0"
          className="w-full px-2 py-1 border border-gray-300 rounded-md text-sm"
          onChange={handleMaxChange}
        />
      </div>
    </div>
  );
};

export default RangeInput;
