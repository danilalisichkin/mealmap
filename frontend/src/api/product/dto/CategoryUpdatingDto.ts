export interface CategoryUpdatingDto {
    name: string; // Max length: 50
    parent?: number; // Optional parent category ID
}