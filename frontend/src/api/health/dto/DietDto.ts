import { DietType } from "../enums/DietType";

export interface DietDto {
    id: number;
    dietType: DietType; // Enum for diet type
    goalWeight: number;
    startDate: string; // ISO date string
}