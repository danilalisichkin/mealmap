import { NutrientDto } from "./NutrientDto";
import { CategorySimpleDto } from "./CategorySimpleDto";
import { AllergenDto } from "./AllergenDto";

export interface ProductDto {
  id: number;
  name: string;
  imageUrl: string;
  price: number;
  weight: number;
  nutrients: NutrientDto; // Nutritional information
  description?: string; // Optional
  isNew: boolean; // Indicates if the product is new
  supplierId: number;
  categories: CategorySimpleDto[]; // List of associated categories
  allergens: AllergenDto[]; // List of associated allergens
}
