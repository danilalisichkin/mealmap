import { PreferenceType } from "../enums/PreferenceType";

export interface ProductPreferenceDto {
  id: number;
  productId: number;
  preferenceType: PreferenceType;
}
