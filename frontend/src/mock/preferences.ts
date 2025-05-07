import { UserPreferencesDto } from "../api/preference/dto/UserPreferencesDto";
import { PreferenceType } from "../api/preference/enums/PreferenceType";
import { ProductDto } from "../api/product/dto/ProductDto";
import { CategorySimpleDto } from "../api/product/dto/CategorySimpleDto";

export const mockPreferences: UserPreferencesDto = {
  id: 1,
  userId: "0e3901b5-fb3f-44e4-b691-adac0a735789",
  productPreferences: [
    {
      id: 1,
      productId: 1,
      preferenceType: PreferenceType.LIKED,
    },
    {
      id: 2,
      productId: 2,
      preferenceType: PreferenceType.LIKED,
    },
    {
      id: 3,
      productId: 3,
      preferenceType: PreferenceType.DISLIKED,
    },
    {
      id: 4,
      productId: 4,
      preferenceType: PreferenceType.DISLIKED,
    },
  ],
  categoryPreferences: [
    {
      id: 1,
      categoryId: 1,
      preferenceType: PreferenceType.LIKED,
    },
    {
      id: 2,
      categoryId: 2,
      preferenceType: PreferenceType.LIKED,
    },
    {
      id: 3,
      categoryId: 3,
      preferenceType: PreferenceType.DISLIKED,
    },
  ],
};

export const mockPreferenceProducts: ProductDto[] = [
  {
    id: 1,
    name: "Жаренный редис",
    imageUrl: "products_1.webp",
    price: 300,
    weight: 60,
    nutrients: {
      calories: 200,
      proteins: 9,
      fats: 4,
      carbs: 22,
      fibers: 3,
      sugars: 5,
    },
    description: "Легкий куриный бульон.",
    isNew: true,
    supplierId: 3,
    categories: [
      {
        id: 3,
        name: "Супы",
        parent: undefined,
      },
    ],
  },
  {
    id: 2,
    name: "Гречка с грибами",
    imageUrl: "products_1.webp",
    price: 250,
    weight: 150,
    nutrients: {
      calories: 180,
      proteins: 5,
      fats: 3,
      carbs: 32,
      fibers: 4,
      sugars: 2,
    },
    description: "Гречневая каша с шампиньонами и луком.",
    isNew: false,
    supplierId: 1,
    categories: [
      {
        id: 1,
        name: "Гарниры",
        parent: undefined,
      },
    ],
  },
  {
    id: 3,
    name: "Салат Цезарь",
    imageUrl: "products_1.webp",
    price: 350,
    weight: 120,
    nutrients: {
      calories: 220,
      proteins: 12,
      fats: 10,
      carbs: 14,
      fibers: 2,
      sugars: 3,
    },
    description: "Классический салат с курицей и соусом Цезарь.",
    isNew: false,
    supplierId: 2,
    categories: [
      {
        id: 2,
        name: "Салаты",
        parent: undefined,
      },
    ],
  },
  {
    id: 4,
    name: "Плов с бараниной",
    imageUrl: "products_1.webp",
    price: 400,
    weight: 200,
    nutrients: {
      calories: 320,
      proteins: 15,
      fats: 18,
      carbs: 28,
      fibers: 3,
      sugars: 1,
    },
    description: "Традиционный плов с кусочками баранины.",
    isNew: true,
    supplierId: 4,
    categories: [
      {
        id: 1,
        name: "Гарниры",
        parent: undefined,
      },
    ],
  },
];

export const mockPreferenceCategories: CategorySimpleDto[] = [
  {
    id: 1,
    name: "Гарниры",
    parent: undefined,
  },
  {
    id: 2,
    name: "Салаты",
    parent: undefined,
  },
  {
    id: 3,
    name: "Супы",
    parent: undefined,
  },
];
