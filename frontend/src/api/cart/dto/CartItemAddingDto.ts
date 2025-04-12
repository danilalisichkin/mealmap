export interface CartItemAddingDto {
    productId: number; // Must not be null
    quantity: number;  // Must be positive and <= 20
}