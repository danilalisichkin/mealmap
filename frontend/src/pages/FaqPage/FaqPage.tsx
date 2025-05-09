import React from "react";
import Accordion from "../../components/commons/Accordion/Accordion";

interface FaqPageProps {}

const employeeFaqItems = [
  {
    title: "Как мне зарегистрироваться в системе?",
    content: (
      <p>
        Регистрация происходит через вашу компанию. Администратор организации
        должен добавить вас в систему, после чего вы получите письмо с
        инструкциями по активации аккаунта на указанный email.
      </p>
    ),
  },
  {
    title: "Как настроить диетические предпочтения?",
    content: (
      <p>
        После входа в систему перейдите в раздел "Мой профиль" → "Здоровье".
        Здесь вы можете:
        <ul className="list-disc pl-5 mt-2 space-y-1">
          <li>Указать тип диеты (вегетарианская, кето, безглютеновая и др.)</li>
          <li>Добавить аллергии и непереносимости</li>
          <li>Отметить любимые / нелюбимые продукты</li>
          <li>Установить цели питания (похудение, поддержание веса и др.)</li>
        </ul>
      </p>
    ),
  },
  {
    title: "Как сделать заказ?",
    content: (
      <p>
        <ol className="list-decimal pl-5 space-y-2">
          <li>Откройте раздел "Каталог"</li>
          <li>
            Выберите подходящие блюда из предложенных вариантов (система
            учитывает ваши предпочтения)
          </li>
          <li>Укажите количество порций</li>
          <li>Добавьте в корзину, затем подтвердите заказ</li>
          <li>Оплата происходит автоматически через корпоративную систему</li>
        </ol>
        <p className="mt-3 text-sm text-gray-500">
          Примечание: время заказа может быть ограничено политикой вашей
          компании.
        </p>
      </p>
    ),
  },
  {
    title: "Можно ли изменить или отменить заказ?",
    content: (
      <p>
        Изменение заказа после его оформления невозможно. Вы можете отменить
        заказ, пока он не начал готовиться.
        <p className="mt-2">Для отмены:</p>
        <ol className="list-decimal pl-5 mt-1 space-y-1">
          <li>Перейдите в "Мои заказы"</li>
          <li>Найдите активный заказ</li>
          <li>Нажмите "Отменить"</li>
        </ol>
      </p>
    ),
  },
];

const companyFaqItems = [
  {
    title: "Как подключить нашу компанию к MealMap?",
    content: (
      <p>
        Для подключения вашей компании:
        <ol className="list-decimal pl-5 mt-2 space-y-1">
          <li>
            Оставьте заявку на нашем сайте или позвоните по телефону +375 (29)
            28-84-988
          </li>
          <li>Наш менеджер свяжется с вами для обсуждения условий</li>
          <li>После согласования мы создадим аккаунт Вашей компании</li>
          <li>
            Ваши сотрудники смогут зарегистрироваться на сайте и пользоваться
            нашими услугами
          </li>
        </ol>
      </p>
    ),
  },
  {
    title: "Какие поставщики питания доступны?",
    content: (
      <p>
        В нашей сети присутствуют проверенные поставщики питания из Лиды и
        Минска, включая:
        <ul className="list-disc pl-5 mt-2 space-y-1">
          <li className="flex items-center">
            <i className="fas fa-check text-green-500 mr-2"></i> Рестораны
          </li>
          <li className="flex items-center">
            <i className="fas fa-check text-green-500 mr-2"></i> Кафе
          </li>
          <li className="flex items-center">
            <i className="fas fa-check text-green-500 mr-2"></i> Службы доставки
          </li>
          <li className="flex items-center">
            <i className="fas fa-check text-green-500 mr-2"></i> Кейтеринговые
            компании
          </li>
          <li className="flex items-center">
            <i className="fas fa-check text-green-500 mr-2"></i> Здоровое
            питание
          </li>
          <li className="flex items-center">
            <i className="fas fa-check text-green-500 mr-2"></i> Вегетарианские
            кухни
          </li>
        </ul>
        <ul />
        <p className="mt-3">
          Вы можете выбрать нескольких поставщиков или мы подберем оптимальные
          варианты под ваши требования.
        </p>
      </p>
    ),
  },
  {
    title: "Как работает система оплаты?",
    content: (
      <p>
        MealMap предлагает гибкие условия оплаты:
        <div className="p-3 bg-gray-50 rounded-lg">
          <h4 className="font-medium text-gray-800">Корпоративная оплата</h4>
          <p className="text-sm mt-1">
            Компания полностью или частично оплачивает питание сотрудников.
            Выставляется единый счет в конце месяца.
          </p>
        </div>
        <div className="p-3 bg-gray-50 rounded-lg">
          <h4 className="font-medium text-gray-800">Личная оплата</h4>
          <p className="text-sm mt-1">
            Сотрудники оплачивают питание самостоятельно через привязанные
            банковские карты.
          </p>
        </div>
        <div className="p-3 bg-gray-50 rounded-lg">
          <h4 className="font-medium text-gray-800">Смешанная модель</h4>
          <p className="text-sm mt-1">
            Компания покрывает часть стоимости (например, 70%), остальное
            оплачивает сотрудник.
          </p>
        </div>
      </p>
    ),
  },
  {
    title: "Можно ли изменить или отменить заказ?",
    content: (
      <p>
        Изменение заказа после его оформления невозможно. Вы можете отменить
        заказ, пока он не начал готовиться.
        <p className="mt-2">Для отмены:</p>
        <ol className="list-decimal pl-5 mt-1 space-y-1">
          <li>Перейдите в "Мои заказы"</li>
          <li>Найдите активный заказ</li>
          <li>Нажмите "Отменить"</li>
        </ol>
      </p>
    ),
  },
];

const supplierFaqItems = [
  {
    title: "Как стать поставщиком на платформе?",
    content: (
      <p>
        Требования к поставщикам:
        <ul className="list-disc pl-5 mt-2 space-y-1">
          <li>Официальная регистрация и все необходимые разрешения</li>
          <li>Собственное производство или кухня</li>
          <li>Возможность готовить от 50 порций в день</li>
          <li>Собственная или партнерская служба доставки</li>
        </ul>
        <p className="mt-3">Процесс подключения:</p>
        <ol className="list-decimal pl-5 mt-1 space-y-1">
          <li>Заполните форму на сайте в разделе "Для поставщиков"</li>
          <li>Предоставьте меню и образцы продукции</li>
          <li>Пройдите проверку службой безопасности</li>
          <li>Подпишите договор и получите доступ к системе</li>
        </ol>
      </p>
    ),
  },
  {
    title: "Как работает система заказов?",
    content: (
      <p>
        После подключения вы получите:
        <ul className="list-disc pl-5 mt-2 space-y-1">
          <li>Личный кабинет поставщика</li>
          <li>Доступ к списку активных заказов</li>
          <li>API для интеграции с вашей системой учета</li>
        </ul>
        <p className="mt-3">Процесс работы:</p>
        <ol className="list-decimal pl-5 mt-1 space-y-1">
          <li>Ежедневно вы получаете уведомление об оформленных заказах</li>
          <li>Подтверждаете возможность выполнения</li>
          <li>Готовите и упаковываете заказы с маркировкой MealMap</li>
          <li>
            Организуете доставку в указанное время (или используете нашу
            логистику)
          </li>
          <li>Получаете оплату в соотвествии действующего договора</li>
        </ol>
      </p>
    ),
  },
  {
    title: "Какие комиссии берет платформа?",
    content: (
      <p>
        Наши тарифы:
        <div className="grid grid-cols-1 md:grid-cols-3 gap-4 mt-3">
          <div className="border rounded-lg p-4">
            <h4 className="font-medium text-gray-800">Базовый</h4>
            <p className="text-2xl font-bold text-green-600 mt-1">30%</p>
            <p className="text-sm text-gray-500 mt-2">До 100 заказов/месяц</p>
            <p className="text-sm text-gray-500">Базовая поддержка</p>
          </div>
          <div className="border rounded-lg p-4">
            <h4 className="font-medium text-gray-800">Оптима</h4>
            <p className="text-2xl font-bold text-green-600 mt-1">25%</p>
            <p className="text-sm text-gray-500 mt-2">100-500 заказов/месяц</p>
            <p className="text-sm text-gray-500">Персональный менеджер</p>
          </div>
          <div className="border rounded-lg p-4">
            <h4 className="font-medium text-gray-800">Премиум</h4>
            <p className="text-2xl font-bold text-green-600 mt-1">20%</p>
            <p className="text-sm text-gray-500 mt-2">500+ заказов/месяц</p>
            <p className="text-sm text-gray-500">Приоритетное размещение</p>
          </div>
        </div>
        <p className="mt-3">
          Комиссия рассчитывается от стоимости заказа без учета доставки. Есть
          льготные тарифы для новых клиентов.
        </p>
      </p>
    ),
  },
];

const FaqPage: React.FC<FaqPageProps> = () => {
  return (
    <main className="container mx-auto px-4 py-8">
      {/* Page Header */}
      <div className="text-center mb-12">
        <h1 className="text-3xl md:text-4xl font-bold text-gray-800 mb-3">
          Часто задаваемые вопросы
        </h1>
        <p className="text-gray-600 max-w-3xl mx-auto">
          Найдите ответы на самые популярные вопросы о работе сервиса MealMap
        </p>
      </div>

      {/* FAQ Sections */}
      <div className="max-w-4xl mx-auto">
        {/* For Employees Section */}
        <div className="mb-12">
          <div className="flex items-center mb-6">
            <div className="w-10 h-10 rounded-full bg-green-100 flex items-center justify-center mr-3">
              <i className="fas fa-user-tie text-green-600"></i>
            </div>
            <h2 className="text-2xl font-semibold text-gray-800">
              Для сотрудников
            </h2>
          </div>
          <Accordion items={employeeFaqItems} />
        </div>

        {/* For Companies Section */}
        <div className="mb-12">
          <div className="flex items-center mb-6">
            <div className="w-10 h-10 rounded-full bg-blue-100 flex items-center justify-center mr-3">
              <i className="fas fa-building text-blue-600"></i>
            </div>
            <h2 className="text-2xl font-semibold text-gray-800">
              Для компаний
            </h2>
          </div>
          <Accordion items={companyFaqItems} />
        </div>

        {/* For Suppliers Section */}
        <div className="mb-12">
          <div className="flex items-center mb-6">
            <div className="w-10 h-10 rounded-full bg-purple-100 flex items-center justify-center mr-3">
              <i className="fas fa-truck text-purple-600"></i>
            </div>
            <h2 className="text-2xl font-semibold text-gray-800">
              Для поставщиков
            </h2>
          </div>
          <Accordion items={supplierFaqItems} />
        </div>
      </div>

      {/* Contact Section */}
      <div className="bg-green-50 rounded-xl p-6 md:p-8">
        <div className="text-center mb-6">
          <h2 className="text-2xl font-semibold text-gray-800">
            Не нашли ответ на свой вопрос?
          </h2>
          <p className="text-gray-600 mt-2">
            Наша служба поддержки всегда готова помочь
          </p>
        </div>

        <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
          <div className="bg-white rounded-lg p-4 text-center">
            <div className="w-12 h-12 bg-green-100 rounded-full flex items-center justify-center mx-auto mb-3">
              <i className="fas fa-phone text-green-600"></i>
            </div>
            <h3 className="font-medium text-gray-800">Телефон</h3>
            <a href="tel:+375292884988" className="block text-green-600 mt-1">
              +375 (29) 28-84-988
            </a>
            <p className="text-sm text-gray-500 mt-2">Пн-Пт: 9:00-18:00</p>
          </div>

          <div className="bg-white rounded-lg p-4 text-center">
            <div className="w-12 h-12 bg-green-100 rounded-full flex items-center justify-center mx-auto mb-3">
              <i className="fas fa-envelope text-green-600"></i>
            </div>
            <h3 className="font-medium text-gray-800">Email</h3>
            <a
              href="mailto:support@mealmap.by"
              className="block text-green-600 mt-1"
            >
              support@mealmap.by
            </a>
            <p className="text-sm text-gray-500 mt-2">
              Ответ в течение 24 часов
            </p>
          </div>

          <div className="bg-white rounded-lg p-4 text-center">
            <div className="w-12 h-12 bg-green-100 rounded-full flex items-center justify-center mx-auto mb-3">
              <i className="fab fa-telegram text-green-600"></i>
            </div>
            <h3 className="font-medium text-gray-800">Чат</h3>
            <button className="bg-green-500 hover:bg-green-600 text-white px-4 py-1 rounded-full text-sm mt-1 transition">
              Открыть чат
            </button>
            <p className="text-sm text-gray-500 mt-2">Онлайн 24/7</p>
          </div>
        </div>
      </div>
    </main>
  );
};

export default FaqPage;
