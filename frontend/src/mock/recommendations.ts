import { UserRecommendationDto } from "../api/recommendation/dto/UserRecommendationDto";

export const mockRecommendation: UserRecommendationDto = {
  userId: "0e3901b5-fb3f-44e4-b691-adac0a735789",
  items: [
    {
      productId: 41,
      quantity: 1,
    },
    {
      productId: 55,
      quantity: 1,
    }
  ],
  createdAt: "2023-10-01T12:00:00Z",
  message:
    "Исходя из Вашей текущей диеты, а также Ваших предпочтений, я принял решение посоветовать Вам именно эти блюда на сегодняшний обед. Надеюсь Вам понравится ;).",
};
