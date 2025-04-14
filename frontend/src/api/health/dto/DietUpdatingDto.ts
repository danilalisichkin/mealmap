import { DietType } from "../enums/DietType";

export interface DietUpdatingDto {
  dietType: DietType; // Enum for diet type
  goalWeight: number; // Must be positive
}
