export interface OrderItemCreationDto {
    productId: number; // Must not be null
    quantity: number;  // Must be between 1 and MAX_PRODUCTS_PER_ITEM
}