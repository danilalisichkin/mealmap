import { NutrientDto } from "./NutrientDto";

export interface ProductCreatingDto {
  name: string; // Max length: 40
  imageUrl: string;
  price: number; // Must be positive
  weight: number; // Must be positive
  nutrients: NutrientDto; // Nutritional information
  description?: string; // Optional, max length: 255
  supplierId: number; // Must not be null
  categories: number[]; // List of category IDs
}
