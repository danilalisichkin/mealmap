import React from "react";
import { useNavigate } from "react-router-dom";

interface UserProfileStatsProps {
  totalOrders: number;
  totalDiscounted: number;
}

const UserProfileStats: React.FC<UserProfileStatsProps> = ({
  totalOrders,
  totalDiscounted,
}) => {
  const navigate = useNavigate();
  
  //TODO: API CALL
  const isHealthSet = true;

  return (
    <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-4 mb-8">
      <button
        type="button"
        className="bg-white rounded-lg shadow-sm p-6 hover-scale flex items-center w-full transition hover:bg-green-50 "
        onClick={() => navigate("/user/health")}
      >
        <div className="p-3 rounded-full bg-blue-100 text-blue-500 mr-4">
          <i className="fas fa-heartbeat"></i>
        </div>
        <div className="text-left">
          <p className="text-gray-500 text-sm">Здоровье</p>
          <p className="text-xl font-bold text-gray-800">{isHealthSet ? "Просмотреть" : "Указать"}</p>
        </div>
      </button>
      <button
        type="button"
        className="bg-white rounded-lg shadow-sm p-6 hover-scale flex items-center w-full transition hover:bg-blue-50"
        onClick={() => navigate("/orders")}
      >
        <div className="p-3 rounded-full bg-green-100 text-green-500 mr-4">
          <i className="fas fa-utensils"></i>
        </div>
        <div className="text-left">
          <p className="text-gray-500 text-sm">Заказов</p>
          <p className="text-xl font-bold text-gray-800">{totalOrders}</p>
        </div>
      </button>
      <div className="bg-white rounded-lg shadow-sm p-6 hover-scale">
        <div className="flex items-center">
          <div className="p-3 rounded-full bg-purple-100 text-purple-500 mr-4">
            <i className="fas fa-tag"></i>
          </div>
          <div>
            <p className="text-gray-500 text-sm">Сэкономлено</p>
            <p className="text-xl font-bold text-gray-800">
              {totalDiscounted.toFixed(2)}₽
            </p>
          </div>
        </div>
      </div>
    </div>
  );
};

export default UserProfileStats;
