import React, { useEffect, useState } from "react";
import { ProductDto } from "../../api/product/dto/ProductDto";
import { useLocation, useParams } from "react-router-dom";
import { ProductApi } from "../../api/product/ProductApi";
import "./ProductPage.css";
import { PreferenceType } from "../../api/preference/enums/PreferenceType";
import { PreferenceApi } from "../../api/preference/UserPreferenceApi";
import { useAuth } from "../../contexts/AuthContext";
import { CartApi } from "../../api/cart/UserCartApi";
import ErrorBanner from "../../components/commons/ErrorBanner/ErrorBanner";
import { ErrorDetail } from "../../api/common/dto/ErrorDetail";
import PopupNotification from "../../components/features/PopupNotification/PopupNotification";
import { FileApi } from "../../api/file/FileApi";
import LoadingSpinner from "../../components/commons/LoadingSpinner/LoadingSpinner";

interface ProductPageProps {}

const ProductPage: React.FC<ProductPageProps> = () => {
  const location = useLocation();
  const params = useParams();
  const { userId } = useAuth();
  const [product, setProduct] = useState<ProductDto | null>(
    location.state?.product ?? null
  );

  const [imageSrc, setImageSrc] = useState<string | null>(null);
  const [currentPreferenceType, setCurrentPreferenceType] =
    useState<PreferenceType | null>(null);

  const [quantity, setQuantity] = useState(1);

  const [loading, setLoading] = useState<boolean>(!product);
  const [error, setError] = useState<ErrorDetail | null>(null);

  const [notification, setNotification] = useState<{
    id: number;
    message: string;
    type: "success" | "error" | "info";
    isVisible: boolean;
  }>({
    id: 0,
    message: "",
    type: "success",
    isVisible: false,
  });

  const showNotification = (
    message: string,
    type: "success" | "error" | "info"
  ) => {
    setNotification({
      id: Date.now(),
      message,
      type,
      isVisible: true,
    });
  };

  const fetchProduct = async () => {
    if (!product && params.id) {
      try {
        setLoading(true);
        const fetchedProduct = await ProductApi.getProductById(
          Number(params.id)
        );
        setProduct(fetchedProduct);
      } catch (err: any) {
        if (err.response?.status === 404) {
          console.error("Ошибка при загрузке продукта:", err);
          setError({
            title: "Упс! Кажется, продукт не найден в каталоге",
            detail: err.response?.data.detail,
            status: "404",
          });
        } else {
          console.error("Ошибка при загрузке продукта:", err);
          setError({
            title: "Что-то пошло не так",
            detail: err.response?.data.detail,
            status: "500",
          });
        }
      } finally {
        setLoading(false);
      }
    }
  };

  const fetchImage = async () => {
    if (!product) return;

    try {
      const imageBlob = await FileApi.downloadFile(product.imageUrl);
      const imageUrl = URL.createObjectURL(imageBlob);
      setImageSrc(imageUrl);
    } catch (error) {
      console.error("Ошибка при загрузке изображения:", error);
    }
  };

  useEffect(() => {
    if (product?.imageUrl) {
      fetchImage();
    }
  }, [product?.imageUrl]);

  const fetchProductPreference = async () => {
    if (!userId || !params.id) return;

    try {
      setLoading(true);
      const fetchedPreference = await PreferenceApi.getProductPreference(
        userId,
        Number(params.id)
      );
      setCurrentPreferenceType(fetchedPreference.preferenceType);
    } catch (err: any) {
      if (err.response?.status === 404) {
        setCurrentPreferenceType(null);
      } else {
        console.error("Ошибка при загрузке предпочтения продукта:", err);
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

  const handleAddToCart = async (productId: number) => {
    if (!userId) {
      console.error("Пользователь не авторизован");
      return;
    }

    try {
      await CartApi.addItemToCart(userId, { productId, quantity });
      showNotification("Товар успешно добавлен в корзину!", "success");
      console.log(`Товар с ID ${productId} добавлен в корзину`);
    } catch (error) {
      showNotification("Ошибка при добавлении товара в корзину", "error");
      console.error("Ошибка при добавлении товара в корзину:", error);
    }
  };

  const handleAddToPreference = async (
    productId: number,
    preferenceType: PreferenceType
  ) => {
    if (!userId) {
      console.error("Пользователь не авторизован");
      showNotification(
        "Для выбора предпочтений необходимо войти в систему",
        "info"
      );
      return;
    }

    try {
      await PreferenceApi.addProductPreference(userId, {
        productId,
        preferenceType,
      });
      setCurrentPreferenceType(preferenceType);
      showNotification("Блюдо добавлено в предпочтения!", "success");
      console.log(`Товар с ID ${productId} добавлен в предпочтения`);
    } catch (error) {
      console.error("Ошибка при добавлении товара в предпочтения:", error);
    }
  };

  const handleRemoveFromPreference = async (productId: number) => {
    if (!userId) {
      console.error("Пользователь не авторизован");
      showNotification(
        "Для выбора предпочтений необходимо войти в систему",
        "info"
      );
      return;
    }

    try {
      await PreferenceApi.removeProductPreference(userId, productId);
      setCurrentPreferenceType(null);
      showNotification("Блюдо убрано из предпочтений!", "success");
      console.log(`Товар с ID ${productId} убран из предпочтений`);
    } catch (error) {
      console.error("Ошибка при удалении товара из предпочтений:", error);
    }
  };

  const addToPreference = (type: PreferenceType) => {
    if (currentPreferenceType === type) {
      handleRemoveFromPreference(product!.id);
      if (userId !== null) {
        setCurrentPreferenceType(null);
      }
    } else {
      if (userId !== null) {
        setCurrentPreferenceType(type);
      }
      handleAddToPreference(product!.id, type);
    }
  };

  useEffect(() => {
    fetchProduct();
  }, [params.id]);

  useEffect(() => {
    if (userId) {
      fetchProductPreference();
    }
  }, [userId, params.id]);

  if (loading) {
    return <div className="text-center py-12">Загрузка...</div>;
  }

  if (error) {
    return <ErrorBanner error={error} />;
  }

  if (!product) {
    return (
      <ErrorBanner
        error={{
          title: "Упс! Кажется, продукт не найден в каталоге",
          detail: "Продукт не найден",
          status: "404",
        }}
      />
    );
  }

  return (
    <main className="container mx-auto px-4 py-8 max-w-6xl">
      {/* Product Card */}
      <div className="flex flex-col md:flex-row bg-white rounded-xl shadow-md">
        {/* Image Container */}
        <div className="md:w-1/2 overflow-hidden">
          {imageSrc ? (
            <div className="relative">
              <img
                src={imageSrc}
                alt={product.name}
                className="product-image"
              />
              {product.isNew && (
                <div className="absolute top-4 right-4 flex space-x-2">
                  <span className="new-badge bg-orange-500 text-white text-xs px-3 py-1 rounded-full font-medium">
                    новинка
                  </span>
                </div>
              )}

              {/* Preference Choice */}
              <div className="absolute scale-150 bottom-4 right-4 flex space-x-2">
                {currentPreferenceType !== PreferenceType.DISLIKED && (
                  <button
                    className={`like-btn bg-white p-1 rounded-full shadow-md transition z-5 ${
                      currentPreferenceType === PreferenceType.LIKED
                        ? "active"
                        : ""
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
                    className={`dislike-btn bg-white p-1 rounded-full shadow-md transition z-5 ${
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
            </div>
          ) : (
            <LoadingSpinner />
          )}
          {/* Product Image */}
        </div>

        {/* Product Info */}
        <div className="md:w-1/2 p-6 product">
          {/* Product Header */}
          <div className="flex justify-between items-start mb-4">
            {/* Product Name & Categories */}
            <div>
              <h1 className="text-2xl font-bold text-gray-800 mb-1">
                {product.name}
              </h1>
            </div>
          </div>

          {/* Categories */}
          <div className="flex flex-wrap gap-1 mb-2">
            {product.categories.map((category) => (
              <span
                key={category.id}
                className="bg-gray-100 text-gray-600 text-xs px-2 py-1 rounded"
              >
                {category.name}
              </span>
            ))}
          </div>

          {/* Description */}
          <div className="mb-6">
            <h3 className="text-lg font-semibold text-gray-800 mb-2">
              Описание
            </h3>
            <p className="text-gray-600 text-sm">{product.description}</p>
          </div>

          {/* Nutrients */}
          <div className="mb-6">
            <h3 className="text-lg font-semibold text-gray-800 mb-3">
              Пищевая ценность
            </h3>

            <div className="grid grid-cols-2 gap-4 mb-4">
              <div className="bg-gray-50 p-3 rounded-lg">
                <div className="flex justify-between items-center mb-1">
                  <span className="text-gray-600 text-sm">Вес</span>
                  <span className="font-medium">{product.weight} г</span>
                </div>
              </div>
              <div className="bg-gray-50 p-3 rounded-lg">
                <div className="flex justify-between items-center mb-1">
                  <span className="text-gray-600 text-sm">Калории</span>
                  <span className="font-medium">
                    {product.nutrients.calories} ккал
                  </span>
                </div>
              </div>
            </div>

            <div className="space-y-2">
              <div>
                <div className="flex justify-between text-sm mb-1">
                  <span className="text-gray-600">Белки</span>
                  <span className="font-medium">
                    {product.nutrients.proteins} г
                  </span>
                </div>
                <div className="w-full bg-gray-200 rounded-full h-1.5">
                  <div className="bg-blue-500 nutrient-bar"></div>
                </div>
              </div>
              <div>
                <div className="flex justify-between text-sm mb-1">
                  <span className="text-gray-600">Жиры</span>
                  <span className="font-medium">
                    {product.nutrients.fats} г
                  </span>
                </div>
                <div className="w-full bg-gray-200 rounded-full h-1.5">
                  <div className="bg-yellow-500 nutrient-bar"></div>
                </div>
              </div>
              <div>
                <div className="flex justify-between text-sm mb-1">
                  <span className="text-gray-600">Углеводы</span>
                  <span className="font-medium">
                    {product.nutrients.carbs} г
                  </span>
                </div>
                <div className="w-full bg-gray-200 rounded-full h-1.5">
                  <div className="bg-red-500 nutrient-bar"></div>
                </div>
              </div>
              <div>
                <div className="flex justify-between text-sm mb-1">
                  <span className="text-gray-600">Клетчатка</span>
                  <span className="font-medium">
                    {product.nutrients.fibers} г
                  </span>
                </div>
                <div className="w-full bg-gray-200 rounded-full h-1.5">
                  <div className="bg-green-500 nutrient-bar"></div>
                </div>
              </div>
            </div>
          </div>

          {/* Price & Quantity */}
          <div className="flex items-center justify-between space-x-2 mb-6">
            <span className="text-2xl font-bold text-green-600">
              {(product.price / 100).toFixed(2)}₽
            </span>
            <div className="flex items-center space-x-2">
              <button
                className="bg-gray-200 hover:bg-gray-300 text-gray-800 w-8 h-8 rounded-full flex items-center justify-center"
                onClick={() => setQuantity((prev) => Math.max(prev - 1, 1))}
              >
                <i className="fas fa-minus"></i>
              </button>
              <span className="w-8 text-center">{quantity}</span>
              <button
                className="bg-gray-200 hover:bg-gray-300 text-gray-800 w-8 h-8 rounded-full flex items-center justify-center"
                onClick={() => setQuantity((prev) => prev + 1)}
              >
                <i className="fas fa-plus"></i>
              </button>
            </div>
          </div>

          {/* Add to Cart Button */}
          <button
            className="w-full bg-green-500 hover:bg-green-600 text-white py-3 rounded-lg font-bold transition flex items-center justify-center text-lg"
            onClick={() => handleAddToCart(product.id)}
          >
            <i className="fas fa-plus mr-2"></i> Добавить в корзину
          </button>
        </div>
      </div>
      <PopupNotification
        key={notification.id}
        message={notification.message}
        type={notification.type}
        isVisible={notification.isVisible}
      />
    </main>
  );
};

export default ProductPage;
