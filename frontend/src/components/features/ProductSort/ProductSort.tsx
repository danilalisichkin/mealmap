import React from "react";
import Sort from "../../commons/Sort/Sort";
import { ProductSortField } from "../../../api/product/enums/ProductSortField";

const SORT_FIELDS = [
  { value: ProductSortField.NAME, label: "Название" },
  { value: ProductSortField.PRICE, label: "Цена" },
  { value: ProductSortField.WEIGHT, label: "Вес" },
  { value: ProductSortField.CALORIES, label: "Калории" },
  { value: ProductSortField.PROTEINS, label: "Белки" },
  { value: ProductSortField.FATS, label: "Жиры" },
  { value: ProductSortField.CARBS, label: "Углеводы" },
  { value: ProductSortField.FIBERS, label: "Клетчатка" },
  { value: ProductSortField.SUGARS, label: "Сахар" },
  { value: ProductSortField.NEWNESS, label: "Новизна" },
];

interface ProductSortProps {
  sortField: ProductSortField;
  sortOrder: "asc" | "desc";
  onSortChange: (field: ProductSortField, order: "asc" | "desc") => void;
}

const ProductSort: React.FC<ProductSortProps> = ({
  sortField,
  sortOrder,
  onSortChange,
}) => {
  return (
    <Sort
      sortField={sortField}
      sortOrder={sortOrder}
      fields={SORT_FIELDS}
      onSortChange={onSortChange}
    />
  );
};

export default ProductSort;
