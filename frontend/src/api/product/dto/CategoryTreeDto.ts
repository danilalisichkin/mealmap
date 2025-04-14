export interface CategoryTreeDto {
  id: number;
  name: string;
  parent?: CategoryTreeDto; // Recursive parent category
}
