export interface PromoCodeDto {
  value: string; // Promo code value
  limit: number; // Maximum usage limit
  discountPercentage: number; // Discount percentage (1-100)
  startDate: string; // ISO date string
  endDate: string; // ISO date string
}
