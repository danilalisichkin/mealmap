import { DietType } from "../enums/DietType";

export interface DietCreationDto {
  type: DietType; // Enum for diet type
  goalWeight: number; // Must be positive
}
