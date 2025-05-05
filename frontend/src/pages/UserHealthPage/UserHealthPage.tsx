import React, { useCallback, useEffect, useState } from "react";
import "./UserHealthPage.css";
import PhysicHealthModal from "../../components/features/PhysicHealthModal/PhysicHealthModal";
import { differenceInCalendarDays } from "date-fns";
import UserHealthOverviewTab from "../../components/features/UserHealthOverviewTab/UserHealthOverviewTab";
import UserHealthDietTab from "../../components/features/UserHealthDietTab/UserHealthDietTab";
import UserHealthPhysicalTab from "../../components/features/UserHealthPhysicalTab/UserHealthPhysicalTab";
import { useLocation } from "react-router-dom";
import { HealthApi } from "../../api/health/UserHealthApi";
import { DietDto } from "../../api/health/dto/DietDto";
import { PhysicHealthHistoryDto } from "../../api/health/dto/PhysicHealthHistoryDto";
import NotFoundError from "../../components/commons/NotFoundError/NotFoundError";
import { PhysicHealthDto } from "../../api/health/dto/PhysicHealthDto";
import { DietType } from "../../api/health/enums/DietType";

interface UserHealthPageProps {}

const TABS = [
  { key: "overview", label: "Общие сведения" },
  { key: "diet", label: "Диета" },
  { key: "physical", label: "Физические показатели" },
];

const UserHealthPage: React.FC<UserHealthPageProps> = () => {
  const location = useLocation();
  const userId = location.state?.userId ?? null;

  const [physicHealth, setPhysicHealth] = useState<PhysicHealthDto | null>(
    null
  );
  const [weightHistory, setWeightHistory] = useState<PhysicHealthHistoryDto[]>(
    []
  );
  const [diet, setDiet] = useState<DietDto | null>(null);
  const [bmi, setBmi] = useState<string>("-");

  const [isPhysicModalOpen, setPhysicModalOpen] = useState<boolean>(false);
  const [activeTab, setActiveTab] = useState<string>("overview");

  const [successMessage, setSuccessMessage] = useState<string | null>(null);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  const fetchPhysicHealth = useCallback(async () => {
    if (!userId) {
      setError("Пользователь не авторизован");
      setLoading(false);
      return;
    }

    try {
      setLoading(true);
      setError(null);

      const response = await HealthApi.getUserPhysicHealth(userId);
      setPhysicHealth(response);
    } catch (err) {
      console.error("Ошибка при загрузке физического здоровья:", err);
      setError("Не удалось загрузить данные физического здоровья.");
    } finally {
      setLoading(false);
    }
  }, [userId]);

  const fetchDiet = useCallback(async () => {
    if (!userId) {
      setError("Пользователь не авторизован");
      setLoading(false);
      return;
    }

    try {
      setLoading(true);
      setError(null);

      const response = await HealthApi.getUserDiet(userId);
      setDiet(response);
    } catch (err: any) {
      if (err.response?.status === 404) {
        setDiet(null);
      } else {
        console.error("Ошибка при загрузке диеты:", err);
        setError("Не удалось загрузить данные диеты.");
      }
    } finally {
      setLoading(false);
    }
  }, [userId]);

  const fetchPhysicHealthHistory = useCallback(async () => {
    if (!userId) {
      setError("Пользователь не авторизован");
      setLoading(false);
      return;
    }

    try {
      setLoading(true);
      setError(null);

      const response = await HealthApi.getUserPhysicHealthHistory(userId);
      setWeightHistory(response);
    } catch (err) {
      console.error("Ошибка при загрузке диеты:", err);
      setError("Не удалось загрузить данные диеты.");
    } finally {
      setLoading(false);
    }
  }, [userId]);

  useEffect(() => {
    fetchPhysicHealth();
    fetchDiet();
    fetchPhysicHealthHistory();
  }, [fetchPhysicHealth, fetchDiet, fetchPhysicHealthHistory]);

  useEffect(() => {
    if (physicHealth) {
      const calculateBmi = (weight: number, height: number) => {
        return weight && height
          ? (weight / (height / 100) ** 2).toFixed(1)
          : "-";
      };

      setBmi(calculateBmi(physicHealth.weight / 1000, physicHealth.height));
    }
  }, [physicHealth]);

  const handleSaveWeight = async (newWeight: number) => {
    if (!userId) {
      return;
    }

    try {
      setLoading(true);
      setError(null);
      setSuccessMessage(null);

      const updatedPhysicHealth = await HealthApi.updateUserPhysicHealth(
        userId,
        {
          weight: newWeight,
        }
      );
      setPhysicHealth(updatedPhysicHealth);

      fetchPhysicHealthHistory();

      setSuccessMessage("Вес успешно обновлён!");
    } catch (err) {
      console.error("Ошибка при обновлении веса:", err);
      setError("Не удалось обновить вес. Попробуйте снова.");
    } finally {
      setLoading(false);
    }
  };

  const handleDeleteDiet = async () => {
    if (!userId) {
      setError("Пользователь не авторизован.");
      return;
    }

    try {
      setLoading(true);
      setError(null);

      await HealthApi.deleteUserDiet(userId);
      setDiet(null);
      setSuccessMessage("Диета успешно завершена!");
    } catch (err) {
      console.error("Ошибка при завершении диеты:", err);
      setError("Не удалось завершить диету. Попробуйте снова.");
    } finally {
      setLoading(false);
    }
  };

  const handleCreateDiet = async (type: DietType, goalWeight: number) => {
    if (!userId) {
      setError("Пользователь не авторизован.");
      return;
    }

    if (diet) {
      handleDeleteDiet();
    }

    try {
      setLoading(true);
      setError(null);

      const newDiet = await HealthApi.createUserDiet(userId, {
        type: type,
        goalWeight: goalWeight * 1000,
      });
      setDiet(newDiet);
      setSuccessMessage("Новая диета успешно создана!");
    } catch (err) {
      console.error("Ошибка при создании диеты:", err);
      setError("Не удалось создать диету. Попробуйте снова.");
    } finally {
      setLoading(false);
    }
  };

  if (loading) {
    return <div className="text-center py-12">Загрузка...</div>;
  }

  if (error) {
    return <div className="text-center py-12 text-red-500">{error}</div>;
  }

  if (!physicHealth) {
    return (
      <NotFoundError
        title="Упс! Кажется, информация о Вашем здоровье не найдена"
        message="Похоже, что Вы еще не заполняли форму"
      />
    );
  }

  const age = Math.floor(
    differenceInCalendarDays(new Date(), new Date(physicHealth.birthDate)) /
      365.25
  );

  return (
    <main className="container mx-auto px-4 py-4">
      <div className="flex border-b mb-6">
        {TABS.map((tab) => (
          <button
            key={tab.key}
            className={`tab-button px-4 py-2 font-medium ${
              activeTab === tab.key ? "active" : ""
            }`}
            onClick={() => setActiveTab(tab.key)}
            data-tab={tab.key}
            type="button"
          >
            {tab.label}
          </button>
        ))}
      </div>

      <div id="tab-content">
        {activeTab === "overview" && (
          <UserHealthOverviewTab
            physicHealth={physicHealth}
            diet={diet}
            weightHistory={weightHistory}
            bmi={bmi}
            age={age}
          />
        )}

        {activeTab === "diet" && (
          <UserHealthDietTab
            diet={diet}
            currentWeight={physicHealth.weight}
            onDeleteDiet={handleDeleteDiet}
            onCreateDiet={handleCreateDiet}
          />
        )}

        {activeTab === "physical" && (
          <UserHealthPhysicalTab
            physicHealth={physicHealth}
            age={age}
            onSaveWeight={handleSaveWeight}
            successMessage={successMessage}
          />
        )}
      </div>

      <PhysicHealthModal
        isOpen={isPhysicModalOpen}
        onClose={() => setPhysicModalOpen(false)}
      />
    </main>
  );
};

export default UserHealthPage;
