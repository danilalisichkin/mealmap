import { PreferenceType } from "../enums/PreferenceType";

export interface ProductPreferenceCreationDto {
  productId: number; // Must not be null
  preferenceType: PreferenceType; // Must not be null
}
