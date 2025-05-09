import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import AOS from "aos";
import "./GreetingPage.css";
import "aos/dist/aos.css";

interface GreetingPageProps {}

const GreetingPage: React.FC<GreetingPageProps> = () => {
  const navigate = useNavigate();

  useEffect(() => {
    AOS.init({
      duration: 1000,
      once: true,
    });
    AOS.refresh();
  }, []);

  const navigateToFaq = () => {
    navigate("/faq");
  };

  return (
    <main className="container mx-auto px-4 py-8">
      {/* Hero Section */}
      <HeroSection onNavigateToFaq={navigateToFaq} />

      <StatsSection />

      <FeatureSection />

      <BenefitsSection />

      <PartnersSection />

      <TestimonialsSection />
    </main>
  );
};

interface HeroSectionProps {
  onNavigateToFaq: () => void;
}

const HeroSection: React.FC<HeroSectionProps> = ({ onNavigateToFaq }) => {
  return (
    <section className="hero-gradient">
      <div className="container mx-auto px-4 py-16 md:py-24 flex flex-col md:flex-row items-center">
        <div className="md:w-1/2 mb-10 md:mb-0" data-aos="fade-right">
          <h1 className="text-4xl md:text-5xl font-bold text-gray-800 mb-6">
            Умная система{" "}
            <span className="text-green-500">корпоративного питания</span>
          </h1>
          <p className="text-xl text-gray-600 mb-8">
            Автоматизированная платформа для организации здорового и
            персонализированного питания сотрудников вашей компании
          </p>
          <div className="flex flex-col sm:flex-row space-y-3 sm:space-y-0 sm:space-x-4">
            <button className="bg-green-500 hover:bg-green-600 text-white px-6 py-3 rounded-full font-medium transition transform hover:scale-105 scale">
              Подключить компанию
            </button>
            <button
              className="border border-green-500 text-green-500 hover:bg-green-50 px-6 py-3 rounded-full font-medium transition"
              onClick={onNavigateToFaq}
            >
              Узнать больше
            </button>
          </div>
        </div>
        <div className="md:w-1/2 flex justify-center" data-aos="fade-left">
          <img
            src="https://images.unsplash.com/photo-1555244162-803834f70033?ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=80"
            alt="Корпоративное питание"
            className="rounded-xl shadow-xl floating max-w-md w-full"
          />
        </div>
      </div>
    </section>
  );
};

const StatsSection: React.FC<{}> = () => {
  return (
    <section className="bg-white py-12">
      <div className="container mx-auto px-4">
        <div className="grid grid-cols-2 md:grid-cols-4 gap-6 text-center">
          <div className="p-6" data-aos="zoom-in">
            <div className="text-4xl font-bold text-green-500 mb-2">1</div>
            <div className="text-gray-600">Компаний</div>
          </div>
          <div className="p-6" data-aos="zoom-in" data-aos-delay="100">
            <div className="text-4xl font-bold text-green-500 mb-2">5</div>
            <div className="text-gray-600">Поставщиков</div>
          </div>
          <div className="p-6" data-aos="zoom-in" data-aos-delay="200">
            <div className="text-4xl font-bold text-green-500 mb-2">50+</div>
            <div className="text-gray-600">Сотрудников</div>
          </div>
          <div className="p-6" data-aos="zoom-in" data-aos-delay="300">
            <div className="text-4xl font-bold text-green-500 mb-2">500+</div>
            <div className="text-gray-600">Обедов в месяц</div>
          </div>
        </div>
      </div>
    </section>
  );
};

const FeatureSection: React.FC<{}> = () => {
  return (
    <section id="features" className="py-16 bg-gray-50">
      <div className="container mx-auto px-4">
        <div className="text-center mb-16" data-aos="fade-up">
          <h2 className="text-3xl font-bold text-gray-800 mb-4">
            Как работает MealMap?
          </h2>
          <p className="text-xl text-gray-600 max-w-3xl mx-auto">
            Простая и удобная система для всех участников процесса
          </p>
        </div>

        <div className="grid md:grid-cols-3 gap-8">
          <div
            className="bg-white rounded-xl p-8 shadow-md card-hover"
            data-aos="fade-up"
          >
            <div className="w-16 h-16 bg-green-100 rounded-full flex items-center justify-center mb-6 mx-auto">
              <i className="fas fa-building text-green-500 text-2xl"></i>
            </div>
            <h3 className="text-xl font-semibold text-center mb-4">
              Для компаний
            </h3>
            <ul className="space-y-3 text-gray-600">
              <li className="flex items-start">
                <i className="fas fa-check text-green-500 mt-1 mr-2"></i>
                <span>Полная автоматизация процесса питания сотрудников</span>
              </li>
              <li className="flex items-start">
                <i className="fas fa-check text-green-500 mt-1 mr-2"></i>
                <span>Гибкие условия оплаты и налоговые преимущества</span>
              </li>
              <li className="flex items-start">
                <i className="fas fa-check text-green-500 mt-1 mr-2"></i>
                <span>Контроль расходов и аналитика питания</span>
              </li>
            </ul>
          </div>

          <div
            className="bg-white rounded-xl p-8 shadow-md card-hover"
            data-aos="fade-up"
            data-aos-delay="100"
          >
            <div className="w-16 h-16 bg-blue-100 rounded-full flex items-center justify-center mb-6 mx-auto">
              <i className="fas fa-user-tie text-blue-500 text-2xl"></i>
            </div>
            <h3 className="text-xl font-semibold text-center mb-4">
              Для сотрудников
            </h3>
            <ul className="space-y-3 text-gray-600">
              <li className="flex items-start">
                <i className="fas fa-check text-blue-500 mt-1 mr-2"></i>
                <span>Персонализированное меню с учетом предпочтений</span>
              </li>
              <li className="flex items-start">
                <i className="fas fa-check text-blue-500 mt-1 mr-2"></i>
                <span>Удобное управление заказами через приложение</span>
              </li>
              <li className="flex items-start">
                <i className="fas fa-check text-blue-500 mt-1 mr-2"></i>
                <span>Здоровое питание без лишних затрат времени</span>
              </li>
            </ul>
          </div>

          <div
            className="bg-white rounded-xl p-8 shadow-md card-hover"
            data-aos="fade-up"
            data-aos-delay="200"
          >
            <div className="w-16 h-16 bg-purple-100 rounded-full flex items-center justify-center mb-6 mx-auto">
              <i className="fas fa-truck text-purple-500 text-2xl"></i>
            </div>
            <h3 className="text-xl font-semibold text-center mb-4">
              Для поставщиков
            </h3>
            <ul className="space-y-3 text-gray-600">
              <li className="flex items-start">
                <i className="fas fa-check text-purple-500 mt-1 mr-2"></i>
                <span>Стабильный поток заказов от корпоративных клиентов</span>
              </li>
              <li className="flex items-start">
                <i className="fas fa-check text-purple-500 mt-1 mr-2"></i>
                <span>Автоматизация процессов и сокращение издержек</span>
              </li>
              <li className="flex items-start">
                <i className="fas fa-check text-purple-500 mt-1 mr-2"></i>
                <span>Возможность расширения клиентской базы</span>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </section>
  );
};

const BenefitsSection: React.FC<{}> = () => {
    return (
        <section id="benefits" className="py-16 bg-white">
            <div className="container mx-auto px-4">
                <div className="text-center mb-16" data-aos="fade-up">
                    <h2 className="text-3xl font-bold text-gray-800 mb-4">
                        Преимущества MealMap
                    </h2>
                    <p className="text-xl text-gray-600 max-w-3xl mx-auto">
                        Почему компании выбирают нашу платформу для организации питания
                    </p>
                </div>

                <div className="grid md:grid-cols-2 gap-12 items-center">
                    <div className="space-y-8" data-aos="fade-right">
                        <div className="flex items-start">
                            <div className="w-12 h-12 bg-green-100 rounded-full flex-shrink-0 flex items-center justify-center mr-4 mt-1">
                                <i className="fas fa-clock text-green-500"></i>
                            </div>
                            <div>
                                <h3 className="text-xl font-semibold mb-2">Экономия времени</h3>
                                <p className="text-gray-600">
                                    Автоматизация всех процессов от заказа до доставки избавляет
                                    HR-отделы от рутинной работы
                                </p>
                            </div>
                        </div>

                        <div className="flex items-start">
                            <div className="w-12 h-12 bg-blue-100 rounded-full flex-shrink-0 flex items-center justify-center mr-4 mt-1">
                                <i className="fas fa-heart text-blue-500"></i>
                            </div>
                            <div>
                                <h3 className="text-xl font-semibold mb-2">
                                    Здоровые сотрудники
                                </h3>
                                <p className="text-gray-600">
                                    Персонализированное питание повышает продуктивность и снижает
                                    заболеваемость
                                </p>
                            </div>
                        </div>

                        <div className="flex items-start">
                            <div className="w-12 h-12 bg-purple-100 rounded-full flex-shrink-0 flex items-center justify-center mr-4 mt-1">
                                <i className="fas fa-chart-line text-purple-500"></i>
                            </div>
                            <div>
                                <h3 className="text-xl font-semibold mb-2">
                                    Прозрачность расходов
                                </h3>
                                <p className="text-gray-600">
                                    Детальная аналитика позволяет контролировать бюджет на питание
                                    и оптимизировать затраты
                                </p>
                            </div>
                        </div>

                        <div className="flex items-start">
                            <div className="w-12 h-12 bg-red-100 rounded-full flex-shrink-0 flex items-center justify-center mr-4 mt-1">
                                <i className="fas fa-users text-red-500"></i>
                            </div>
                            <div>
                                <h3 className="text-xl font-semibold mb-2">
                                    Повышение лояльности сотрудников
                                </h3>
                                <p className="text-gray-600">
                                    Забота о питании сотрудников улучшает их удовлетворенность и
                                    укрепляет корпоративную культуру
                                </p>
                            </div>
                        </div>
                    </div>

                    <div data-aos="fade-left">
                        <img
                            src="https://images.unsplash.com/photo-1521791055366-0d553872125f?ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=80"
                            alt="Преимущества MealMap"
                            className="rounded-xl shadow-xl w-full"
                        />
                    </div>
                </div>
            </div>
        </section>
    );
};

const PartnersSection: React.FC<{}> = () => {
  return (
    <section id="partners" className="py-16 bg-green-50">
      <div className="container mx-auto px-4">
        <div className="text-center mb-16" data-aos="fade-up">
          <h2 className="text-3xl font-bold text-gray-800 mb-4">
            Станьте партнером MealMap
          </h2>
          <p className="text-xl text-gray-600 max-w-3xl mx-auto">
            Присоединяйтесь к нашей сети поставщиков и получите доступ к тысячам
            корпоративных клиентов
          </p>
        </div>

        <div className="grid md:grid-cols-2 gap-12 items-center">
          <div data-aos="fade-right">
            <img
              src="https://images.unsplash.com/photo-1555396273-367ea4eb4db5?ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=80"
              alt="Партнеры MealMap"
              className="rounded-xl shadow-xl w-full"
            />
          </div>

          <div className="space-y-6" data-aos="fade-left">
            <div className="bg-white rounded-lg p-6 shadow-sm card-hover">
              <h3 className="text-xl font-semibold mb-3 text-green-600">
                Для ресторанов и кафе
              </h3>
              <p className="text-gray-600">
                Увеличьте загрузку кухни в "мертвые" часы за счет корпоративных
                заказов
              </p>
            </div>

            <div className="bg-white rounded-lg p-6 shadow-sm card-hover">
              <h3 className="text-xl font-semibold mb-3 text-green-600">
                Для служб доставки
              </h3>
              <p className="text-gray-600">
                Получайте стабильные объемы заказов с предоплатой и
                гарантированной доставкой
              </p>
            </div>

            <div className="bg-white rounded-lg p-6 shadow-sm card-hover">
              <h3 className="text-xl font-semibold mb-3 text-green-600">
                Для производителей
              </h3>
              <p className="text-gray-600">
                Продвигайте свои продукты питания напрямую корпоративным
                клиентам
              </p>
            </div>

            <button className="w-full bg-green-500 hover:bg-green-600 text-white py-3 rounded-lg font-medium mt-4 transition transform hover:scale-105">
              Стать поставщиком
            </button>
          </div>
        </div>
      </div>
    </section>
  );
};

const TestimonialsSection: React.FC<{}> = () => {
  return (
    <section className="py-16 bg-white">
      <div className="container mx-auto px-4">
        <div className="text-center mb-16" data-aos="fade-up">
          <h2 className="text-3xl font-bold text-gray-800 mb-4">
            Нас рекомендуют
          </h2>
          <p className="text-xl text-gray-600 max-w-3xl mx-auto">
            Что говорят наши клиенты и партнеры
          </p>
        </div>

        <div className="grid md:grid-cols-3 gap-8">
          <div className="bg-gray-50 rounded-xl p-8" data-aos="zoom-in">
            <div className="flex items-center mb-4">
              <div className="w-12 h-12 rounded-full bg-green-100 flex items-center justify-center mr-4">
                <i className="fas fa-user text-green-500"></i>
              </div>
              <div>
                <h4 className="font-semibold">Александр К.</h4>
                <p className="text-sm text-gray-500">HR-директор</p>
              </div>
            </div>
            <p className="text-gray-600 italic">
              "С MealMap мы сократили время на организацию питания сотрудников
              на 80%. Теперь это полностью автоматизированный процесс."
            </p>
          </div>

          <div
            className="bg-gray-50 rounded-xl p-8"
            data-aos="zoom-in"
            data-aos-delay="100"
          >
            <div className="flex items-center mb-4">
              <div className="w-12 h-12 rounded-full bg-blue-100 flex items-center justify-center mr-4">
                <i className="fas fa-user text-blue-500"></i>
              </div>
              <div>
                <h4 className="font-semibold">Елена С.</h4>
                <p className="text-sm text-gray-500">Сотрудник</p>
              </div>
            </div>
            <p className="text-gray-600 italic">
              "Наконец-то могу есть то, что мне действительно нравится и
              подходит по диете. Больше не нужно тратить время на поиск еды."
            </p>
          </div>

          <div
            className="bg-gray-50 rounded-xl p-8"
            data-aos="zoom-in"
            data-aos-delay="200"
          >
            <div className="flex items-center mb-4">
              <div className="w-12 h-12 rounded-full bg-purple-100 flex items-center justify-center mr-4">
                <i className="fas fa-user text-purple-500"></i>
              </div>
              <div>
                <h4 className="font-semibold">Дмитрий П.</h4>
                <p className="text-sm text-gray-500">Ресторатор</p>
              </div>
            </div>
            <p className="text-gray-600 italic">
              "Благодаря MealMap мы заполнили обеденные часы в нашем ресторане и
              получили стабильный доход от корпоративных клиентов."
            </p>
          </div>
        </div>
      </div>
    </section>
  );
};

export default GreetingPage;
