import React from "react";
import { ErrorDetail } from "../../../api/common/dto/ErrorDetail";

interface ErrorBannerProps {
  error: ErrorDetail;
}

const ErrorBanner: React.FC<ErrorBannerProps> = ({ error }) => {
  const image = (() => {
    switch (error.status) {
      case "401":
        return "/assets/images/401.webp";
      case "403":
        return "/assets/images/403.webp";
      case "404":
        return "/assets/images/404.webp";
      default:
        return "/assets/images/500.webp";
    }
  })();

  return (
    <div className="flex items-center justify-center min-h-screen bg-gray-100">
      <div className="flex flex-col md:w-1/2 justify-center bg-gray-100 mt-8 p-6">
        <div className="relative">
          <img src={image} alt="resource not found" />
        </div>
        <h2 className="text-4xl font-bold text-gray-800 text-center mt-6">
          {error.title}
        </h2>
        <p className="text-gray-600 text-center mt-4 max-w-lg">
          {error.detail}
        </p>
      </div>
    </div>
  );
};

export default ErrorBanner;
