export interface PromoCodeUpdatingDto {
  discountPercentage: number; // Between 1 and 100
  endDate: string; // ISO date string, must be in the future
  limit: number; // Must be zero or positive
}
