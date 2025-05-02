import React, { useEffect, useState } from "react";
import { ProductDto } from "../../api/product/dto/ProductDto";
import { useLocation, useParams } from "react-router-dom";
import { ProductApi } from "../../api/product/ProductApi";
import NotFoundError from "../../components/commons/NotFoundError/NotFoundError";

interface ProductPageProps {}

const ProductPage: React.FC<ProductPageProps> = () => {
  const location = useLocation();
  const params = useParams();
  const [product, setProduct] = useState<ProductDto | null>(
    location.state?.product ?? null
  );
  
  const [loading, setLoading] = useState<boolean>(!product);
  const [error, setError] = useState<boolean | null>(null);

  const fetchProduct = async () => {
    if (!product && params.id) {
      try {
        setLoading(true);
        const fetchedProduct = await ProductApi.getProductById(Number(params.id));
        setProduct(fetchedProduct);
      } catch (err) {
        console.error("Ошибка при загрузке продукта:", err);
        setError(true);
      } finally {
        setLoading(false);
      }
    }
  };

  useEffect(() => {
    fetchProduct();
  }, [params.id]);


  const [quantity, setQuantity] = useState(1);

  if (loading) {
    return <div className="text-center py-12">Загрузка...</div>;
  }

  if (error || !product) {
    return <NotFoundError title="Упс! Кажется, блюдо не найдено" message="Похоже, что запрашиваемое блюдо не существует в каталоге" />
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

            <div className="mb-6">
              <div className="flex justify-between text-gray-600 mb-3">
                <span>
                  <i className="fas fa-weight-hanging mr-2"></i>
                  {product.weight} г
                </span>
                <span>
                  <i className="fas fa-fire mr-2"></i>
                  {product.nutrients.calories} ккал
                </span>
              </div>

              <div className="grid grid-cols-2 gap-3 mb-4">
                <div className="bg-gray-100 p-3 rounded-lg">
                  <span className="text-gray-600">
                    <span className="nutrient-dot bg-blue-500"></span>
                    Белки: {product.nutrients.proteins} г
                  </span>
                </div>
                <div className="bg-gray-100 p-3 rounded-lg">
                  <span className="text-gray-600">
                    <span className="nutrient-dot bg-yellow-500"></span>
                    Жиры: {product.nutrients.fats} г
                  </span>
                </div>
                <div className="bg-gray-100 p-3 rounded-lg">
                  <span className="text-gray-600">
                    <span className="nutrient-dot bg-red-500"></span>
                    Углеводы: {product.nutrients.carbs} г
                  </span>
                </div>
                <div className="bg-gray-100 p-3 rounded-lg">
                  <span className="text-gray-600">
                    <span className="nutrient-dot bg-green-500"></span>
                    Клетчатка: {product.nutrients.fibers} г
                  </span>
                </div>
                <div className="bg-gray-100 p-3 rounded-lg">
                  <span className="text-gray-600">
                    <span className="nutrient-dot bg-yellow-500"></span>
                    Сахар: {product.nutrients.sugars} г
                  </span>
                </div>
              </div>
            </div>

            <div className="mb-6">
              <h3 className="font-semibold text-gray-800 mb-2">Описание</h3>
              <p className="text-gray-600">{product.description}</p>
            </div>

            <nav className="mb-6">
              <h3 className="font-semibold text-gray-800 mb-2">Категории</h3>
              <span className="text-gray-600 hover:text-green-600">
                Главная
              </span>
              {product.categories.map((cat, idx) => {
                const path: string[] = [];
                let current = cat;
                while (current) {
                  path.unshift(current.name);
                  current = current.parent as typeof cat;
                }
                return (
                  <div key={idx} className="flex items-center">
                    <span className="mx-2 text-gray-600">/</span>
                    <span className="flex text-gray-600 flex-wrap gap-1">
                      {path.map((name, i) => (
                        <span
                          key={i}
                          className="text-gray-600 hover:text-green-600"
                        >
                          {name}
                          {i < path.length - 1 && (
                            <span className="mx-1 text-gray-600">/</span>
                          )}
                        </span>
                      ))}
                    </span>
                  </div>
                );
              })}
            </nav>

            <button className="w-full bg-green-500 hover:bg-green-600 text-white py-3 rounded-lg font-bold transition flex items-center justify-center text-lg">
              <i className="fas fa-plus mr-2"> Добавить в корзину</i>
            </button>
          </div>
        </div>
      </div>

      <section className="mt-12">
        <h2 className="text-xl font-bold text-gray-800 mb-6">Похожие товары</h2>
        <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6">
          <div className="bg-white rounded-lg shadow-sm overflow-hidden transition hover:shadow-md">
            <div className="relative">
              <img
                src="https://images.unsplash.com/photo-1512621776951-a57141f2eefd?ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=80"
                alt="Овощной салат"
                className="w-full h-40 object-cover"
              />
            </div>
            <div className="p-4">
              <h3 className="font-semibold text-gray-800 mb-1">
                Овощной салат
              </h3>
              <div className="flex justify-between items-center">
                <span className="text-green-600 font-bold">280₽</span>
                <button className="text-green-500 hover:text-green-700">
                  <i className="fas fa-plus"></i>
                </button>
              </div>
            </div>
          </div>

          <div className="bg-white rounded-lg shadow-sm overflow-hidden transition hover:shadow-md">
            <div className="relative">
              <img
                src="https://images.unsplash.com/photo-1544025162-d76694265947?ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=80"
                alt="Греческий салат"
                className="w-full h-40 object-cover"
              />
              <div className="absolute top-2 right-2">
                <span className="bg-orange-500 text-white text-xs px-2 py-1 rounded-full font-medium">
                  новинка
                </span>
              </div>
            </div>
            <div className="p-4">
              <h3 className="font-semibold text-gray-800 mb-1">
                Греческий салат
              </h3>
              <div className="flex justify-between items-center">
                <span className="text-green-600 font-bold">290₽</span>
                <button className="text-green-500 hover:text-green-700">
                  <i className="fas fa-plus"></i>
                </button>
              </div>
            </div>
          </div>

          <div className="bg-white rounded-lg shadow-sm overflow-hidden transition hover:shadow-md">
            <div className="relative">
              <img
                src="https://images.unsplash.com/photo-1490645935967-10de6ba17061?ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=80"
                alt="Салат с креветками"
                className="w-full h-40 object-cover"
              />
            </div>
            <div className="p-4">
              <h3 className="font-semibold text-gray-800 mb-1">
                Салат с креветками
              </h3>
              <div className="flex justify-between items-center">
                <span className="text-green-600 font-bold">₽420</span>
                <button className="text-green-500 hover:text-green-700">
                  <i className="fas fa-plus"></i>
                </button>
              </div>
            </div>
          </div>
        </div>
      </section>
    </main>
  );
};

export default ProductPage;
