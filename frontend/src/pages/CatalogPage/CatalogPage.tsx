import React, { useState } from "react";
import { ProductDto } from "../../api/product/dto/ProductDto";
import Search from "../../components/commons/Search/Search";
import FilterToggleButton from "../../components/commons/FilterToggleButton/FilterToggleButton";
import CatalogFilter from "../../components/features/CatalogFilter/CatalogFilter";
import MultiSelect from "../../components/commons/MultiSelect/MultiSelect";
import Catalog from "../../components/features/Catalog/Catalog";
import Pagination from "../../components/commons/Pagination/Pagination";
import { ProductFilter } from "../../api/product/dto/ProductFilter";
import { PageDto } from "../../api/common/dto/PageDto";
import { mockProductPage } from "../../mock/products";
import RecommendationSection from "../../components/features/RecommendationSection/RecommendationSection";
import { ProductSortField } from "../../api/product/enums/ProductSortField";
import ProductSort from "../../components/features/ProductSort/ProductSort";

interface CatalogPageProps {}

const categoryOptions = [
  { label: "Все", value: "all" },
  { label: "Завтраки", value: "breakfast" },
  { label: "Салаты", value: "salads" },
  { label: "Основные", value: "main" },
  { label: "Супы", value: "soups" },
];

const suppliers = [
  { label: "Все", value: "all" },
  { label: "McDonalds", value: "1" },
  { label: "BurgerKing", value: "2" },
  { label: "Тутака", value: "3" },
  { label: "ПиццаПанда", value: "4" },
];

const defaultFilter: ProductFilter = {
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
  categories: [],
};

const CatalogPage: React.FC<CatalogPageProps> = () => {
  const [pageData, setPageData] =
    useState<PageDto<ProductDto>>(mockProductPage);

  const handlePageChange = (page: number) => {
    console.log(`Переключение на страницу ${page}`);
    setPageData({ ...pageData, currentPage: page });
  };

  const [filter, setFilter] = useState<ProductFilter>(defaultFilter);
  const [isFilterOpened, setFilterOpened] = useState(false);

  const toggleFilter = () => {
    setFilterOpened((prev) => !prev);
  };

  const handleFilterChange = (updatedFilter: ProductFilter) => {
    console.log("New filter:", updatedFilter);
    setFilter(updatedFilter);
  };

  const [sortField, setSortField] = useState<ProductSortField>(
    ProductSortField.NAME
  );
  const [sortOrder, setSortOrder] = useState<"asc" | "desc">("asc");

  const handleSortChange = (field: ProductSortField, order: "asc" | "desc") => {
    setSortField(field);
    setSortOrder(order);

    console.log(`Сортировка: ${field}, порядок: ${order}`);
  };

  const [searchText, setSearchText] = useState("");

  const handleSearchChange = (text: string) => {
    setSearchText(text);
  };

  const handleSearchApply = () => {
    console.log("Поиск по тексту:", searchText);
  };

  return (
    <main className="container mx-auto px-4 py-4">
      <div className="flex flex-col md:flex-row justify-between items-start md:items-center mb-2 gap-4">
        <h1 className="text-3xl font-bold text-gray-800">Каталог</h1>
        <div className="w-full md:w-auto flex flex-col md:flex-row gap-3">
          <Search
            text={searchText}
            placeHolder="Поиск в каталоге..."
            onChange={handleSearchChange}
            onApply={() => handleSearchApply()}
          />
          <ProductSort
            sortField={sortField}
            sortOrder={sortOrder}
            onSortChange={(field: ProductSortField, order: "asc" | "desc") =>
              handleSortChange(field, order)
            }
          />
          <FilterToggleButton label="фильтры" onClick={toggleFilter} />
        </div>
      </div>
      <CatalogFilter
        filter={filter}
        suppliers={suppliers}
        isOpened={isFilterOpened}
        onFilterChange={handleFilterChange}
        onApply={() => setFilterOpened(false)}
      />
      <MultiSelect
        title="Категории"
        options={categoryOptions}
        onSelect={(selected) => console.log("Выбранные категории:", selected)}
      />
      <Catalog products={pageData.items} />
      <Pagination
        page={pageData.currentPage}
        pageSize={pageData.pageSize}
        totalPages={pageData.totalPages}
        totalElements={pageData.totalElements}
        onPageChange={handlePageChange}
      />
      <RecommendationSection />
    </main>
  );
};

export default CatalogPage;
