import { PreferenceType } from "../enums/PreferenceType";

export interface CategoryPreferenceDto {
    id: number;
    categoryId: number;
    preferenceType: PreferenceType;
}