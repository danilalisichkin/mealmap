export interface PromoCodeCreationDto {
    value: string; // Between 2 and 20 characters
    limit: number; // Must be positive
    discountPercentage: number; // Between 1 and 100
    startDate: string; // ISO date string, must be in the present or future
    endDate: string; // ISO date string, must be in the future
}