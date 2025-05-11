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
import { PhysicHealthDto } from "../../api/health/dto/PhysicHealthDto";
import { DietType } from "../../api/health/enums/DietType";
import ErrorBanner from "../../components/commons/ErrorBanner/ErrorBanner";
import { ErrorDetail } from "../../api/common/dto/ErrorDetail";
import { PhysicHealthCreationDto } from "../../api/health/dto/PhysicHealthCreationDto";
import LoadingSpinner from "../../components/commons/LoadingSpinner/LoadingSpinner";

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
  const [error, setError] = useState<ErrorDetail | null>(null);

  const fetchPhysicHealth = useCallback(async () => {
    if (!userId) {
      setError({
        title: "Пользователь не авторизован",
        detail: "Пользователь не авторизован",
        status: "401",
      });
      setLoading(false);
      return;
    }

    try {
      setLoading(true);
      setError(null);

      const response = await HealthApi.getUserPhysicHealth(userId);
      setPhysicHealth(response);
    } catch (err: any) {
      if (err.response?.status === 404) {
        console.error("Информация о здоровье не найдена:", err);
        setPhysicModalOpen(true);
      } else {
        console.error("Ошибка при загрузке физического здоровья:", err);
        setError({
          title: "Что-то пошло не так",
          detail: err.response?.data.detail,
          status: "500",
        });
      }
    } finally {
      setLoading(false);
    }
  }, [userId]);

  const fetchDiet = useCallback(async () => {
    if (!userId) {
      setError({
        title: "Пользователь не авторизован",
        detail: "Пользователь не авторизован",
        status: "401",
      });
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
        setError({
          title: "Что-то пошло не так",
          detail: err.response?.data.detail,
          status: "500",
        });
      }
    } finally {
      setLoading(false);
    }
  }, [userId]);

  const fetchPhysicHealthHistory = useCallback(async () => {
    if (!userId) {
      setError({
        title: "Пользователь не авторизован",
        detail: "Пользователь не авторизован",
        status: "401",
      });
      setLoading(false);
      return;
    }

    try {
      setLoading(true);
      setError(null);

      const response = await HealthApi.getUserPhysicHealthHistory(userId);
      setWeightHistory(response);
    } catch (err: any) {
      if (err.response?.status === 404) {
        console.error("Ошибка при загрузке истории здоровья:", err);
        setWeightHistory([]);
      } else if (err.response?.status === 500) {
        console.error("Ошибка при загрузке истории здоровья:", err);
        setError({
          title: "Что-то пошло не так",
          detail: err.response?.data.detail,
          status: "500",
        });
      }
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

  const handleCreatePhysicHealth = async (data: PhysicHealthCreationDto) => {
    if (!userId) {
      setError({
        title: "Пользователь не авторизован",
        detail: "Пользователь не авторизован",
        status: "401",
      });
      return;
    }

    try {
      setLoading(true);
      setError(null);

      const newPhysicHealth = await HealthApi.createUserPhysicHealth(
        userId,
        data
      );
      setPhysicHealth(newPhysicHealth);
      setSuccessMessage("Информация о здоровье успешно добавлена!");
      setPhysicModalOpen(false);
    } catch (err: any) {
      console.error("Ошибка при создании информации о здоровье:", err);
      setError({
        title: "Ошибка при создании информации о здоровье",
        detail: err.response?.data.detail,
        status: "400",
      });
    } finally {
      setLoading(false);
    }
  };

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
    } catch (err: any) {
      if (err.response?.status === 400) {
        setError({
          title: "Некорректный запрос",
          detail: err.response?.data.detail,
          status: "400",
        });
      }
    } finally {
      setLoading(false);
    }
  };

  const handleDeleteDiet = async () => {
    if (!userId) {
      setError({
        title: "Пользователь не авторизован",
        detail: "Пользователь не авторизован",
        status: "401",
      });
      return;
    }

    try {
      setLoading(true);
      setError(null);

      await HealthApi.deleteUserDiet(userId);
      setDiet(null);
      setSuccessMessage("Диета успешно завершена!");
    } catch (err: any) {
      if (err.response?.status === 400) {
        setError({
          title: "Некорректный запрос",
          detail: err.response?.data.detail,
          status: "400",
        });
      }
    } finally {
      setLoading(false);
    }
  };

  const handleCreateDiet = async (type: DietType, goalWeight: number) => {
    if (!userId) {
      setError({
        title: "Пользователь не авторизован",
        detail: "Пользователь не авторизован",
        status: "401",
      });
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
    } catch (err: any) {
      if (err.response?.status === 400) {
        setError({
          title: "Некорректный запрос",
          detail: err.response?.data.detail,
          status: "400",
        });
      }
    } finally {
      setLoading(false);
    }
  };

  if (loading) {
    return (
      <div className="min-h-screen flex items-center justify-center">
        <LoadingSpinner />
      </div>
    );
  }

  if (error) {
    return (
      <div className="min-h-screen flex items-center justify-center">
        <ErrorBanner error={error} />
      </div>
    );
  }

  const age = physicHealth
    ? Math.floor(
        differenceInCalendarDays(new Date(), new Date(physicHealth.birthDate)) /
          365.25
      )
    : "-";

  return (
    <main className="container min-h-screen mx-auto px-4 py-4">
      {physicHealth && (
        <>
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
        </>
      )}
      <PhysicHealthModal
        isOpen={isPhysicModalOpen}
        onClose={() => setPhysicModalOpen(false)}
        onSubmit={handleCreatePhysicHealth}
      />
    </main>
  );
};

export default UserHealthPage;
