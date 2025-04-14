export interface NutrientDto {
  calories: number; // Must not be null
  proteins: number; // Must be positive
  fats: number; // Must be positive
  carbs: number; // Must be positive
  fibers: number; // Must be positive
  sugars: number; // Must be positive
}
