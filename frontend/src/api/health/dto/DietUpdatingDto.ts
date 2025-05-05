import { DietType } from "../enums/DietType";

export interface DietUpdatingDto {
  type: DietType; // Enum for diet type
  goalWeight: number; // Must be positive
}
