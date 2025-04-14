import { DietType } from "../enums/DietType";

export interface DietCreationDto {
  dietType: DietType; // Enum for diet type
  goalWeight: number; // Must be positive
}
