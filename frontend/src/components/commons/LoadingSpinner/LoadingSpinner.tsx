import React from "react";
import "./LoadingSpinner.css";

const LoadingSpinner: React.FC = () => {
  return (
    <div className="flex items-center justify-center h-full">
      <div className="spinner"></div>
    </div>
  );
};

export default LoadingSpinner;
