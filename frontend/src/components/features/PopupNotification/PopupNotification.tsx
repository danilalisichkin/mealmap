import React, { useEffect, useState } from "react";
import "./PopupNotification.css";

interface PopupNotificationProps {
  message: string;
  type: "success" | "error" | "info";
  isVisible: boolean;
  duration?: number;
  onClose?: () => void;
}

const PopupNotification: React.FC<PopupNotificationProps> = ({
  message,
  type,
  isVisible,
  duration = 3000,
  onClose,
}) => {
  const [shouldRender, setShouldRender] = useState(isVisible);
  const [shouldHide, setShouldHide] = useState(false);

  useEffect(() => {
    if (isVisible) {
      setShouldRender(true);
    }
  }, [isVisible]);

  useEffect(() => {
    if (!isVisible) return;
    const timer = setTimeout(() => handleClose(), duration);
    return () => clearTimeout(timer);
  }, [isVisible, duration]);

  const handleClose = () => {
    setShouldHide(true);
    setTimeout(() => {
        setShouldRender(false);
        onClose?.();
    }, 500);
  };

  const notificationStyle = {
    success: {
      backgroundColor: "green",
      color: "green",
      borderColor: "green",
    },
    error: {
      backgroundColor: "red",
      color: "red",
      borderColor: "red",
    },
    info: {
      backgroundColor: "blue",
      color: "blue",
      borderColor: "blue",
    },
  }[type];

  const getNotificationIcon = () => {
    switch (type) {
      case "success":
        return <i className="fas fa-check-circle text-green-500 text-xl"></i>;
      case "error":
        return <i className="fas fa-exclamation-circle text-red-500 text-xl"></i>;
      case "info":
        return <i className="fas fa-info-circle text-blue-500 text-xl"></i>;
      default:
        return null;
    }
  };

  return (
    <>
      {shouldRender && (
        <div
          className={`popup-notification rounded-lg ${shouldHide ? "hide" : "show"}`}
          style={{ backgroundColor: notificationStyle.backgroundColor }}
        >
          <div
            className="notification bg-white rounded-lg shadow-lg overflow-hidden w-80 border-l-4"
            style={{ borderColor: notificationStyle.borderColor }}
          >
            <div className="flex items-start p-4">
              <div className="flex-shrink-0">
                <div
                  className="w-10 h-10 rounded-full flex items-center justify-center"
                  style={{ backgroundColor: `${notificationStyle.backgroundColor}20` }}
                >
                  {getNotificationIcon()}
                </div>
              </div>
              <div className="ml-3 flex-1 pt-0.5">
                <p className="text-sm font-medium" style={{ color: notificationStyle.color }}>
                  {message}
                </p>
              </div>
              <button
                className="ml-4 flex-shrink-0 text-gray-400 hover:text-gray-500"
                onClick={handleClose}
              >
                <i className="fas fa-times"></i>
              </button>
            </div>
          </div>
        </div>
      )}
    </>
  );
};

export default PopupNotification;
