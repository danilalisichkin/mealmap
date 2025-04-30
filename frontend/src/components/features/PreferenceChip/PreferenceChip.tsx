import React from "react";
import { PreferenceType } from "../../../api/preference/enums/PreferenceType";
import "./PreferenceChip.css";

interface PreferenceChipProps {
  text: string;
  type: PreferenceType;
  onRemove: () => void;
}

const PreferenceChip: React.FC<PreferenceChipProps> = ({
  text,
  type,
  onRemove,
}) => (
  <div
    className={`preference-chip flex items-center justify-between bg-${
      type === PreferenceType.LIKED ? "green" : "red"
    }-50 rounded-full px-4 py-2`}
  >
    <span className="font-medium text-gray-800 mr-2">{text}</span>
    <button
      className="ml-2 text-gray-400 hover:text-red-500"
      onClick={onRemove}
    >
      <i className="fas fa-times"></i>
    </button>
  </div>
);

export default PreferenceChip;
