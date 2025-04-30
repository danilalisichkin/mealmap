import React, { useState, useRef, useEffect } from "react";
import "./RecommendationSection.css";
import { mockRecommendation } from "../../../mock/recommendations";
import { mockProducts } from "../../../mock/products";
import RecommendationsResult from "../RecommendationResult/RecommendationResult";
import RecommendationsShuffling from "../RecommendationShuffling/RecommendationShuffling";
import RecommendationPrompt from "../RecommendationPrompt/RecommendationPrompt";

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
  const recommendations = mockRecommendation;
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

  // Запуск процесса подбора рекомендаций
  const handleGenerate = () => {
    setStage("shuffling");
    setAiMessageIndex(0);
    typeMessage(AI_MESSAGES[0]);

    // Меняем фразу каждые 3 секунды
    aiIntervalRef.current = setInterval(() => {
      setAiMessageIndex((prev) => {
        const next = (prev + 1) % AI_MESSAGES.length;
        typeMessage(AI_MESSAGES[next]);
        return next;
      });
    }, 3000);

    // Через 5 секунд показываем результат
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
  }

  // При смене aiMessageIndex печатаем новый текст
  useEffect(() => {
    if (stage === "shuffling") {
      typeMessage(AI_MESSAGES[aiMessageIndex]);
    }
    // eslint-disable-next-line
  }, [aiMessageIndex]);

  // Очищаем таймеры при размонтировании
  useEffect(() => {
    return () => {
      if (aiIntervalRef.current) clearInterval(aiIntervalRef.current);
      if (aiTimeoutRef.current) clearTimeout(aiTimeoutRef.current);
      if (typingTimeoutRef.current) clearTimeout(typingTimeoutRef.current);
      if (foodAnimIntervalRef.current)
        clearInterval(foodAnimIntervalRef.current);
    };
  }, []);

  function animateFoodItemsSequence() {
    const foodItems = Array.from(
      document.querySelectorAll<HTMLDivElement>(".food-item")
    );

    foodItems.forEach((item, index) => {
      // Позиционирование
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
      // Сбросим стили
      item.style.opacity = "0";
      item.style.transform = "scale(0)";
      item.style.transition = "none";
      item.style.animation = "none";
    });

    // Плавное появление друг за другом
    foodItems.forEach((item, index) => {
      setTimeout(() => {
        item.style.transition = "opacity 0.5s, transform 0.5s";
        item.style.opacity = "1";
        item.style.transform = "scale(1)";
      }, index * 400); // 1200
    });

    // Плавное исчезновение друг за другом через 1 сек после появления последнего
    const disappearDelay = 2 * 400 + 200;
    foodItems.forEach((item, index) => {
      setTimeout(() => {
        item.style.transition = "opacity 0.5s, transform 0.5s";
        item.style.opacity = "0";
        item.style.transform = "scale(0)";
      }, disappearDelay + index * 400);
    });
  }

  // Анимация foodItems проигрывается всё время, пока stage === "shuffling"
  useEffect(() => {
    if (stage === "shuffling") {
      animateFoodItemsSequence();

      foodAnimIntervalRef.current = setInterval(() => {
        animateFoodItemsSequence();
      }, 3000); // время появления + пауза + время исчезновения
    } else {
      if (foodAnimIntervalRef.current)
        clearInterval(foodAnimIntervalRef.current);
      // Сбросить стили при выходе из shuffling
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
