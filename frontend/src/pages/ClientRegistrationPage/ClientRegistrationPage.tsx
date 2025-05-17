import React, { useEffect } from "react";
import AOS from "aos";
import "aos/dist/aos.css";

interface ClientRegistrationPageProps {}

const ClientRegistrationPage: React.FC<ClientRegistrationPageProps> = () => {
  useEffect(() => {
    AOS.init({
      duration: 1000,
      once: true,
    });
    AOS.refresh();
  }, []);

  return (
    <main className="container min-h-screen overflow-hidden mx-auto px-4 py-8">
      <HeroSection />

      <StatsSection />

      <ProblemsSection />

      <SolutionsSection />

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
              <span className="text-green-500">Корпоративное питание</span>
              <br />
              для вашей компании
            </h1>
            <p className="text-xl text-gray-600 mb-8">
              Автоматизированная система организации здорового питания
              сотрудников с персональным подходом и налоговыми преимуществами
            </p>
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
                Подключить компанию
              </a>
              <a
                href="#solutions"
                className="border border-green-500 text-green-500 hover:bg-green-50 px-6 py-3 rounded-full font-medium text-center transition"
                onClick={(e) => {
                  e.preventDefault();
                  document.querySelector("#solutions")?.scrollIntoView({
                    behavior: "smooth",
                  });
                }}
              >
                Наши решения
              </a>
            </div>
          </div>
            <div className="md:w-1/2 flex justify-center" data-aos="fade-left">
            <img
              src="https://images.unsplash.com/photo-1600891964599-f61ba0e24092?ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80"
              alt="Сотрудники за обедом"
              className="rounded-xl shadow-xl floating max-w-md w-full"
            />
            </div>
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
            <div className="text-4xl font-bold text-green-500 mb-2">80%</div>
            <div className="text-gray-600">
              Сотрудников выбирают здоровое питание
            </div>
          </div>
          <div className="p-6" data-aos="zoom-in" data-aos-delay="100">
            <div className="text-4xl font-bold text-green-500 mb-2">30%</div>
            <div className="text-gray-600">Экономия времени HR-отдела</div>
          </div>
          <div className="p-6" data-aos="zoom-in" data-aos-delay="200">
            <div className="text-4xl font-bold text-green-500 mb-2">15%</div>
            <div className="text-gray-600">Рост продуктивности</div>
          </div>
          <div className="p-6" data-aos="zoom-in" data-aos-delay="300">
            <div className="text-4xl font-bold text-green-500 mb-2">100%</div>
            <div className="text-gray-600">Соответствие налоговому кодексу</div>
          </div>
        </div>
      </div>
    </section>
  );
};

const ProblemsSection: React.FC<{}> = () => {
  return (
    <section className="py-16 bg-gray-50">
      <div className="container mx-auto px-4">
        <div className="text-center mb-16" data-aos="fade-up">
          <h2 className="text-3xl font-bold mb-4">Какие проблемы мы решаем?</h2>
          <p className="text-xl text-gray-600 max-w-3xl mx-auto">
            Трудности, с которыми сталкиваются компании при организации питания
            сотрудников
          </p>
        </div>

        <div className="grid md:grid-cols-3 gap-8">
          <div
            className="bg-white rounded-xl p-8 shadow-md card-hover"
            data-aos="fade-up"
          >
            <div className="w-16 h-16 bg-red-100 rounded-full flex items-center justify-center mb-6 mx-auto">
              <i className="fas fa-clock text-red-500 text-2xl"></i>
            </div>
            <h3 className="text-xl font-semibold text-center mb-4">
              Потери времени
            </h3>
            <p className="text-gray-600 text-center">
              HR-отдел тратит до 20 часов в месяц на организацию питания: сбор
              заказов, расчеты с поставщиками, решение проблем
            </p>
          </div>

          <div
            className="bg-white rounded-xl p-8 shadow-md card-hover"
            data-aos="fade-up"
            data-aos-delay="100"
          >
            <div className="w-16 h-16 bg-yellow-100 rounded-full flex items-center justify-center mb-6 mx-auto">
              <i className="fas fa-money-bill-wave text-yellow-500 text-2xl"></i>
            </div>
            <h3 className="text-xl font-semibold text-center mb-4">
              Налоговые риски
            </h3>
            <p className="text-gray-600 text-center">
              Неправильное оформление питания сотрудников может привести к
              доначислениям налогов и штрафам
            </p>
          </div>

          <div
            className="bg-white rounded-xl p-8 shadow-md card-hover"
            data-aos="fade-up"
            data-aos-delay="200"
          >
            <div className="w-16 h-16 bg-blue-100 rounded-full flex items-center justify-center mb-6 mx-auto">
              <i className="fas fa-frown text-blue-500 text-2xl"></i>
            </div>
            <h3 className="text-xl font-semibold text-center mb-4">
              Недовольство сотрудников
            </h3>
            <p className="text-gray-600 text-center">
              Однообразное меню, неучтенные диеты и предпочтения снижают
              удовлетворенность сотрудников
            </p>
          </div>
        </div>
      </div>
    </section>
  );
};

const SolutionsSection: React.FC<{}> = () => {
  return (
    <section id="solutions" className="py-16 bg-white">
      <div className="container mx-auto px-4">
        <div className="text-center mb-16" data-aos="fade-up">
          <h2 className="text-3xl font-bold mb-4">Наши решения для компаний</h2>
          <p className="text-xl text-gray-600 max-w-3xl mx-auto">
            Комплексные услуги по организации корпоративного питания
          </p>
        </div>

        <div className="grid md:grid-cols-2 gap-8 mb-12">
          <div
            className="bg-gray-50 rounded-xl p-8 solution-card"
            data-aos="fade-right"
          >
            <div className="flex items-start mb-4">
              <div className="w-12 h-12 bg-green-100 rounded-full flex-shrink-0 flex items-center justify-center mr-4 mt-1">
                <i className="fas fa-utensils text-green-500"></i>
              </div>
              <div>
                <h3 className="text-xl font-semibold mb-2">
                  Полный цикл организации питания
                </h3>
                <p className="text-gray-600">
                  Мы берем на себя все этапы: от выбора поставщиков и
                  составления меню до доставки и отчетности
                </p>
              </div>
            </div>
            <ul className="space-y-3 text-gray-600 pl-16">
              <li className="flex items-start">
                <i className="fas fa-check text-green-500 mt-1 mr-2"></i>
                <span>Подбор проверенных поставщиков под ваш бюджет</span>
              </li>
              <li className="flex items-start">
                <i className="fas fa-check text-green-500 mt-1 mr-2"></i>
                <span>
                  Разработка сбалансированного меню с учетом пожеланий
                </span>
              </li>
              <li className="flex items-start">
                <i className="fas fa-check text-green-500 mt-1 mr-2"></i>
                <span>Организация доставки в офис или коворкинг</span>
              </li>
            </ul>
          </div>

          <div
            className="bg-gray-50 rounded-xl p-8 solution-card"
            data-aos="fade-left"
          >
            <div className="flex items-start mb-4">
              <div className="w-12 h-12 bg-blue-100 rounded-full flex-shrink-0 flex items-center justify-center mr-4 mt-1">
                <i className="fas fa-mobile-alt text-blue-500"></i>
              </div>
              <div>
                <h3 className="text-xl font-semibold mb-2">
                  Персонализированный подход
                </h3>
                <p className="text-gray-600">
                  Каждый сотрудник выбирает то, что ему нравится, с учетом диет
                  и предпочтений
                </p>
              </div>
            </div>
            <ul className="space-y-3 text-gray-600 pl-16">
              <li className="flex items-start">
                <i className="fas fa-check text-blue-500 mt-1 mr-2"></i>
                <span>Удобный сайт для выбора блюд</span>
              </li>
              <li className="flex items-start">
                <i className="fas fa-check text-blue-500 mt-1 mr-2"></i>
                <span>Учет диет: похудение, поддержка или набор массы</span>
              </li>
              <li className="flex items-start">
                <i className="fas fa-check text-blue-500 mt-1 mr-2"></i>
                <span>Гибкая система оплаты (компания/сотрудник/доля)</span>
              </li>
            </ul>
          </div>
        </div>

        <div className="grid md:grid-cols-2 gap-8">
          <div
            className="bg-gray-50 rounded-xl p-8 solution-card"
            data-aos="fade-right"
          >
            <div className="flex items-start mb-4">
              <div className="w-12 h-12 bg-purple-100 rounded-full flex-shrink-0 flex items-center justify-center mr-4 mt-1">
                <i className="fas fa-file-invoice-dollar text-purple-500"></i>
              </div>
              <div>
                <h3 className="text-xl font-semibold mb-2">
                  Налоговая оптимизация
                </h3>
                <p className="text-gray-600">
                  Полное соответствие законодательству и помощь в оформлении
                  документов
                </p>
              </div>
            </div>
            <ul className="space-y-3 text-gray-600 pl-16">
              <li className="flex items-start">
                <i className="fas fa-check text-purple-500 mt-1 mr-2"></i>
                <span>Оформление по Налоговому кодексу</span>
              </li>
              <li className="flex items-start">
                <i className="fas fa-check text-purple-500 mt-1 mr-2"></i>
                <span>Поддержка в вопросах налогообложения</span>
              </li>
              <li className="flex items-start">
                <i className="fas fa-check text-purple-500 mt-1 mr-2"></i>
                <span>Автоматизированная отчетность</span>
              </li>
            </ul>
          </div>

          <div
            className="bg-gray-50 rounded-xl p-8 solution-card"
            data-aos="fade-left"
          >
            <div className="flex items-start mb-4">
              <div className="w-12 h-12 bg-yellow-100 rounded-full flex-shrink-0 flex items-center justify-center mr-4 mt-1">
                <i className="fas fa-chart-line text-yellow-500"></i>
              </div>
              <div>
                <h3 className="text-xl font-semibold mb-2">
                  Аналитика и контроль
                </h3>
                <p className="text-gray-600">
                  Прозрачный учет расходов и контроль качества питания
                </p>
              </div>
            </div>
            <ul className="space-y-3 text-gray-600 pl-16">
              <li className="flex items-start">
                <i className="fas fa-check text-yellow-500 mt-1 mr-2"></i>
                <span>Личный кабинет с аналитикой расходов</span>
              </li>
              <li className="flex items-start">
                <i className="fas fa-check text-yellow-500 mt-1 mr-2"></i>
                <span>Мониторинг качества питания и сервиса</span>
              </li>
              <li className="flex items-start">
                <i className="fas fa-check text-yellow-500 mt-1 mr-2"></i>
                <span>Обратная связь от сотрудников</span>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </section>
  );
};

const HowItWorksSection: React.FC<{}> = () => {
  return (
    <section className="py-16 bg-green-50">
      <div className="container mx-auto px-4">
        <div className="text-center mb-16" data-aos="fade-up">
          <h2 className="text-3xl font-bold mb-4">
            Как подключить корпоративное питание?
          </h2>
          <p className="text-xl text-gray-600 max-w-3xl mx-auto">
            Всего 4 простых шага для внедрения системы в вашей компании
          </p>
        </div>

        <div className="flex flex-col md:flex-row items-center">
          <div className="md:w-1/2 mb-12 md:mb-0" data-aos="fade-right">
            <div className="space-y-8">
              <div className="flex">
                <div className="flex-shrink-0">
                  <div className="flex items-center justify-center w-12 h-12 rounded-full bg-green-100 text-green-500 font-bold text-xl mr-4 mt-1">
                    1
                  </div>
                </div>
                <div>
                  <h3 className="text-xl font-semibold mb-2">
                    Заявка на подключение
                  </h3>
                  <p className="text-gray-600">
                    Оставьте заявку, и наш менеджер свяжется с вами для
                    уточнения деталей
                  </p>
                </div>
              </div>

              <div className="flex">
                <div className="flex-shrink-0">
                  <div className="flex items-center justify-center w-12 h-12 rounded-full bg-blue-100 text-blue-500 font-bold text-xl mr-4 mt-1">
                    2
                  </div>
                </div>
                <div>
                  <h3 className="text-xl font-semibold mb-2">
                    Анализ потребностей
                  </h3>
                  <p className="text-gray-600">
                    Мы изучаем особенности вашей компании: количество
                    сотрудников, бюджет, предпочтения
                  </p>
                </div>
              </div>

              <div className="flex">
                <div className="flex-shrink-0">
                  <div className="flex items-center justify-center w-12 h-12 rounded-full bg-purple-100 text-purple-500 font-bold text-xl mr-4 mt-1">
                    3
                  </div>
                </div>
                <div>
                  <h3 className="text-xl font-semibold mb-2">
                    Подписание договора
                  </h3>
                  <p className="text-gray-600">
                    Согласование условий и оформление всех необходимых
                    документов
                  </p>
                </div>
              </div>

              <div className="flex">
                <div className="flex-shrink-0">
                  <div className="flex items-center justify-center w-12 h-12 rounded-full bg-yellow-100 text-yellow-500 font-bold text-xl mr-4 mt-1">
                    4
                  </div>
                </div>
                <div>
                  <h3 className="text-xl font-semibold mb-2">Запуск системы</h3>
                  <p className="text-gray-600">
                    Обучение сотрудников, настройка приложения и начало работы
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
    <section id="registration" className="py-16 bg-gray-50">
      <div className="container mx-auto px-4 max-w-4xl">
        <div className="text-center mb-12" data-aos="fade-up">
          <h2 className="text-3xl font-bold mb-4">
            Подключить корпоративное питание
          </h2>
          <p className="text-xl text-gray-600">
            Оставьте заявку, и мы подготовим индивидуальное предложение для
            вашей компании
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
                placeholder="ЧУП 'Голодные волки'"
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
          <h2 className="text-3xl font-bold mb-4">Нас рекомендуют</h2>
          <p className="text-xl text-gray-600 max-w-3xl mx-auto">
            Что говорят компании, которые уже используют MealMap
          </p>
        </div>

        <div className="grid md:grid-cols-3 gap-8">
          <div className="bg-white rounded-xl p-8 shadow-sm" data-aos="fade-up">
            <div className="flex items-center mb-4">
              <div className="w-12 h-12 rounded-full bg-green-100 flex items-center justify-center mr-4">
                <i className="fas fa-user text-green-500"></i>
              </div>
              <div>
                <h4 className="font-semibold">Александр К.</h4>
                <p className="text-sm text-gray-500">
                  HR-директор, IT-компания (150 сотрудников)
                </p>
              </div>
            </div>
            <p className="text-gray-600 italic">
              "С MealMap мы сократили время на организацию питания сотрудников
              на 80%. Теперь это полностью автоматизированный процесс, а
              сотрудники довольны разнообразным меню."
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
                <h4 className="font-semibold">Ольга М.</h4>
                <p className="text-sm text-gray-500">
                  Финансовый директор, производственная компания (300
                  сотрудников)
                </p>
              </div>
            </div>
            <p className="text-gray-600 italic">
              "Благодаря MealMap мы оптимизировали расходы на питание и получили
              полную налоговую прозрачность. Бухгалтерия больше не тратит время
              на проверку чеков."
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
                <h4 className="font-semibold">Дмитрий П.</h4>
                <p className="text-sm text-gray-500">
                  Генеральный директор, консалтинговая компания (50 сотрудников)
                </p>
              </div>
            </div>
            <p className="text-gray-600 italic">
              "После внедрения MealMap мы заметили рост удовлетворенности
              сотрудников и снижение количества больничных. Здоровое питание
              действительно влияет на продуктивность!"
            </p>
          </div>
        </div>
      </div>
    </section>
  );
};

export default ClientRegistrationPage;
