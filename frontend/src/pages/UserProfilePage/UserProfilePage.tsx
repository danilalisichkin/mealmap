import React, { useCallback, useEffect, useState } from "react";
import UserProfileSidebar from "../../components/features/UserProfileSidebar/UserProfileSidebar";
import UserProfileStats from "../../components/features/UserProfileStats/UserProfileStats";
import UserProfileHistory from "../../components/features/UserProfileHistory/UserProfileHistory";
import UserPreferences from "../../components/features/UserPreferences/UserPreferences";
import { mockPreferences } from "../../mock/preferences";
import { useAuth } from "../../contexts/AuthContext";
import { UserApi } from "../../api/user/UserApi";
import { UserDto } from "../../api/user/dto/UserDto";
import NotFoundError from "../../components/commons/NotFoundError/NotFoundError";
import { StatusHistoryDto } from "../../api/user/dto/StatusHistoryDto";

interface UserProfilePageProps {}

const UserProfilePage: React.FC<UserProfilePageProps> = () => {
  const { userId } = useAuth();
  const [user, setUser] = useState<UserDto | null>(null);

  const [userStatusHistory, setUserStatusHistory] = useState<
    StatusHistoryDto[] | null
  >(null);
  const [userPreferences, setUserPreferences] = useState(mockPreferences);
  const tgChatId = 2;
  const totalOrders = 20;
  const totalDiscounted = 1000;

  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  const fetchUser = useCallback(async () => {
    if (!userId) {
      setError("Пользователь не авторизован");
      setLoading(false);
      return;
    }

    try {
      const fetchedUser = await UserApi.getUserById(userId);
      setUser(fetchedUser);
    } catch (err) {
      console.error("Ошибка при загрузке пользователя:", err);
      setError("Не удалось загрузить данные пользователя.");
    } finally {
      setLoading(false);
    }
  }, [userId]);

  const fetchUserStatusHistory = useCallback(async () => {
    if (!userId) {
      setError("Пользователь не авторизован");
      setLoading(false);
      return;
    }

    try {
      const fetchedHistory = await UserApi.getUserStatusHistory(userId);
      setUserStatusHistory(fetchedHistory.items);
    } catch (err) {
      console.error("Ошибка при загрузке пользователя:", err);
      setError("Не удалось загрузить данные пользователя.");
    } finally {
      setLoading(false);
    }
  }, [userId]);

  useEffect(() => {
    fetchUser();
    fetchUserStatusHistory();
  }, [fetchUser, fetchUserStatusHistory]);

  const handleProductPreferenceRemove = (id: number) => {
    // TODO: послать запрос на API
    console.log("Убираем product preference");
    setUserPreferences((prev) => ({
      ...prev,
      productPreferences: prev.productPreferences.filter(
        (pref) => pref.productId !== id
      ),
    }));
  };

  const handleCategoryPreferenceRemove = (id: number) => {
    // TODO: послать запрос на API
    console.log("Убираем category preference");
    setUserPreferences((prev) => ({
      ...prev,
      categoryPreferences: prev.categoryPreferences.filter(
        (pref) => pref.categoryId !== id
      ),
    }));
  };

  if (loading) {
    return <div className="text-center py-12">Загрузка...</div>;
  }

  if (error || !user) {
    return (
      <NotFoundError
        title="Упс! Кажется, профиль пользователя не найден"
        message="Возможно, данного пользователя на существует"
      />
    );
  }

  return (
    <main className="container mx-auto px-4 py-8">
      <div className="flex flex-col md:flex-row gap-8">
        <div className="w-full md:w-1/3 lg:w-1/4">
          <UserProfileSidebar user={user} tgChatId={tgChatId} />
        </div>
        <div className="w-full md:w-2/3 lg:w-3/4">
          <UserProfileStats
            totalOrders={totalOrders}
            totalDiscounted={totalDiscounted}
          />
          <UserPreferences
            userPreferences={userPreferences}
            onProductPreferencesRemove={handleProductPreferenceRemove}
            onCategoryPreferencesRemove={handleCategoryPreferenceRemove}
          />
          {userStatusHistory && (
            <UserProfileHistory history={userStatusHistory} />
          )}
        </div>
      </div>
    </main>
  );
};

export default UserProfilePage;
