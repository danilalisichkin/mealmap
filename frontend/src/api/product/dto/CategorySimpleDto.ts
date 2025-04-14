import { CategoryShortInfo } from "./CategoryShortInfo";

export interface CategorySimpleDto {
  id: number;
  name: string;
  parent?: CategoryShortInfo; // Optional parent category
}
