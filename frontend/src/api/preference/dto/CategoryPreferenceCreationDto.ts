import { PreferenceType } from "../enums/PreferenceType";

export interface CategoryPreferenceCreationDto {
    categoryId: number; // Must not be null
    preferenceType: PreferenceType; // Must not be null
}