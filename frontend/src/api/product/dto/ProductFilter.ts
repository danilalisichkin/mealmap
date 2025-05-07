export interface ProductFilter {
  minPrice?: number; // Optional, must be >= 0
  maxPrice?: number; // Optional, must be >= 0
  minWeight?: number; // Optional, must be >= 0
  maxWeight?: number; // Optional, must be > 0
  minCalories?: number; // Optional
  maxCalories?: number; // Optional
  minProteins?: number; // Optional, must be >= 0
  maxProteins?: number; // Optional, must be > 0
  minFats?: number; // Optional, must be >= 0
  maxFats?: number; // Optional, must be > 0
  minCarbs?: number; // Optional, must be >= 0
  maxCarbs?: number; // Optional, must be > 0
  minFibers?: number; // Optional, must be >= 0
  maxFibers?: number; // Optional, must be > 0
  minSugars?: number; // Optional, must be >= 0
  maxSugars?: number; // Optional, must be > 0
  supplierId?: number; // Optional
  categories?: string; // Optional list of category IDs
}
