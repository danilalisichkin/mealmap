import React, { useCallback, useEffect, useState } from "react";
import { ProductDto } from "../../api/product/dto/ProductDto";
import Search from "../../components/commons/Search/Search";
import FilterToggleButton from "../../components/commons/FilterToggleButton/FilterToggleButton";
import CatalogFilter from "../../components/features/CatalogFilter/CatalogFilter";
import MultiSelect from "../../components/commons/MultiSelect/MultiSelect";
import Catalog from "../../components/features/Catalog/Catalog";
import Pagination from "../../components/commons/Pagination/Pagination";
import { ProductFilter } from "../../api/product/dto/ProductFilter";
import { PageDto } from "../../api/common/dto/PageDto";
import RecommendationSection from "../../components/features/RecommendationSection/RecommendationSection";
import { ProductSortField } from "../../api/product/enums/ProductSortField";
import ProductSort from "../../components/features/ProductSort/ProductSort";
import { ProductApi } from "../../api/product/ProductApi";

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

const DEFAULT_FILTER: ProductFilter = {
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

const DEFAULT_PAGINATION_OPTIONS = {
  PAGE_NUMBER: 1,
  PAGE_SIZE: 8,
};

const CatalogPage: React.FC<CatalogPageProps> = () => {
  const [productPage, setProductPage] = useState<PageDto<ProductDto> | null>(null);
  const [filter, setFilter] = useState<ProductFilter>(DEFAULT_FILTER);
  const [isFilterOpened, setFilterOpened] = useState(false);
  const [sortField, setSortField] = useState<ProductSortField>(
    ProductSortField.ID
  );
  const [sortOrder, setSortOrder] = useState<"asc" | "desc">("asc");
  const [searchText, setSearchText] = useState("");
  const [currentPage, setCurrentPage] = useState(
    DEFAULT_PAGINATION_OPTIONS.PAGE_NUMBER
  );

  //TODO: прикрутить категории из MultiSelect в запрос
  const fetchProducts = useCallback(async () => {
    try {
      const response = await ProductApi.getProducts(
        (currentPage - 1),
        DEFAULT_PAGINATION_OPTIONS.PAGE_SIZE,
        sortField,
        sortOrder.toUpperCase() as "ASC" | "DESC",
        filter,
        searchText
      );
      setProductPage(response);
    } catch (error) {
      console.error("Ошибка при загрузке продуктов:", error);
    }
  }, [currentPage, sortField, sortOrder, filter, searchText]);

  useEffect(() => {
    fetchProducts();
  }, [fetchProducts]);

  const handlePageChange = (page: number) => {
    setCurrentPage(page);
  };

  const toggleFilter = () => {
    setFilterOpened((prev) => !prev);
  };

  const handleFilterChange = (updatedFilter: ProductFilter) => {
    setFilter(updatedFilter);
    setCurrentPage(1);
  };

  const handleSortChange = (field: ProductSortField, order: "asc" | "desc") => {
    setSortField(field);
    setSortOrder(order);
    setCurrentPage(1);
  };

  const handleSearchChange = (text: string) => {
    setSearchText(text);
  };

  const handleSearchApply = () => {
    setCurrentPage(1);
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
            onApply={handleSearchApply}
          />
          <ProductSort
            sortField={sortField}
            sortOrder={sortOrder}
            onSortChange={handleSortChange}
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
      {productPage ? (
        <>
          <Catalog products={productPage.items} />
          <Pagination
            page={currentPage}
            pageSize={productPage.pageSize}
            totalPages={productPage.totalPages}
            totalElements={productPage.totalElements}
            onPageChange={handlePageChange}
          />
        </>
      ) : (
        <p>Загрузка...</p>
      )}
      <RecommendationSection />
    </main>
  );
};

export default CatalogPage;
