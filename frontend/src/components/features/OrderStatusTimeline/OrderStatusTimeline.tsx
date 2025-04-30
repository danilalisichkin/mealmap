import React from "react";
import OrderStatusStep from "../OrderStatusStep/OrderStatusStep";

interface OrderStatusTimelineProps {
  status: string;
  createdAt: string;
}

const steps = [
  { key: "NEW", label: "Заказ оформлен", icon: "fas fa-check" },
  {
    key: "IN_PROGRESS",
    label: "В процессе приготовления",
    icon: "fas fa-utensils",
  },
  { key: "READY", label: "Приготовлен", icon: "fas fa-check-double" },
  { key: "ON_THE_WAY", label: "Передан курьеру", icon: "fas fa-truck" },
  { key: "COMPLETED", label: "Доставлен", icon: "fas fa-home" },
];

const OrderStatusTimeline: React.FC<OrderStatusTimelineProps> = ({
  status,
  createdAt,
}) => {
  let activeIndex = steps.findIndex((s) => s.key === status);
  if (activeIndex === -1) activeIndex = 0;

  return (
    <div className="space-y-4">
      {steps.map((step, idx) => {
        let stepStatus: "done" | "current" | "pending" =
          idx < activeIndex
            ? "done"
            : idx === activeIndex
            ? "current"
            : "pending";
        return (
          <OrderStatusStep
            key={step.key}
            label={step.label}
            icon={step.icon}
            status={stepStatus}
            date={
              idx === 0 && stepStatus === "current"
                ? new Date(createdAt).toLocaleDateString() +
                  " в " +
                  new Date(createdAt).toLocaleTimeString()
                : undefined
            }
          />
        );
      })}
    </div>
  );
};

export default OrderStatusTimeline;
