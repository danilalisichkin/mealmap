import { ProductPreferenceDto } from "./ProductPreferenceDto";
import { CategoryPreferenceDto } from "./CategoryPreferenceDto";

export interface UserPreferencesDto {
    id: number;
    userId: string; // UUID as a string
    productPreferences: ProductPreferenceDto[];
    categoryPreferences: CategoryPreferenceDto[];
}