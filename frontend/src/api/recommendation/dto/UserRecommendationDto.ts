import { RecommendationItem } from "./RecommendationItem";

export interface UserRecommendationDto {
  userId: string; // UUID as a string
  items: RecommendationItem[]; // List of recommendation items
  message: string; // Optional message
  createdAt: string; // ISO date-time string
}
