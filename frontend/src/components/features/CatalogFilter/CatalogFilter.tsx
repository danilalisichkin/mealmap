import React, { useCallback } from "react";
import { ProductFilter } from "../../../api/product/dto/ProductFilter";
import RangeInput from "../../commons/RangeInput/RangeInput";
import "./CatalogFilter.css";

const emptyFilter: ProductFilter = {
  minPrice: undefined,
  maxPrice: undefined,
  minWeight: undefined,
  maxWeight: undefined,
  minCalories: undefined,
  maxCalories: undefined,
  minProteins: undefined,
  maxProteins: undefined,
  minFats: undefined,
  maxFats: undefined,
  minCarbs: undefined,
  maxCarbs: undefined,
  minFibers: undefined,
  maxFibers: undefined,
  minSugars: undefined,
  maxSugars: undefined,
  supplierId: undefined,
  categories: "",
};

interface CatalogFilterProps {
  filter: ProductFilter;
  suppliers: Option[];
  isOpened: boolean;
  onFilterChange: (updatedFilter: ProductFilter) => void;
  onApply: () => void;
}

interface Option {
  label: string;
  value: string;
}

const CatalogFilter: React.FC<CatalogFilterProps> = ({
  filter,
  suppliers,
  isOpened,
  onFilterChange,
  onApply,
}) => {
  const handleFilterUpdate = useCallback(
    (updates: Partial<ProductFilter>) => {
      const updatedFilter = { ...filter, ...updates };
      onFilterChange(updatedFilter);
    },
    [filter, onFilterChange]
  );

  const handleRangeChange = useCallback(
    (fieldMin: keyof ProductFilter, fieldMax: keyof ProductFilter) =>
      (min: number | undefined, max: number | undefined) => {
        if (min !== undefined) {
          min = Math.max(0, min);
        }
        if (min !== undefined && max !== undefined) {
          max = Math.max(min, max);
        }
        handleFilterUpdate({ [fieldMin]: min, [fieldMax]: max });
      },
    [handleFilterUpdate]
  );

  const handlePriceRangeChange = useCallback(
    (minRubles: number | undefined, maxRubles: number | undefined) => {
      const minCents =
        minRubles !== undefined ? Math.round(minRubles * 100) : undefined;
      const maxCents =
        maxRubles !== undefined ? Math.round(maxRubles * 100) : undefined;
      handleFilterUpdate({ minPrice: minCents, maxPrice: maxCents });
    },
    [handleFilterUpdate]
  );

  const handleFilterReset = useCallback(() => {
    onFilterChange(emptyFilter);
  }, [onFilterChange]);

  return (
    <div
      id="filter-panel"
      className={`filter-panel bg-white rounded-lg shadow-sm mb-6 ${
        isOpened ? "open" : "overflow-hidden"
      }`}
    >
      <div className="p-4 grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
        <RangeInput
          label="Цена (₽)"
          minValue={
            filter.minPrice !== undefined ? filter.minPrice / 100 : undefined
          }
          maxValue={
            filter.maxPrice !== undefined ? filter.maxPrice / 100 : undefined
          }
          step={0.01}
          onChange={handlePriceRangeChange}
        />

        <RangeInput
          label="Вес (г)"
          minValue={filter.minWeight}
          maxValue={filter.maxWeight}
          onChange={handleRangeChange("minWeight", "maxWeight")}
        />

        <RangeInput
          label="Калории (ккал)"
          minValue={filter.minCalories}
          maxValue={filter.maxCalories}
          onChange={handleRangeChange("minCalories", "maxCalories")}
        />

        <RangeInput
          label="Белки (г)"
          minValue={filter.minProteins}
          maxValue={filter.maxProteins}
          onChange={handleRangeChange("minProteins", "maxProteins")}
        />

        <RangeInput
          label="Жиры (г)"
          minValue={filter.minFats}
          maxValue={filter.maxFats}
          onChange={handleRangeChange("minFats", "maxFats")}
        />

        <RangeInput
          label="Углеводы (г)"
          minValue={filter.minCarbs}
          maxValue={filter.maxCarbs}
          onChange={handleRangeChange("minCarbs", "maxCarbs")}
        />

        <RangeInput
          label="Клетчатка (г)"
          minValue={filter.minFibers}
          maxValue={filter.maxFibers}
          onChange={handleRangeChange("minFibers", "maxFibers")}
        />

        <RangeInput
          label="Сахар (г)"
          minValue={filter.minSugars}
          maxValue={filter.maxSugars}
          onChange={handleRangeChange("minSugars", "maxSugars")}
        />

        <div className="space-y-2">
          <label
            htmlFor="supplier"
            className="block text-sm font-medium text-gray-700"
          >
            Поставщик
          </label>
          <select
            id="supplier"
            value={filter.supplierId}
            className="w-full px-2 py-1 border border-gray-300 rounded-md text-sm"
            onChange={(e) =>
              handleFilterUpdate({
                supplierId: Number(e.target.value) || undefined,
              })
            }
          >
            {suppliers.map((supplier) => (
              <option key={supplier.value} value={supplier.value}>
                {supplier.label}
              </option>
            ))}
          </select>
        </div>
      </div>

      <div className="bg-gray-50 px-4 py-3 flex justify-between border-t">
        <button
          id="reset-filters"
          className="text-gray-600 hover:text-gray-800 text-sm"
          onClick={handleFilterReset}
        >
          <i className="fas fa-redo mr-1">Сбросить</i>
        </button>
        <button
          id="apply-filters"
          className="bg-green-600 text-white px-4 py-1 rounded-md text-sm hover:bg-green-700"
          onClick={onApply}
        >
          Применить
        </button>
      </div>
    </div>
  );
};

export default CatalogFilter;
