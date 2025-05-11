import React, { useEffect } from "react";
import AOS from "aos";
import "aos/dist/aos.css";

interface SupplierRegistrationPageProps {}

const SupplierRegistrationPage: React.FC<
  SupplierRegistrationPageProps
> = () => {
  useEffect(() => {
    AOS.init({
      duration: 1000,
      once: true,
    });
    AOS.refresh();
  }, []);

  return (
    <main className="container min-h-screen mx-auto px-4 py-8">
      <HeroSection />

      <BenefitsSection />

      <SuppliersSection />

      <HowItWorksSection />

      <RegistrationSection />

      <TestimonialsSection />
    </main>
  );
};

const HeroSection: React.FC<{}> = () => {
  return (
    <section className="hero-gradient py-16 md:py-24">
      <div className="container mx-auto px-4">
        <div className="flex flex-col md:flex-row items-center">
          <div className="md:w-1/2 mb-12 md:mb-0" data-aos="fade-right">
            <h1 className="text-4xl md:text-5xl font-bold mb-6">
              <span className="text-green-500">Станьте поставщиком</span>
              <br />
              корпоративного питания
            </h1>
            <p className="text-xl text-gray-600 mb-8">
              Присоединяйтесь к нашей платформе и получите доступ к тысячам
              корпоративных клиентов, автоматизацию заказов и стабильный доход.
            </p>
          </div>
          <div className="md:w-1/2 flex justify-center" data-aos="fade-left">
            <img
              src="https://images.unsplash.com/photo-1555396273-367ea4eb4db5?ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80"
              alt="Поставщики питания"
              className="rounded-xl shadow-xl floating max-w-md w-full"
            />
          </div>
        </div>
        <div className="flex flex-col sm:flex-row space-y-3 sm:space-y-0 sm:space-x-4">
          <a
            href="#registration"
            className="bg-green-500 hover:bg-green-600 text-white px-6 py-3 rounded-full font-medium text-center transition transform hover:scale-105"
            onClick={(e) => {
              e.preventDefault();
              document.querySelector("#registration")?.scrollIntoView({
                behavior: "smooth",
              });
            }}
          >
            Стать поставщиком
          </a>
          <a
            href="#how-it-works"
            className="border border-green-500 text-green-500 hover:bg-green-50 px-6 py-3 rounded-full font-medium text-center transition"
            onClick={(e) => {
              e.preventDefault();
              document.querySelector("#how-it-works")?.scrollIntoView({
                behavior: "smooth",
              });
            }}
          >
            Подробнее
          </a>
        </div>
      </div>
    </section>
  );
};

const BenefitsSection: React.FC<{}> = () => {
  return (
    <section id="benefits" className="py-16 bg-gray-50">
      <div className="container mx-auto px-4">
        <div className="text-center mb-16" data-aos="fade-up">
          <h2 className="text-3xl font-bold mb-4">
            Почему поставщики выбирают MealMap?
          </h2>
          <p className="text-xl text-gray-600 max-w-3xl mx-auto">
            Мы создаем выгодные условия для партнеров и помогаем развивать
            бизнес
          </p>
        </div>

        <div className="grid md:grid-cols-3 gap-8">
          <div
            className="bg-white rounded-xl p-8 shadow-md card-hover"
            data-aos="fade-up"
          >
            <div className="w-16 h-16 bg-green-100 rounded-full flex items-center justify-center mb-6 mx-auto">
              <i className="fas fa-chart-line text-green-500 text-2xl"></i>
            </div>
            <h3 className="text-xl font-semibold text-center mb-4">
              Рост продаж
            </h3>
            <p className="text-gray-600 text-center">
              Увеличение объема заказов за счет доступа к корпоративным клиентам
              и их сотрудникам
            </p>
          </div>

          <div
            className="bg-white rounded-xl p-8 shadow-md card-hover"
            data-aos="fade-up"
            data-aos-delay="100"
          >
            <div className="w-16 h-16 bg-blue-100 rounded-full flex items-center justify-center mb-6 mx-auto">
              <i className="fas fa-cogs text-blue-500 text-2xl"></i>
            </div>
            <h3 className="text-xl font-semibold text-center mb-4">
              Автоматизация
            </h3>
            <p className="text-gray-600 text-center">
              Удобная система управления заказами, интеграция с вашей кухней и
              сокращение ручного труда
            </p>
          </div>

          <div
            className="bg-white rounded-xl p-8 shadow-md card-hover"
            data-aos="fade-up"
            data-aos-delay="200"
          >
            <div className="w-16 h-16 bg-purple-100 rounded-full flex items-center justify-center mb-6 mx-auto">
              <i className="fas fa-hand-holding-usd text-purple-500 text-2xl"></i>
            </div>
            <h3 className="text-xl font-semibold text-center mb-4">
              Стабильные платежи
            </h3>
            <p className="text-gray-600 text-center">
              Гарантированные выплаты и прозрачная система расчетов без задержек
            </p>
          </div>
        </div>
      </div>
    </section>
  );
};

const SuppliersSection: React.FC<{}> = () => {
  return (
    <section className="py-16 bg-white">
      <div className="container mx-auto px-4">
        <div className="text-center mb-16" data-aos="fade-up">
          <h2 className="text-3xl font-bold mb-4">
            Кто может стать поставщиком?
          </h2>
          <p className="text-xl text-gray-600 max-w-3xl mx-auto">
            Мы сотрудничаем с разными типами поставщиков питания
          </p>
        </div>

        <div className="grid md:grid-cols-3 gap-8">
          <div
            className="bg-gray-50 rounded-xl p-8 card-hover"
            data-aos="fade-up"
          >
            <div className="w-20 h-20 bg-red-100 rounded-full flex items-center justify-center mb-6 mx-auto">
              <i className="fas fa-utensils text-red-500 text-3xl"></i>
            </div>
            <h3 className="text-xl font-semibold text-center mb-4">
              Рестораны и кафе
            </h3>
            <p className="text-gray-600 text-center">
              Предлагайте свои блюда корпоративным клиентам и заполняйте
              "мертвые" часы
            </p>
          </div>

          <div
            className="bg-gray-50 rounded-xl p-8 card-hover"
            data-aos="fade-up"
            data-aos-delay="100"
          >
            <div className="w-20 h-20 bg-yellow-100 rounded-full flex items-center justify-center mb-6 mx-auto">
              <i className="fas fa-truck text-yellow-500 text-3xl"></i>
            </div>
            <h3 className="text-xl font-semibold text-center mb-4">
              Службы доставки
            </h3>
            <p className="text-gray-600 text-center">
              Получайте стабильные объемы заказов с предоплатой и
              гарантированной доставкой
            </p>
          </div>

          <div
            className="bg-gray-50 rounded-xl p-8 card-hover"
            data-aos="fade-up"
            data-aos-delay="200"
          >
            <div className="w-20 h-20 bg-green-100 rounded-full flex items-center justify-center mb-6 mx-auto">
              <i className="fas fa-industry text-green-500 text-3xl"></i>
            </div>
            <h3 className="text-xl font-semibold text-center mb-4">
              Производители
            </h3>
            <p className="text-gray-600 text-center">
              Продвигайте свои продукты питания напрямую корпоративным клиентам
            </p>
          </div>
        </div>
      </div>
    </section>
  );
};

const HowItWorksSection: React.FC<{}> = () => {
  return (
    <section className="py-16 bg-green-50" id="how-it-works">
      <div className="container mx-auto px-4">
        <div className="text-center mb-16" data-aos="fade-up">
          <h2 className="text-3xl font-bold mb-4">Как стать поставщиком?</h2>
          <p className="text-xl text-gray-600 max-w-3xl mx-auto">
            Простая процедура подключения к платформе
          </p>
        </div>

        <div className="flex flex-col md:flex-row items-center">
          <div className="md:w-1/2 mb-12 md:mb-0" data-aos="fade-right">
            <div className="space-y-8">
              <div className="flex">
                <div className="flex-shrink-0">
                  <div className="flex items-center justify-center w-12 h-12 rounded-full bg-green-100 text-green-500 font-bold text-xl mr-4">
                    1
                  </div>
                </div>
                <div>
                  <h3 className="text-xl font-semibold mb-2">
                    Заполните заявку
                  </h3>
                  <p className="text-gray-600">
                    Оставьте свои контактные данные и информацию о вашем бизнесе
                  </p>
                </div>
              </div>

              <div className="flex">
                <div className="flex-shrink-0">
                  <div className="flex items-center justify-center w-12 h-12 rounded-full bg-blue-100 text-blue-500 font-bold text-xl mr-4">
                    2
                  </div>
                </div>
                <div>
                  <h3 className="text-xl font-semibold mb-2">Собеседование</h3>
                  <p className="text-gray-600">
                    Наш менеджер свяжется с вами для уточнения деталей
                    сотрудничества
                  </p>
                </div>
              </div>

              <div className="flex">
                <div className="flex-shrink-0">
                  <div className="flex items-center justify-center w-12 h-12 rounded-full bg-purple-100 text-purple-500 font-bold text-xl mr-4">
                    3
                  </div>
                </div>
                <div>
                  <h3 className="text-xl font-semibold mb-2">
                    Подписание договора
                  </h3>
                  <p className="text-gray-600">
                    Мы подготовим все необходимые документы для начала работы
                  </p>
                </div>
              </div>

              <div className="flex">
                <div className="flex-shrink-0">
                  <div className="flex items-center justify-center w-12 h-12 rounded-full bg-yellow-100 text-yellow-500 font-bold text-xl mr-4">
                    4
                  </div>
                </div>
                <div>
                  <h3 className="text-xl font-semibold mb-2">
                    Обучение и запуск
                  </h3>
                  <p className="text-gray-600">
                    Вы получите доступ к системе и сможете начать принимать
                    заказы
                  </p>
                </div>
              </div>
            </div>
          </div>

          <div className="md:w-1/2 flex justify-center" data-aos="fade-left">
            <img
              src="https://images.unsplash.com/photo-1551288049-bebda4e38f71?ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80"
              alt="Процесс подключения"
              className="rounded-xl shadow-xl max-w-md w-full"
            />
          </div>
        </div>
      </div>
    </section>
  );
};

const RegistrationSection: React.FC<{}> = () => {
  return (
    <section id="registration" className="py-16 bg-white">
      <div className="container mx-auto px-4 max-w-4xl">
        <div className="text-center mb-12" data-aos="fade-up">
          <h2 className="text-3xl font-bold mb-4">Заявка на подключение</h2>
          <p className="text-xl text-gray-600">
            Заполните форму, и наш менеджер свяжется с вами в ближайшее время
          </p>
        </div>

        <form
          className="bg-gray-50 rounded-xl p-8 shadow-sm"
          data-aos="fade-up"
        >
          <div className="grid md:grid-cols-2 gap-6 mb-8">
            <div>
              <label
                htmlFor="company-name"
                className="block text-gray-700 font-medium mb-2"
              >
                Название компании
              </label>
              <input
                type="text"
                id="company-name"
                className="w-full px-4 py-3 rounded-lg border border-gray-300 form-input focus:outline-none focus:border-green-500"
                placeholder="ООО 'Вкусная еда'"
                required
              />
            </div>
            <div>
              <label
                htmlFor="upn"
                className="block text-gray-700 font-medium mb-2"
              >
                УНП
              </label>
              <input
                type="number"
                id="upn"
                className="w-full px-4 py-3 rounded-lg border border-gray-300 form-input focus:outline-none focus:border-green-500"
                placeholder="123456789"
                required
              />
            </div>
            <div>
              <label
                htmlFor="phone"
                className="block text-gray-700 font-medium mb-2"
              >
                Телефон
              </label>
              <input
                type="tel"
                id="phone"
                className="w-full px-4 py-3 rounded-lg border border-gray-300 form-input focus:outline-none focus:border-green-500"
                placeholder="+375 (29) 123-45-67"
                required
              />
            </div>
            <div>
              <label
                htmlFor="email"
                className="block text-gray-700 font-medium mb-2"
              >
                Email
              </label>
              <input
                type="email"
                id="email"
                className="w-full px-4 py-3 rounded-lg border border-gray-300 form-input focus:outline-none focus:border-green-500"
                placeholder="example@domain.com"
                required
              />
            </div>
          </div>

          <div className="mb-8">
            <label
              htmlFor="address"
              className="block text-gray-700 font-medium mb-2"
            >
              Юридический адрес
            </label>
            <input
              type="text"
              id="address"
              className="w-full px-4 py-3 rounded-lg border border-gray-300 form-input focus:outline-none focus:border-green-500"
              placeholder="г. Минск, ул. Примерная, д. 1"
              required
            />
          </div>

          <div className="flex items-center mb-8">
            <input
              type="checkbox"
              id="agreement"
              className="w-5 h-5 text-green-500 rounded border-gray-300 focus:ring-green-500"
            />
            <label htmlFor="agreement" className="ml-2 text-gray-600">
              Я согласен на обработку персональных данных и принимаю условия{" "}
              <a href="#" className="text-green-500 hover:underline">
                пользовательского соглашения
              </a>
            </label>
          </div>

          <button
            type="submit"
            className="w-full bg-green-500 hover:bg-green-600 text-white py-3 px-6 rounded-lg font-medium text-lg transition transform hover:scale-105"
          >
            Отправить заявку
          </button>
        </form>
      </div>
    </section>
  );
};

const TestimonialsSection: React.FC<{}> = () => {
  return (
    <section className="py-16 bg-gray-50">
      <div className="container mx-auto px-4">
        <div className="text-center mb-16" data-aos="fade-up">
          <h2 className="text-3xl font-bold mb-4">Отзывы наших поставщиков</h2>
          <p className="text-xl text-gray-600 max-w-3xl mx-auto">
            Что говорят партнеры, которые уже работают с MealMap
          </p>
        </div>

        <div className="grid md:grid-cols-3 gap-8">
          <div className="bg-white rounded-xl p-8 shadow-sm" data-aos="fade-up">
            <div className="flex items-center mb-4">
              <div className="w-12 h-12 rounded-full bg-green-100 flex items-center justify-center mr-4">
                <i className="fas fa-user text-green-500"></i>
              </div>
              <div>
                <h4 className="font-semibold">Анна М.</h4>
                <p className="text-sm text-gray-500">Владелец кафе</p>
              </div>
            </div>
            <p className="text-gray-600 italic">
              "Благодаря MealMap мы заполнили обеденные часы в нашем кафе.
              Теперь у нас стабильный поток клиентов даже в будни."
            </p>
          </div>

          <div
            className="bg-white rounded-xl p-8 shadow-sm"
            data-aos="fade-up"
            data-aos-delay="100"
          >
            <div className="flex items-center mb-4">
              <div className="w-12 h-12 rounded-full bg-blue-100 flex items-center justify-center mr-4">
                <i className="fas fa-user text-blue-500"></i>
              </div>
              <div>
                <h4 className="font-semibold">Дмитрий К.</h4>
                <p className="text-sm text-gray-500">
                  Директор службы доставки
                </p>
              </div>
            </div>
            <p className="text-gray-600 italic">
              "С MealMap мы получили доступ к крупным корпоративным клиентам.
              Заказы поступают регулярно, платежи вовремя - идеальный партнер."
            </p>
          </div>

          <div
            className="bg-white rounded-xl p-8 shadow-sm"
            data-aos="fade-up"
            data-aos-delay="200"
          >
            <div className="flex items-center mb-4">
              <div className="w-12 h-12 rounded-full bg-purple-100 flex items-center justify-center mr-4">
                <i className="fas fa-user text-purple-500"></i>
              </div>
              <div>
                <h4 className="font-semibold">Елена С.</h4>
                <p className="text-sm text-gray-500">
                  Производитель здорового питания
                </p>
              </div>
            </div>
            <p className="text-gray-600 italic">
              "Платформа помогла нам выйти на новые рынки. Теперь наши продукты
              доступны сотрудникам десятков компаний без дополнительных затрат
              на маркетинг."
            </p>
          </div>
        </div>
      </div>
    </section>
  );
};

export default SupplierRegistrationPage;
