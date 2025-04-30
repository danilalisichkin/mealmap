import React from "react";
import {
  LineChart,
  Line,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  ResponsiveContainer,
  ReferenceDot,
} from "recharts";
import { PhysicHealthHistoryDto } from "../../../api/health/dto/PhysicHealthHistoryDto";

interface UserHealthWeightChartProps {
  history: PhysicHealthHistoryDto[];
}

const formatDate = (dateStr: string) => {
  const date = new Date(dateStr);
  return date.toLocaleDateString("ru-RU", { day: "2-digit", month: "2-digit" });
};

const UserHealthWeightChart: React.FC<UserHealthWeightChartProps> = ({
  history,
}) => {
  const data = history.map((entry) => ({
    date: formatDate(entry.createdAt),
    weight: entry.weight / 1000,
  }));

  return (
    <ResponsiveContainer width="100%" height="100%">
      <LineChart
        data={data}
        margin={{ top: 16, right: 24, left: 10, bottom: 8 }}
      >
        <CartesianGrid
          strokeDasharray="3 3"
          vertical={false}
          stroke="#e5e7eb"
        />
        <XAxis dataKey="date" tick={{ fontSize: 12, fill: "#6b7280" }} />
        <YAxis
          tick={{ fontSize: 12, fill: "#6b7280" }}
          width={40}
          domain={["auto", "auto"]}
          tickFormatter={(v) => `${v} кг`}
        />
        <Tooltip
          formatter={(value: number) => [`Вес: ${value} кг`]}
          labelFormatter={(label) => `Дата: ${label}`}
          contentStyle={{ borderRadius: 8, fontSize: 14 }}
        />
        <Line
          type="monotone"
          dataKey="weight"
          stroke="#22c55e"
          strokeWidth={3}
          dot={{ r: 5, stroke: "#22c55e", strokeWidth: 2, fill: "#fff" }}
          activeDot={{ r: 7, fill: "#22c55e" }}
        />
        {data.length > 0 && (
          <ReferenceDot
            x={data[data.length - 1].date}
            y={data[data.length - 1].weight}
            r={7}
            fill="#22c55e"
            stroke="#fff"
            strokeWidth={2}
            isFront
          />
        )}
      </LineChart>
    </ResponsiveContainer>
  );
};

export default UserHealthWeightChart;
