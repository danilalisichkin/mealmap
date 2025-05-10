import React, { useCallback, useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";
import { CategoryApi } from "../../api/product/CategoryApi";
import { PreferenceApi } from "../../api/preference/UserPreferenceApi";
import { useAuth } from "../../contexts/AuthContext";
import LoadingSpinner from "../../components/commons/LoadingSpinner/LoadingSpinner";
import ErrorBanner from "../../components/commons/ErrorBanner/ErrorBanner";
import { ErrorDetail } from "../../api/common/dto/ErrorDetail";
import { PreferenceType } from "../../api/preference/enums/PreferenceType";
import { CategoryDto } from "../../api/product/dto/CategoryDto";
import PopupNotification, {
  NotificationType,
} from "../../components/features/PopupNotification/PopupNotification";
import "./CategoryPage.css";
import { PageDto } from "../../api/common/dto/PageDto";
import { ProductDto } from "../../api/product/dto/ProductDto";
import { ProductFilter } from "../../api/product/dto/ProductFilter";
import { ProductSortField } from "../../api/product/enums/ProductSortField";
import { ProductApi } from "../../api/product/ProductApi";
import { OrganizationApi } from "../../api/organization/OrganizationApi";
import { ProductPreferenceDto } from "../../api/preference/dto/ProductPreferenceDto";
import Search from "../../components/commons/Search/Search";
import ProductSort from "../../components/features/ProductSort/ProductSort";
import FilterToggleButton from "../../components/commons/FilterToggleButton/FilterToggleButton";
import CatalogFilter from "../../components/features/CatalogFilter/CatalogFilter";
import Catalog from "../../components/features/Catalog/Catalog";
import CatalogPlaceholder from "../../components/commons/Placeholders/CatalogPlaceholder/CatalogPlaceholder";
import Pagination from "../../components/commons/Pagination/Pagination";

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

const CategoryPage: React.FC = () => {
  const { categoryId } = useParams<{ categoryId: string }>();
  const { userId } = useAuth();

  const [category, setCategory] = useState<CategoryDto | null>(null);
  const [currentPreferenceType, setCurrentPreferenceType] =
    useState<PreferenceType | null>(null);

  const [productPage, setProductPage] = useState<PageDto<ProductDto> | null>(
    null
  );
  const [productPreferences, setProductPreferences] = useState<
    ProductPreferenceDto[]
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

  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<ErrorDetail | null>(null);

  const [notification, setNotification] = useState<{
    id: number;
    message: string;
    type: NotificationType;
    isVisible: boolean;
  }>({
    id: 0,
    message: "",
    type: NotificationType.SUCCESS,
    isVisible: false,
  });

  const showNotification = (message: string, type: NotificationType) => {
    setNotification({
      id: Date.now(),
      message,
      type,
      isVisible: true,
    });
  };

  const fetchCategory = async () => {
    try {
      setLoading(true);
      const fetchedCategory = await CategoryApi.getCategoryById(
        Number(categoryId)
      );
      setCategory(fetchedCategory);
    } catch (err: any) {
      if (err.response?.status === 404) {
        console.error("Ошибка при загрузке категории:", err);
        setError({
          title: "Упс! Кажется, категория не найдена",
          detail: err.response?.data.detail,
          status: "404",
        });
      } else {
        console.error("Ошибка при загрузке категории:", err);
        setError({
          title: "Что-то пошло не так",
          detail: err.response?.data.detail,
          status: "500",
        });
      }
    } finally {
      setLoading(false);
    }
  };

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
      } catch (err: any) {
        console.error("Ошибка при загрузке каталога:", err);
        setError({
          title: "Не получилось загрузить каталог",
          detail: err.response?.data.detail,
          status: "500",
        });
      } finally {
        setLoading(false);
      }
    },
    [currentPage, sortField, sortOrder]
  );

  const fetchPreference = async () => {
    if (!userId || !categoryId) return;

    try {
      const fetchedPreference = await PreferenceApi.getCategoryPreference(
        userId,
        Number(categoryId)
      );
      setCurrentPreferenceType(fetchedPreference.preferenceType);
    } catch (err) {
      console.error("Ошибка при загрузке предпочтений категории:", err);
    }
  };

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
    } catch (err: any) {
      console.error("Ошибка при загрузке предпочтений:", err);
      setError({
        title: "Не получилось загрузить предпочтения",
        detail: err.response?.data.detail,
        status: "500",
      });
    } finally {
      setLoading(false);
    }
  };

  const handleAddToPreference = async (
    categoryId: number,
    preferenceType: PreferenceType
  ) => {
    if (!userId) {
      console.error("Пользователь не авторизован");
      showNotification(
        "Для выбора предпочтений необходимо войти в систему",
        NotificationType.INFO
      );
      return;
    }

    try {
      await PreferenceApi.addCategoryPreference(userId, {
        categoryId,
        preferenceType,
      });
      setCurrentPreferenceType(preferenceType);
      showNotification(
        "Категория добавлена в предпочтения!",
        NotificationType.SUCCESS
      );
      console.log(`Категория с ID ${categoryId} добавлена в предпочтения`);
    } catch (error) {
      console.error("Ошибка при добавлении категории в предпочтения:", error);
    }
  };

  const handleRemoveFromPreference = async (categoryId: number) => {
    if (!userId) {
      console.error("Пользователь не авторизован");
      showNotification(
        "Для выбора предпочтений необходимо войти в систему",
        NotificationType.INFO
      );
      return;
    }

    try {
      await PreferenceApi.removeCategoryPreference(userId, categoryId);
      setCurrentPreferenceType(null);
      showNotification(
        "Категория убрана из предпочтений!",
        NotificationType.SUCCESS
      );
      console.log(`Категория с ID ${categoryId} убрана из предпочтений`);
    } catch (error) {
      console.error("Ошибка при удалении категории из предпочтений:", error);
    }
  };

  useEffect(() => {
    if (categoryId) {
      fetchCategory();
      const updatedFilter = { ...DEFAULT_FILTER, categories: categoryId };
      setFilter(updatedFilter);
      fetchProducts(updatedFilter, searchText);
    }
  }, [categoryId]);

  useEffect(() => {
    if (userId) {
      fetchPreference();
    }
  }, [userId, categoryId]);

  useEffect(() => {
    fetchProducts(filter, searchText);
  }, [fetchProducts]);

  useEffect(() => {
    if (userId) {
      fetchProductPreferences();
    }
  }, [userId]);

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

  const addToPreference = (type: PreferenceType) => {
    if (currentPreferenceType === type) {
      handleRemoveFromPreference(category!.id);
      if (userId !== null) {
        setCurrentPreferenceType(null);
      }
    } else {
      if (userId !== null) {
        setCurrentPreferenceType(type);
      }
      handleAddToPreference(category!.id, type);
    }
  };

  if (!category) {
    return (
      <ErrorBanner
        error={{
          title: "Упс! Кажется, категория не найдена",
          detail: "Категория не найдена",
          status: "404",
        }}
      />
    );
  }

  return (
    <main className="container mx-auto px-4 py-8 min-h-screen max-w-6xl">
      {/* Category Hierarchy */}
      <div className="mb-2">
        <div className="flex items-center space-x-2 text-sm">
          {category.parent && (
            <Link
              to={`/catalog/categories/${category.parent.id}`}
              className="text-green-500 hover:underline"
            >
              {category.parent.name}
            </Link>
          )}
          <span>/</span>
          <span className="text-gray-700">{category.name}</span>
        </div>
      </div>

      <div className="w-full flex flex-col md:flex-row md:items-center md:justify-between gap-3 mb-4">
        {/* Category Name + Preference Choice */}
        <div className="flex items-center space-x-2 flex-shrink-0">
          {/* Preference Choice */}
          <div className="flex items-center space-x-2">
            {currentPreferenceType !== PreferenceType.DISLIKED && (
              <button
                className={`like-btn bg-white p-1 rounded-full shadow-md transition ${
                  currentPreferenceType === PreferenceType.LIKED ? "active" : ""
                }`}
                onClick={(e) => {
                  e.preventDefault();
                  e.stopPropagation();
                  addToPreference(PreferenceType.LIKED);
                }}
              >
                <i className="far fa-thumbs-up"></i>
              </button>
            )}
            {currentPreferenceType !== PreferenceType.LIKED && (
              <button
                className={`dislike-btn bg-white p-1 rounded-full shadow-md transition ${
                  currentPreferenceType === PreferenceType.DISLIKED
                    ? "active"
                    : ""
                }`}
                onClick={(e) => {
                  e.preventDefault();
                  e.stopPropagation();
                  addToPreference(PreferenceType.DISLIKED);
                }}
              >
                <i className="far fa-thumbs-down"></i>
              </button>
            )}
          </div>
          {/* Category Name */}
          <h1 className="text-3xl font-bold">{category.name}</h1>
        </div>

        {/* Search & Sort */}
        <div className="flex flex-col space-y-2 md:flex-row md:space-y-0 md:space-x-2 w-full md:w-auto">
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

      {/* Subcategories */}
      {category.children && category.children.length > 0 && (
        <div>
          <h2 className="text-lg font-semibold mb-2">Подкатегории</h2>
          <div className="flex flex-wrap gap-2 mb-2">
            {category.children.map((child) => (
              <Link
                key={child.id}
                to={`/catalog/categories/${child.id}`}
                className="bg-gray-100 text-gray-600 text-sm px-3 py-1 rounded hover:bg-green-100 hover:text-green-600 transition"
              >
                {child.name}
              </Link>
            ))}
          </div>
        </div>
      )}

      {/* Filter (hidden) */}
      <CatalogFilter
        filter={filter}
        suppliers={suppliers}
        isOpened={isFilterOpened}
        onFilterChange={handleFilterChange}
        onApply={handleFilterApply}
      />

      {(() => {
        let content;
        if (error) {
          content = <ErrorBanner error={error} />;
        } else if (loading) {
          content = (
            <CatalogPlaceholder
              numberOfElements={DEFAULT_PAGINATION_OPTIONS.PAGE_SIZE}
            />
          );
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
        }
        return content;
      })()}

      <PopupNotification
        key={notification.id}
        message={notification.message}
        type={notification.type}
        isVisible={notification.isVisible}
      />
    </main>
  );
};

export default CategoryPage;
