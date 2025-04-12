import { Gender } from "../enums/Gender";

export interface PhysicHealthDto {
    id: number;
    weight: number;
    height: number;
    birthDate: string; // ISO date string
    gender: Gender;    // Enum for gender
}