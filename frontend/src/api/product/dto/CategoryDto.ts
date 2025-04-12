import { CategoryShortInfo } from "./CategoryShortInfo";

export interface CategoryDto {
    id: number;
    name: string;
    parent?: CategoryShortInfo; // Optional parent category
    children: CategoryShortInfo[]; // List of child categories
}