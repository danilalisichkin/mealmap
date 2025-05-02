import React from "react";

interface NotFoundErrorProps {
  title: string;
  message: string;
}

const NotFoundError: React.FC<NotFoundErrorProps> = ({ title, message }) => {
  return (
    <div className="flex flex-col items-center justify-center min-h-screen bg-gray-100">
      <div className="relative">
        <img
          src="/assets/images/404.webp"
          className="w-96 h-auto"
          alt="resource not found"
        />
      </div>
      <h2 className="text-4xl font-bold text-gray-800 text-center mt-6">{title}</h2>
      <p className="text-gray-600 text-center mt-4 max-w-lg">{message}</p>
    </div>
  );
};

export default NotFoundError;
