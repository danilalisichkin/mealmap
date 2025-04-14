import { Gender } from "../enums/Gender";

export interface PhysicHealthCreationDto {
  weight: number; // Must be positive
  height: number; // Must be positive
  birthDate: string; // ISO date string, must be in the past
  gender: Gender; // Enum for gender
}
