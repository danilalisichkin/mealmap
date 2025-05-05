import React, { useEffect, useRef, useState } from "react";
import { useAuth } from "../../../contexts/AuthContext";
import { mockProducts } from "../../../mock/products";
import { mockRecommendation } from "../../../mock/recommendations";
import RecommendationPrompt from "../RecommendationPrompt/RecommendationPrompt";
import RecommendationsResult from "../RecommendationResult/RecommendationResult";
import RecommendationsShuffling from "../RecommendationShuffling/RecommendationShuffling";
import "./RecommendationSection.css";
import { PreferenceApi } from "../../../api/preference/UserPreferenceApi";
import { ProductPreferenceDto } from "../../../api/preference/dto/ProductPreferenceDto";

interface RecommendationSectionProps {}

const AI_MESSAGES = [
  "ИИ анализирует ваши предпочтения...",
  "ИИ подбирает блюда под ваш рацион...",
  "ИИ рассчитывает идеальный баланс БЖУ...",
  "ИИ учитывает вашу диету...",
  "ИИ просматривает меню ресторанов...",
  "ИИ ищет лучшие предложения...",
  "ИИ почти готов с рекомендациями...",
];

const RecommendationSection: React.FC<RecommendationSectionProps> = () => {
  const { userId } = useAuth();
  const [productPreferences, setProductPreferences] = useState<
    ProductPreferenceDto[]
  >([]);

  //TODO: API CALL
  const recommendations = mockRecommendation;

  //TODO: API CALL
  const products = mockProducts;
  const recommendedProducts = products.filter((product) =>
    recommendations.items.some(
      (item: { productId: number }) => item.productId === product.id
    )
  );

  const [stage, setStage] = useState<"prompt" | "shuffling" | "result">(
    "prompt"
  );
  const [aiMessageIndex, setAiMessageIndex] = useState(0);
  const [typedText, setTypedText] = useState(AI_MESSAGES[0].slice(0, 0));
  const typingTimeoutRef = useRef<NodeJS.Timeout | null>(null);
  const aiIntervalRef = useRef<NodeJS.Timeout | null>(null);
  const aiTimeoutRef = useRef<NodeJS.Timeout | null>(null);
  const foodAnimIntervalRef = useRef<NodeJS.Timeout | null>(null);

  // Функция "печати" текста
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

  const handleGenerate = () => {
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

    aiTimeoutRef.current = setTimeout(() => {
      setStage("result");
      if (aiIntervalRef.current) clearInterval(aiIntervalRef.current);
      if (foodAnimIntervalRef.current)
        clearInterval(foodAnimIntervalRef.current);
    }, 5000);
  };

  const handleAddToCart = () => {
    // Здесь будет логика добавления всех рекомендованных продуктов в корзину
    console.log("Добавить все рекомендованные продукты в корзину");
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

  return (
    <div className="container mx-auto px-4 py-8 max-w-2xl">
      <div className="health-card bg-white rounded-xl shadow-sm p-6">
        <div className="flex items-center mb-6">
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

        <div id="recommendations-container">
          {stage === "prompt" && (
            <RecommendationPrompt onGenerate={handleGenerate} />
          )}

          {stage === "shuffling" && (
            <RecommendationsShuffling typedText={typedText} />
          )}

          {stage === "result" && (
            <RecommendationsResult
              recommendations={recommendations}
              recommendedProducts={recommendedProducts}
              preferredProducts={productPreferences}
              onRegenerate={handleGenerate}
              onAddToCart={handleAddToCart}
            />
          )}
        </div>
      </div>
    </div>
  );
};

export default RecommendationSection;
