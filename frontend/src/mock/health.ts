import { DietDto } from "../api/health/dto/DietDto";
import { PhysicHealthDto } from "../api/health/dto/PhysicHealthDto";
import { PhysicHealthHistoryDto } from "../api/health/dto/PhysicHealthHistoryDto";
import { DietType } from "../api/health/enums/DietType";
import { Gender } from "../api/health/enums/Gender";

export const mockPhysicHealth: PhysicHealthDto = {
  id: 1,
  weight: 80000,
  height: 175,
  birthDate: "1991-06-15T00:00:00.000Z",
  gender: Gender.MALE,
};

export const mockDiet: DietDto = {
  id: 1,
  type: DietType.WEIGHT_LOSS,
  goalWeight: 70000,
  startDate: "2024-04-01T00:00:00.000Z",
};

export const mockPhysicHealthHistory: PhysicHealthHistoryDto[] = [
  {
    id: 1,
    weight: 75000,
    createdAt: "2024-03-01T08:00:00.000Z",
  },
  {
    id: 2,
    weight: 74000,
    createdAt: "2024-03-15T08:00:00.000Z",
  },
  {
    id: 3,
    weight: 73000,
    createdAt: "2024-03-29T08:00:00.000Z",
  },
  {
    id: 4,
    weight: 72500,
    createdAt: "2024-04-22T08:00:00.000Z",
  },
  {
    id: 5,
    weight: 75000,
    createdAt: "2024-05-21T08:00:00.000Z",
  },
  {
    id: 6,
    weight: 78000,
    createdAt: "2024-06-14T08:00:00.000Z",
  },
  {
    id: 7,
    weight: 80000,
    createdAt: "2024-07-22T08:00:00.000Z",
  },
];
