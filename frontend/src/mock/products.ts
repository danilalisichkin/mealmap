import { PageDto } from "../api/common/dto/PageDto";
import { ProductDto } from "../api/product/dto/ProductDto";

export const mockProducts: ProductDto[] = [
  {
    id: 41,
    name: "Куриный бульон",
    imageUrl: "products_1.webp",
    price: 100,
    weight: 200,
    nutrients: {
      calories: 180,
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
    allergens: [
      {
        id: 1,
        name: "Соя",
      },
    ],
  },
  {
    id: 55,
    name: "Салат с курицей и ананасом",
    imageUrl: "products_1.webp",
    price: 260,
    weight: 300,
    nutrients: {
      calories: 460,
      proteins: 23,
      fats: 19,
      carbs: 50,
      fibers: 17,
      sugars: 19,
    },
    description: "Салат с курицей и ананасом.",
    isNew: false,
    supplierId: 2,
    categories: [
      {
        id: 9,
        name: "Мясные блюда",
        parent: undefined,
      },
      {
        id: 11,
        name: "Салаты из овощей",
        parent: {
          id: 2,
          name: "Салаты",
        },
      },
      {
        id: 12,
        name: "Салаты с мясом",
        parent: {
          id: 2,
          name: "Салаты",
        },
      },
    ],
    allergens: [],
  },
];

export const mockProductPage: PageDto<ProductDto> = {
  currentPage: 2,
  pageSize: 5,
  totalPages: 10,
  totalElements: 50,
  items: mockProducts,
};
