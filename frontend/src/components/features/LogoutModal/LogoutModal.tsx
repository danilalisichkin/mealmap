import React from "react";

interface LogoutModalProps {
  isOpen: boolean;
  onClose: () => void;
  onConfirm: () => void;
}

const LogoutModal: React.FC<LogoutModalProps> = ({
  isOpen,
  onClose,
  onConfirm,
}) => {
  if (!isOpen) return null;

  return (
    <div
      className={`modal fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-20 ${
        isOpen ? "active" : ""
      }`}
    >
      <div className="bg-white rounded-lg shadow-xl w-full max-w-md mx-4">
        <div className="p-6">
          <h3 className="text-lg font-medium text-gray-800 mb-4">
            Вы уверены, что хотите выйти?
          </h3>
          <div className="flex justify-end space-x-4">
            <button
              className="px-4 py-2 bg-gray-200 text-gray-700 rounded-md hover:bg-gray-300"
              onClick={onClose}
            >
              Отмена
            </button>
            <button
              className="px-4 py-2 bg-red-500 text-white rounded-md hover:bg-red-600"
              onClick={() => {
                onConfirm();
                onClose();
              }}
            >
              Выйти
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default LogoutModal;
