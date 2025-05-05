import React, { useEffect, useState } from "react";
import { ProductDto } from "../../api/product/dto/ProductDto";
import { useLocation, useParams } from "react-router-dom";
import { ProductApi } from "../../api/product/ProductApi";
import NotFoundError from "../../components/commons/NotFoundError/NotFoundError";
import "./ProductPage.css";
import { PreferenceType } from "../../api/preference/enums/PreferenceType";
import { PreferenceApi } from "../../api/preference/UserPreferenceApi";
import { useAuth } from "../../contexts/AuthContext";
import { CartApi } from "../../api/cart/UserCartApi";

interface ProductPageProps {}

const ProductPage: React.FC<ProductPageProps> = () => {
  const location = useLocation();
  const params = useParams();
  const { userId } = useAuth();
  const [product, setProduct] = useState<ProductDto | null>(
    location.state?.product ?? null
  );

  const [currentPreferenceType, setCurrentPreferenceType] =
    useState<PreferenceType | null>(null);

  const [loading, setLoading] = useState<boolean>(!product);
  const [error, setError] = useState<boolean | null>(null);

  const [quantity, setQuantity] = useState(1);

  const fetchProduct = async () => {
    if (!product && params.id) {
      try {
        setLoading(true);
        const fetchedProduct = await ProductApi.getProductById(
          Number(params.id)
        );
        setProduct(fetchedProduct);
      } catch (err) {
        console.error("Ошибка при загрузке продукта:", err);
        setError(true);
      } finally {
        setLoading(false);
      }
    }
  };

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
        // Если предпочтение не найдено, устанавливаем null
        setCurrentPreferenceType(null);
      } else {
        console.error("Ошибка при загрузке предпочтения продукта:", err);
        setError(true);
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
      console.log(`Товар с ID ${productId} добавлен в корзину`);
    } catch (error) {
      console.error("Ошибка при добавлении товара в корзину:", error);
    }
  };

  const handleAddToPreference = async (
    productId: number,
    preferenceType: PreferenceType
  ) => {
    if (!userId) {
      console.error("Пользователь не авторизован");
      return;
    }

    try {
      await PreferenceApi.addProductPreference(userId, {
        productId,
        preferenceType,
      });
      console.log(`Товар с ID ${productId} добавлен в предпочтения`);
      setCurrentPreferenceType(preferenceType);
    } catch (error) {
      console.error("Ошибка при добавлении товара в предпочтения:", error);
    }
  };

  const handleRemoveFromPreference = async (productId: number) => {
    if (!userId) {
      console.error("Пользователь не авторизован");
      return;
    }

    try {
      await PreferenceApi.removeProductPreference(userId, productId);
      console.log(`Товар с ID ${productId} убран из предпочтений`);
      setCurrentPreferenceType(null);
    } catch (error) {
      console.error("Ошибка при удалении товара из предпочтений:", error);
    }
  };

  const addToPreference = (type: PreferenceType) => {
    if (currentPreferenceType === type) {
      handleRemoveFromPreference(product!.id);
    } else {
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

  if (error || !product) {
    return (
      <NotFoundError
        title="Упс! Кажется, блюдо не найдено"
        message="Похоже, что запрашиваемое блюдо не существует в каталоге"
      />
    );
  }

  return (
    <main className="container mx-auto px-4 py-8">
      <div className="bg-white rounded-xl shadow-md overflow-hidden">
        <div className="md:flex">
          <div className="md:w-1/2 p-6">
            <div className="relative">
              <img
                src="https://images.unsplash.com/photo-1546069901-ba9599a7e63c?ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80"
                alt={product.name}
                className="product-image shadow-md"
              />
              {product.isNew && (
                <div className="absolute top-4 left-4">
                  <span className="new-badge bg-orange-500 text-white text-xs px-3 py-1 rounded-full font-medium">
                    новинка
                  </span>
                </div>
              )}
            </div>
          </div>

          <div className="md:w-1/2 p-6">
            <h1 className="text-2xl font-bold text-gray-800 mb-2">
              {product.name}
            </h1>

            <div className="flex items-center justify-between mb-6">
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

            <div className="flex space-x-4 mb-6">
              <button
                className={`like-btn bg-white p-2 rounded-full shadow-md hover:bg-gray-100 transition ${
                  currentPreferenceType === PreferenceType.LIKED ? "active" : ""
                }`}
                onClick={() => addToPreference(PreferenceType.LIKED)}
              >
                <i className="far fa-thumbs-up"></i>
              </button>
              <button
                className={`dislike-btn bg-white p-2 rounded-full shadow-md hover:bg-gray-100 transition ${
                  currentPreferenceType === PreferenceType.DISLIKED
                    ? "active"
                    : ""
                }`}
                onClick={() => addToPreference(PreferenceType.DISLIKED)}
              >
                <i className="far fa-thumbs-down"></i>
              </button>
            </div>

            <button
              className="w-full bg-green-500 hover:bg-green-600 text-white py-3 rounded-lg font-bold transition flex items-center justify-center text-lg"
              onClick={() => handleAddToCart(product.id)}
            >
              <i className="fas fa-plus mr-2"></i> Добавить в корзину
            </button>
          </div>
        </div>
      </div>
    </main>
  );
};

export default ProductPage;
