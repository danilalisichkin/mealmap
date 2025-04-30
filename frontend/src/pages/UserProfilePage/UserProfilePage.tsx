import React, { useState } from "react";
import UserProfileSidebar from "../../components/features/UserProfileSidebar/UserProfileSidebar";
import UserProfileStats from "../../components/features/UserProfileStats/UserProfileStats";
import UserProfileHistory from "../../components/features/UserProfileHistory/UserProfileHistory";
import UserPreferences from "../../components/features/UserPreferences/UserPreferences";
import { mockUser, mockUserStatusHistory } from "../../mock/user";
import { mockPreferences } from "../../mock/preferences";

interface UserProfilePageProps {}

const UserProfilePage: React.FC<UserProfilePageProps> = () => {
  // TODO: API CALL
  const user = mockUser;
  const tgChatId = 2;
  const [userPreferences, setUserPreferences] = useState(mockPreferences);
  const userStatusHistory = mockUserStatusHistory;
  const totalOrders = 20;
  const totalDiscounted = 1000;

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
          <UserProfileHistory history={userStatusHistory} />
        </div>
      </div>
    </main>
  );
};

export default UserProfilePage;
