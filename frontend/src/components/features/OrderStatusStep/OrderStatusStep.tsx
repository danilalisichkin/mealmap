import React from "react";
import "./OrderStatusStep.css";

interface OrderStatusStepProps {
  label: string;
  icon: string;
  status: "done" | "current" | "pending";
  date?: string;
}

const OrderStatusStep: React.FC<OrderStatusStepProps> = ({
  label,
  icon,
  status,
  date,
}) => (
  <div className="order-timeline-item relative pl-8">
    <div
      className={`order-timeline-dot ${
        status === "done" || status === "current"
          ? "active bg-green-500"
          : "inactive"
      }`}
    >
      <i className={`${icon} text-xs`}></i>
    </div>
    <div className="ml-2">
      <h4 className="font-medium text-gray-800">{label}</h4>
      <p className="text-sm text-gray-600">
        {status === "done"
          ? "Выполнено"
          : status === "pending"
          ? "Ожидается"
          : date}
      </p>
    </div>
  </div>
);

export default OrderStatusStep;
