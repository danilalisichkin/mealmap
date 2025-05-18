import React, { useEffect, useRef, useState } from "react";
import { useAuth } from "../../contexts/AuthContext";
import { ProductPreferenceDto } from "../../api/preference/dto/ProductPreferenceDto";
import PopupNotification, {
  NotificationType,
} from "../../components/features/PopupNotification/PopupNotification";
import { CartApi } from "../../api/cart/UserCartApi";
import { PreferenceType } from "../../api/preference/enums/PreferenceType";
import { PreferenceApi } from "../../api/preference/UserPreferenceApi";
import RecommendationPrompt from "../../components/features/RecommendationPrompt/RecommendationPrompt";
import RecommendationsShuffling from "../../components/features/RecommendationShuffling/RecommendationShuffling";
import RecommendationList from "../../components/features/RecommendationResult/RecommendationList";
import { RecommendationApi } from "../../api/recommendation/RecommendationApi";
import { ProductApi } from "../../api/product/ProductApi";
import { UserRecommendationDto } from "../../api/recommendation/dto/UserRecommendationDto";
import { ProductDto } from "../../api/product/dto/ProductDto";
import ErrorBanner from "../../components/commons/ErrorBanner/ErrorBanner";
import { RecommendationItem } from "../../api/recommendation/dto/RecommendationItem";
import { UserAllergenDto } from "../../api/health/dto/UserAllergenDto";
import { HealthApi } from "../../api/health/UserHealthApi";

interface RecommendationPageProps {}

const AI_MESSAGES = [
  "ИИ анализирует ваши предпочтения...",
  "ИИ подбирает блюда под ваш рацион...",
  "ИИ рассчитывает идеальный баланс БЖУ...",
  "ИИ учитывает вашу диету...",
  "ИИ просматривает меню ресторанов...",
  "ИИ ищет лучшие предложения...",
  "ИИ почти готов с рекомендациями...",
];

const RecommendationPage: React.FC<RecommendationPageProps> = () => {
  const { userId } = useAuth();

  const [stage, setStage] = useState<"prompt" | "shuffling" | "result">(
    "prompt"
  );

  const [recommendations, setRecommendations] =
    useState<UserRecommendationDto | null>(null);
  const [recommendedProducts, setRecommendedProducts] = useState<ProductDto[]>(
    []
  );
  const [productPreferences, setProductPreferences] = useState<
    ProductPreferenceDto[]
  >([]);
  const [userAllergens, setUserAllergens] = useState<UserAllergenDto[]>([]);

  const [aiMessageIndex, setAiMessageIndex] = useState(0);
  const [typedText, setTypedText] = useState(AI_MESSAGES[0].slice(0, 0));
  const typingTimeoutRef = useRef<NodeJS.Timeout | null>(null);
  const aiIntervalRef = useRef<NodeJS.Timeout | null>(null);
  const aiTimeoutRef = useRef<NodeJS.Timeout | null>(null);
  const foodAnimIntervalRef = useRef<NodeJS.Timeout | null>(null);

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

  const typeMessage = (msg: string) => {
    if (typingTimeoutRef.current) clearTimeout(typingTimeoutRef.current);
    setTypedText("");
    let i = 0;
    const typeNext = () => {
      setTypedText(msg.slice(0, i + 1));
      if (i < msg.length - 1) {
        i++;
        typingTimeoutRef.current = setTimeout(typeNext, 24);
      }
    };
    typeNext();
  };

  const handleGenerate = async () => {
    setStage("shuffling");
    setAiMessageIndex(0);
    typeMessage(AI_MESSAGES[0]);

    aiIntervalRef.current = setInterval(() => {
      setAiMessageIndex((prev) => {
        const next = (prev + 1) % AI_MESSAGES.length;
        typeMessage(AI_MESSAGES[next]);
        return next;
      });
    }, 3000);

    try {
      const newRecommendations = await fetchRecommendations();
      if (newRecommendations !== undefined) {
        await fetchRecommendedProducts(newRecommendations);
      }
    } catch (error) {
      console.error("Ошибка при генерации рекомендаций:", error);
    } finally {
      if (aiIntervalRef.current) clearInterval(aiIntervalRef.current);
      if (foodAnimIntervalRef.current)
        clearInterval(foodAnimIntervalRef.current);
      setStage("result");
    }
  };

  const fetchRecommendations = async () => {
    if (!userId) {
      console.error("Пользователь не авторизован");
      return;
    }

    try {
      const fetchedRecommendations =
        await RecommendationApi.getNewUserRecommendation(userId);
      setRecommendations(fetchedRecommendations);

      const productIds = fetchedRecommendations.items.map(
        (item) => item.productId
      );
      const fetchedProducts = await ProductApi.bulkGetProducts(productIds);
      setRecommendedProducts(fetchedProducts);
      return fetchedRecommendations;
    } catch (error) {
      console.error("Ошибка при загрузке рекомендаций:", error);
      return null;
    }
  };

  const fetchRecommendedProducts = async (
    newRecommendations: UserRecommendationDto | null
  ) => {
    if (!newRecommendations || newRecommendations.items.length <= 0) {
      setRecommendedProducts([]);
      return;
    }

    try {
      const productIds = newRecommendations.items.map((item) => item.productId);

      const fetchedProducts = await ProductApi.bulkGetProducts(productIds);
      setRecommendedProducts(fetchedProducts);

      const filteredItems = newRecommendations.items.filter((item) =>
        fetchedProducts.some((product) => product.id === item.productId)
      );
      setRecommendations((prev) =>
        prev ? { ...prev, items: filteredItems } : null
      );
    } catch (error) {
      console.error("Ошибка при загрузке рекомендованных продуктов:", error);
    }
  };

  const handleAddToCart = async (item: RecommendationItem) => {
    if (!userId) {
      console.error("Пользователь не авторизован");
      return;
    }

    try {
      await CartApi.addItemToCart(userId, {
        productId: item.productId,
        quantity: item.quantity,
      });
      showNotification("Блюдо добавлено в корзину!", NotificationType.SUCCESS);
      console.log(`Товар с ID ${item.productId} добавлен в корзину`);
    } catch (error) {
      showNotification(
        "Ошибка при добавлении блюда в корзину!",
        NotificationType.ERROR
      );
      console.error("Ошибка при добавлении товара в корзину:", error);
    }
  };

  const handleAddAllToCart = async () => {
    if (!userId) {
      console.error("Пользователь не авторизован");
      return;
    }

    if (!recommendations) return;

    for (const recommendation of recommendations.items) {
      await handleAddToCart(recommendation);
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
        productId: productId,
        preferenceType: preferenceType,
      });
      showNotification(
        "Блюдо добавлено в предпочтения!",
        NotificationType.SUCCESS
      );
      console.log(`Товар с ID ${productId} добавлен в предпочтения`);
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
      showNotification(
        "Блюдо убрано из предпочтений!",
        NotificationType.SUCCESS
      );
      console.log(`Товар с ID ${productId} убран из предпочтений`);
    } catch (error) {
      console.error("Ошибка при удалении товара из предпочтений:", error);
    }
  };

  const fetchProductPreferences = async () => {
    if (!userId) {
      console.error("Пользователь не авторизован");
      return;
    }

    try {
      const response = await PreferenceApi.getProductPreferences(userId);
      setProductPreferences(response);
    } catch (error) {
      console.error("Ошибка при загрузке продуктов:", error);
    }
  };

  const fetchUserAllergens = async () => {
    if (!userId) return;

    try {
      const response = await HealthApi.getUserAllergens(userId);
      setUserAllergens(response);
    } catch (err: any) {
      console.error("Ошибка при загрузке аллергий пользователя:", err);
    }
  };

  useEffect(() => {
    if (stage === "shuffling") {
      typeMessage(AI_MESSAGES[aiMessageIndex]);
    }
    // eslint-disable-next-line
  }, [aiMessageIndex]);

  useEffect(() => {
    return () => {
      if (aiIntervalRef.current) clearInterval(aiIntervalRef.current);
      if (aiTimeoutRef.current) clearTimeout(aiTimeoutRef.current);
      if (typingTimeoutRef.current) clearTimeout(typingTimeoutRef.current);
      if (foodAnimIntervalRef.current)
        clearInterval(foodAnimIntervalRef.current);
    };
  }, []);

  useEffect(() => {
    if (userId) {
      fetchProductPreferences();
    }
  }, [userId]);

  useEffect(() => {
    if (userId) {
      fetchUserAllergens();
    }
  }, [userId]);

  function animateFoodItemsSequence() {
    const foodItems = Array.from(
      document.querySelectorAll<HTMLDivElement>(".food-item")
    );

    foodItems.forEach((item, index) => {
      const angle = index * 90 - 45; // -45 чтобы начать сверху-слева
      const radius = 70;
      const x = radius * Math.cos((angle * Math.PI) / 180);
      const y = radius * Math.sin((angle * Math.PI) / 180);

      item.style.left = `calc(50% + ${x}px - 25px)`;
      item.style.top = `calc(50% + ${y}px - 25px)`;
      item.style.opacity = "0";
      item.style.transform = "scale(0)";
    });

    foodItems.forEach((item) => {
      item.style.opacity = "0";
      item.style.transform = "scale(0)";
      item.style.transition = "none";
      item.style.animation = "none";
    });

    foodItems.forEach((item, index) => {
      setTimeout(() => {
        item.style.transition = "opacity 0.5s, transform 0.5s";
        item.style.opacity = "1";
        item.style.transform = "scale(1)";
      }, index * 400); // 1200
    });

    const disappearDelay = 2 * 400 + 200;
    foodItems.forEach((item, index) => {
      setTimeout(() => {
        item.style.transition = "opacity 0.5s, transform 0.5s";
        item.style.opacity = "0";
        item.style.transform = "scale(0)";
      }, disappearDelay + index * 400);
    });
  }

  useEffect(() => {
    if (stage === "shuffling") {
      animateFoodItemsSequence();

      foodAnimIntervalRef.current = setInterval(() => {
        animateFoodItemsSequence();
      }, 3000); // время появления + пауза + время исчезновения
    } else {
      if (foodAnimIntervalRef.current)
        clearInterval(foodAnimIntervalRef.current);
      const foodItems = Array.from(
        document.querySelectorAll<HTMLDivElement>(".food-item")
      );
      foodItems.forEach((item) => {
        item.style.opacity = "0";
        item.style.transform = "scale(0)";
        item.style.transition = "none";
        item.style.animation = "none";
      });
    }
  }, [stage]);

  if (stage === "result" && !recommendations) {
    return (
      <div className="min-h-screen flex items-center justify-center">
        <ErrorBanner
          error={{
            title: "Упс! Кажется, рекомендации не найдены",
            detail: "ИИ не смог подобрать для вас блюда",
            status: "404",
          }}
        />
      </div>
    );
  }

  const isRecommendationsFound =
    recommendations &&
    recommendations.items &&
    recommendations.items.length > 0 &&
    recommendedProducts.length > 0;

  return (
    <main className="container min-h-screen mx-auto px-4 py-4">
      {/* Page Title */}
      <div className="bg-white rounded-xl shadow-sm p-6 max-w-md mx-auto mb-6">
        <div className="flex items-center">
          <div className="w-10 h-10 rounded-full bg-green-100 flex items-center justify-center text-green-600 mr-3">
            <i className="fas fa-robot text-xl"></i>
          </div>
          <div>
            <h3 className="font-semibold text-gray-800">
              Виртуальный ассистент
            </h3>
            <p className="text-sm text-gray-500">
              Персонализированный подбор блюд
            </p>
          </div>
        </div>
      </div>

      {/* Start Generation / Animation */}
      {(stage === "prompt" || stage === "shuffling") && (
        <div className="bg-white rounded-xl shadow-sm p-6 max-w-md mx-auto mb-6">
          {stage === "prompt" && (
            <RecommendationPrompt onGenerate={handleGenerate} />
          )}

          {stage === "shuffling" && (
            <RecommendationsShuffling typedText={typedText} />
          )}
        </div>
      )}

      {/* Result */}
      {stage === "result" && isRecommendationsFound && (
        <div className="bg-white rounded-xl shadow-sm p-6 max-w-md mx-auto mb-6">
          <div className="ai-message">
            <div className="flex items-start">
              <div className="mr-3 text-green-500">
                <i className="fas fa-robot"></i>
              </div>
              <div>
                <p className="text-sm text-gray-800">
                  {recommendations.message && recommendations.message !== ""
                    ? recommendations.message
                    : "Я старался подобрать наиболее подходящие блюда для Вас. Надеюсь, Вам понравится ;)."}
                </p>
              </div>
            </div>
          </div>
        </div>
      )}

      {/* Generate Again */}
      {stage === "result" && recommendations && (
        <div className="mt-6 mb-6 text-center">
          <button
            className="px-6 py-3 bg-gray-100 text-green-700 rounded-full font-medium hover:bg-green-50 transition-all shadow"
            onClick={handleGenerate}
          >
            <i className="fas fa-sync-alt mr-2"></i> Подобрать заново
          </button>
        </div>
      )}

      {/* Result Items */}
      {stage === "result" && isRecommendationsFound && (
        <div className="bg-white rounded-xl shadow-sm p-6 max-w-4xl mx-auto">
          <RecommendationList
            recommendations={recommendations}
            recommendedProducts={recommendedProducts}
            preferredProducts={productPreferences}
            userAllergens={userAllergens}
            onAddToCart={handleAddToCart}
            onAddToPreference={handleAddToPreference}
            onRemoveFromPreference={handleRemoveFromPreference}
          />
        </div>
      )}

      {/* Add result to cart */}
      {stage === "result" && isRecommendationsFound && (
        <div className="mt-6 text-center">
          <button
            className="px-6 py-3 bg-gradient-to-r from-green-500 to-green-600 text-white rounded-full font-medium hover:from-green-600 hover:to-green-700 transition-all shadow-md hover:shadow-lg"
            onClick={handleAddAllToCart}
          >
            <i className="fas fa-basket-shopping mr-2"></i> Добавить все в
            корзину
          </button>
        </div>
      )}

      {/* Result Items Not Found*/}
      {stage === "result" && recommendations && !isRecommendationsFound && (
        <ErrorBanner
          error={{
            title: "Упс! Кажется, рекомендации не найдены",
            detail: "ИИ не смог подобрать для вас блюда",
            status: "404",
          }}
        />
      )}

      <PopupNotification
        key={notification.id}
        message={notification.message}
        type={notification.type}
        isVisible={notification.isVisible}
      />
    </main>
  );
};

export default RecommendationPage;
