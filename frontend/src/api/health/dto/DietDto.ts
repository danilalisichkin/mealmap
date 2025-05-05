import { DietType } from "../enums/DietType";

export interface DietDto {
  id: number;
  type: DietType; // Enum for diet type
  goalWeight: number;
  startDate: string; // ISO date string
}
