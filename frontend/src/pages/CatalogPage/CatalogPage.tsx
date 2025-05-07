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
import { PreferenceApi } from "../../api/preference/UserPreferenceApi";
import { useAuth } from "../../contexts/AuthContext";
import { ProductPreferenceDto } from "../../api/preference/dto/ProductPreferenceDto";
import { CategoryApi } from "../../api/product/CategoryApi";
import { OrganizationApi } from "../../api/organization/OrganizationApi";

interface CatalogPageProps {}

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
  categories: "",
};

const DEFAULT_PAGINATION_OPTIONS = {
  PAGE_NUMBER: 1,
  PAGE_SIZE: 8,
};

const CatalogPage: React.FC<CatalogPageProps> = () => {
  const { userId } = useAuth();

  const [productPage, setProductPage] = useState<PageDto<ProductDto> | null>(
    null
  );

  const [categories, setCategories] = useState<
    { label: string; value: string }[]
  >([]);
  const [suppliers, setSuppliers] = useState<
    { label: string; value: string }[]
  >([]);
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

  const [productPreferences, setProductPreferences] = useState<
    ProductPreferenceDto[]
  >([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  const fetchProducts = useCallback(
    async (newFilter?: ProductFilter, newSearchText?: string) => {
      try {
        setLoading(true);

        const searchTextToUse = newSearchText ?? searchText;

        const filterToUse = newFilter || filter;

        const response = await ProductApi.getProducts(
          currentPage - 1,
          DEFAULT_PAGINATION_OPTIONS.PAGE_SIZE,
          sortField,
          sortOrder.toUpperCase() as "ASC" | "DESC",
          filterToUse,
          searchTextToUse
        );
        setProductPage(response);
      } catch (error) {
        console.error("Ошибка при загрузке продуктов:", error);
        setError("Не удалось загрузить продукты.");
      } finally {
        setLoading(false);
      }
    },
    [currentPage, sortField, sortOrder]
  );

  const fetchCategories = useCallback(async () => {
    try {
      const response = await CategoryApi.getCategories(0, 20);
      const formattedCategories = response.items.map((category) => ({
        label: category.name,
        value: category.id.toString(),
      }));
      setCategories(formattedCategories);
    } catch (error) {
      console.error("Ошибка при загрузке категорий:", error);
    }
  }, []);

  const fetchSuppliers = useCallback(async () => {
    try {
      const response = await OrganizationApi.getSuppliers();
      const formattedSuppliers = response.items.map((supplier) => ({
        label: supplier.name,
        value: supplier.id.toString(),
      }));
      setSuppliers(formattedSuppliers);
    } catch (error) {
      console.error("Ошибка при загрузке поставщиков:", error);
    }
  }, []);

  const fetchProductPreferences = async () => {
    if (!userId) return;

    try {
      setLoading(true);
      const response = await PreferenceApi.getProductPreferences(userId);
      setProductPreferences(response);
    } catch (error) {
      console.error("Ошибка при загрузке предпочтений продуктов:", error);
      setError("Не удалось загрузить предпочтения продуктов.");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchProducts(filter, searchText);
  }, [fetchProducts]);

  useEffect(() => {
    if (userId) {
      fetchProductPreferences();
    }
  }, [userId]);

  useEffect(() => {
    fetchCategories();
  }, [fetchCategories]);

  useEffect(() => {
    fetchSuppliers();
  }, [fetchSuppliers]);

  const handlePageChange = (page: number) => {
    setCurrentPage(page);
  };

  const toggleFilter = () => {
    setFilterOpened((prev) => !prev);
  };

  const handleFilterApply = () => {
    setFilterOpened(false);
    if (currentPage !== 1) {
      setCurrentPage(1);
    } else {
      fetchProducts(filter);
    }
  };

  const handleFilterChange = (updatedFilter: ProductFilter) => {
    setFilter(updatedFilter);
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
    if (currentPage !== 1) {
      setCurrentPage(1);
    } else {
      fetchProducts(filter, searchText);
    }
  };

  const handleCategorySelect = (selectedCategories: string[]) => {
    const serializedCategories =
      selectedCategories.includes("all") || selectedCategories.length === 0
        ? ""
        : selectedCategories?.join(",") || "";
    const updatedFilter = { ...filter, categories: serializedCategories };
    setFilter(updatedFilter);

    if (currentPage !== 1) {
      setCurrentPage(1);
    } else {
      fetchProducts(updatedFilter);
    }
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
        onApply={handleFilterApply}
      />
      <MultiSelect
        title="Категории"
        options={categories}
        onSelect={handleCategorySelect}
      />
      {(() => {
        let content;
        if (loading) {
          content = <p className="text-center py-12">Загрузка...</p>;
        } else if (error) {
          content = <p className="text-center py-12 text-red-500">{error}</p>;
        } else if (productPage) {
          content = (
            <>
              <Catalog
                products={productPage.items}
                preferredProducts={productPreferences}
              />
              <Pagination
                page={currentPage}
                pageSize={productPage.pageSize}
                totalPages={productPage.totalPages}
                totalElements={productPage.totalElements}
                onPageChange={handlePageChange}
              />
            </>
          );
        } else {
          content = (
            <p className="text-center py-12">Нет данных для отображения.</p>
          );
        }
        return content;
      })()}
      <RecommendationSection />
    </main>
  );
};

export default CatalogPage;
