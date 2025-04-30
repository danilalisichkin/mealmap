import React, { useState } from "react";
import "./UserHealthPage.css";
import PhysicHealthModal from "../../components/features/PhysicHealthModal/PhysicHealthModal";
import { differenceInCalendarDays } from "date-fns";
import {
  mockDiet,
  mockPhysicHealth,
  mockPhysicHealthHistory,
} from "../../mock/health";
import UserHealthOverviewTab from "../../components/features/UserHealthOverviewTab/UserHealthOverviewTab";
import UserHealthDietTab from "../../components/features/UserHealthDietTab/UserHealthDietTab";
import UserHealthPhysicalTab from "../../components/features/UserHealthPhysicalTab/UserHealthPhysicalTab";

interface UserHealthPageProps {}

const TABS = [
  { key: "overview", label: "Общие сведения" },
  { key: "diet", label: "Диета" },
  { key: "physical", label: "Физические показатели" },
];

const UserHealthPage: React.FC<UserHealthPageProps> = () => {
  // TODO: API CALL
  const physicHealth = mockPhysicHealth;
  const diet = mockDiet;
  const weightHistory = mockPhysicHealthHistory;

  const [isPhysicModalOpen, setPhysicModalOpen] = useState<boolean>(false);
  const [activeTab, setActiveTab] = useState<string>("overview");

  const formattedCurrentWeight = physicHealth.weight / 1000;
  const formattedPreviousWeight =
    weightHistory[weightHistory.length - 2].weight / 1000;
  const formattedWeightDifference =
    formattedCurrentWeight - formattedPreviousWeight;

  const formattedDietGoalWeight = diet.goalWeight / 1000;
  const formattedDietStartWeight = weightHistory[0].weight / 1000;
  const formattedRemainingWeight =
    formattedCurrentWeight - formattedDietGoalWeight;

  const bmi =
    formattedCurrentWeight && physicHealth.height
      ? (formattedCurrentWeight / (physicHealth.height / 100) ** 2).toFixed(1)
      : "-";

  let bmiLabel: string;
  if (Number(bmi) < 18.5) {
    bmiLabel = "Недостаточный вес";
  } else if (Number(bmi) < 25) {
    bmiLabel = "Нормальный вес";
  } else if (Number(bmi) < 30) {
    bmiLabel = "Избыточный вес";
  } else {
    bmiLabel = "Ожирение";
  }
 
  const age = Math.floor(
    differenceInCalendarDays(new Date(), new Date(physicHealth.birthDate)) / 365.25
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
            formattedCurrentWeight={formattedCurrentWeight}
            formattedWeightDifference={formattedWeightDifference}
            formattedDietGoalWeight={formattedDietGoalWeight}
            formattedDietStartWeight={formattedDietStartWeight}
            formattedRemainingWeight={formattedRemainingWeight}
            bmi={bmi}
            bmiLabel={bmiLabel}
            age={age}
          />
        )}

        {activeTab === "diet" && (
          <UserHealthDietTab
            diet={diet}
            formattedCurrentWeight={formattedCurrentWeight}
            formattedDietGoalWeight={formattedDietGoalWeight}
          />
        )}

        {activeTab === "physical" && (
          <UserHealthPhysicalTab
          physicHealth={physicHealth}
          formattedCurrentWeight={formattedCurrentWeight}
          age={age}
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
