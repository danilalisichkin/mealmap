import React, { useCallback, useEffect, useState } from "react";
import UserProfileSidebar from "../../components/features/UserProfileSidebar/UserProfileSidebar";
import UserProfileStats from "../../components/features/UserProfileStats/UserProfileStats";
import UserProfileHistory from "../../components/features/UserProfileHistory/UserProfileHistory";
import UserPreferences from "../../components/features/UserPreferences/UserPreferences";
import { useAuth } from "../../contexts/AuthContext";
import { UserApi } from "../../api/user/UserApi";
import { UserDto } from "../../api/user/dto/UserDto";
import { StatusHistoryDto } from "../../api/user/dto/StatusHistoryDto";
import { PreferenceApi } from "../../api/preference/UserPreferenceApi";
import { UserPreferencesDto } from "../../api/preference/dto/UserPreferencesDto";
import ErrorBanner from "../../components/commons/ErrorBanner/ErrorBanner";
import { ErrorDetail } from "../../api/common/dto/ErrorDetail";
import PopupNotification from "../../components/features/PopupNotification/PopupNotification";
import LoadingSpinner from "../../components/commons/LoadingSpinner/LoadingSpinner";

interface UserProfilePageProps {}

const UserProfilePage: React.FC<UserProfilePageProps> = () => {
  const { userId } = useAuth();
  const [user, setUser] = useState<UserDto | null>(null);

  const [userStatusHistory, setUserStatusHistory] = useState<
    StatusHistoryDto[] | null
  >(null);
  const [userPreferences, setUserPreferences] =
    useState<UserPreferencesDto | null>(null);

  const tgChatId = 2;
  const totalOrders = 20;
  const totalDiscounted = 1000;

  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<ErrorDetail | null>(null);

  const [notification, setNotification] = useState<{
    id: number;
    message: string;
    type: "success" | "error" | "info";
    isVisible: boolean;
  }>({
    id: 0,
    message: "",
    type: "success",
    isVisible: false,
  });

  const showNotification = (
    message: string,
    type: "success" | "error" | "info"
  ) => {
    setNotification({
      id: Date.now(),
      message,
      type,
      isVisible: true,
    });
  };

  const fetchUser = useCallback(async () => {
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
      const fetchedUser = await UserApi.getUserById(userId);
      setUser(fetchedUser);
    } catch (err: any) {
      if (err.response?.status === 404) {
        console.error("Ошибка при загрузке пользователя:", err);
        setError({
          title: "Упс! Кажется, пользователь не найден",
          detail: err.response?.data.detail,
          status: "404",
        });
      } else if (err.response?.status === 500) {
        console.error("Ошибка при загрузке пользователя:", err);
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

  const fetchUserStatusHistory = useCallback(async () => {
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
      const fetchedHistory = await UserApi.getUserStatusHistory(userId);
      setUserStatusHistory(fetchedHistory.items);
    } catch (err) {
      console.error("Ошибка при загрузке пользователя:", err);
    } finally {
      setLoading(false);
    }
  }, [userId]);

  const fetchUserPreferences = useCallback(async () => {
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
      const fetchedPreferences = await PreferenceApi.getAllPreferences(userId);
      setUserPreferences(fetchedPreferences);
    } catch (err) {
      console.error("Ошибка при загрузке предпочтений пользователя:", err);
    }
  }, [userId]);

  useEffect(() => {
    fetchUser();
    fetchUserStatusHistory();
    fetchUserPreferences();
  }, [fetchUser, fetchUserStatusHistory, fetchUserPreferences]);

  if (loading) {
    return (
      <div className="h-screen flex items-center justify-center">
        <LoadingSpinner />
      </div>
    );
  }

  if (error) {
    return (
      <div className="h-screen flex items-center justify-center">
        <ErrorBanner error={error} />
      </div>
    );
  }

  const handleRemoveProductPreference = async (id: number) => {
    if (userPreferences) {
      try {
        await PreferenceApi.removeProductPreference(userPreferences.userId, id);
        setUserPreferences((prev) =>
          prev
            ? {
                ...prev,
                productPreferences: prev.productPreferences.filter(
                  (pref) => pref.productId !== id
                ),
              }
            : null
        );
        showNotification("Блюдо убрано из предпочтений!", "success");
      } catch (err) {
        console.error("Ошибка при удалении предпочтения продукта:", err);
      }
    }
  };

  const handleRemoveCategoryPreference = async (id: number) => {
    if (userPreferences) {
      try {
        await PreferenceApi.removeCategoryPreference(
          userPreferences.userId,
          id
        );
        setUserPreferences((prev) =>
          prev
            ? {
                ...prev,
                categoryPreferences: prev.categoryPreferences.filter(
                  (pref) => pref.categoryId !== id
                ),
              }
            : null
        );
        showNotification("Категория убрана из предпочтений!", "success");
      } catch (err) {
        console.error("Ошибка при удалении предпочтения категории:", err);
      }
    }
  };

  return (
    <main className="container mx-auto px-4 py-8">
      <div className="flex flex-col md:flex-row gap-8">
        <div className="w-full md:w-1/3 lg:w-1/4">
          {user && <UserProfileSidebar user={user} tgChatId={tgChatId} />}
        </div>
        <div className="w-full md:w-2/3 lg:w-3/4">
          <UserProfileStats
            totalOrders={totalOrders}
            totalDiscounted={totalDiscounted}
          />
          {userPreferences && (
            <UserPreferences
              userPreferences={userPreferences}
              onProductPreferenceRemove={handleRemoveProductPreference}
              onCategoryPreferenceRemove={handleRemoveCategoryPreference}
            />
          )}
          {userStatusHistory && (
            <UserProfileHistory history={userStatusHistory} />
          )}
        </div>
      </div>
      <PopupNotification
        key={notification.id}
        message={notification.message}
        type={notification.type}
        isVisible={notification.isVisible}
        onClose={() => setNotification({ ...notification, isVisible: false })}
      />
    </main>
  );
};

export default UserProfilePage;
