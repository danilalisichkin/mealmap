import React from "react";

const contacts = {
  phone: "+375 (29) 28-84-988",
  email: "mealmap@mealmap.by",
  address: "г. Минск, ул. Леонида Беды, д. 4, к. 1",
};

const socialMedia = {
  telegram: "t.me/mealmap",
  instagram: "instagram.com/mealmap",
  vk: "vk.com/mealmap",
};

const Footer: React.FC = () => (
  <footer className="bg-white shadow-sm border-t mt-12 py-8 px-4">
    <div className="max-w-6xl mx-auto">
      <div className="flex flex-col md:flex-row justify-between items-start md:items-center mb-8">
        <div className="flex items-center mb-4 md:mb-0">
          <i className="fas fa-utensils text-green-500 text-2xl mr-3"></i>
          <span className="font-bold text-green-500 text-xl">MealMap</span>
        </div>
        <span className="text-gray-500 text-sm">
          © <span id="current-year"></span> Все права защищены
        </span>
      </div>
  
      <div className="footer-divider my-6"></div>

      <div className="grid grid-cols-1 md:grid-cols-3 gap-8">
        <div>
          <h3 className="font-semibold text-gray-800 text-lg mb-3">
            Навигация
          </h3>
          <ul className="space-y-2">
            <li>
              <a
                href="#"
                className="text-gray-600 hover:text-green-600 transition flex items-center"
              >
                <i className="fas fa-chevron-right text-xs text-green-500 mr-2"></i>
                О нас
              </a>
            </li>
            <li>
              <a
                href="#"
                className="text-gray-600 hover:text-green-600 transition flex items-center"
              >
                <i className="fas fa-chevron-right text-xs text-green-500 mr-2"></i>
                Каталог
              </a>
            </li>
            <li>
              <a
                href="#"
                className="text-gray-600 hover:text-green-600 transition flex items-center"
              >
                <i className="fas fa-chevron-right text-xs text-green-500 mr-2"></i>
                Политика конфиденциальности
              </a>
            </li>
          </ul>
        </div>

        <div>
          <h3 className="font-semibold text-gray-800 text-lg mb-3">Контакты</h3>
          <ul className="space-y-3">
            <li className="flex items-start">
              <i className="fas fa-phone text-green-500 mt-1 mr-3"></i>
              <div>
                <span className="text-gray-500 text-sm">Телефон</span>
                <a
                  href={`tel:${contacts.phone}`}
                  className="block text-gray-700 hover:text-green-600 transition"
                >
                  {contacts.phone}
                </a>
              </div>
            </li>
            <li className="flex items-start">
              <i className="fas fa-envelope text-green-500 mt-1 mr-3"></i>
              <div>
                <span className="text-gray-500 text-sm">Email</span>
                <a
                  href={`mailto:${contacts.email}`}
                  className="block text-gray-700 hover:text-green-600 transition"
                >
                  {contacts.email}
                </a>
              </div>
            </li>
            <li className="flex items-start">
              <i className="fas fa-map-marker-alt text-green-500 mt-1 mr-3"></i>
              <div>
                <span className="text-gray-500 text-sm">Адрес</span>
                <span className="block text-gray-700">{contacts.address}</span>
              </div>
            </li>
          </ul>
        </div>

        <div>
          <h3 className="font-semibold text-gray-800 text-lg mb-3">
            Мы в соцсетях
          </h3>
          <p className="text-gray-500 text-sm mb-4">
            Следите за нашими новостями и акциями
          </p>
          <div className="flex space-x-3">
            <a
              href={socialMedia.vk}
              aria-label="VK"
              className="social-icon bg-blue-500 text-white hover:bg-blue-600"
            >
              <i className="fab fa-vk"></i>
            </a>
            <a
              href={socialMedia.telegram}
              aria-label="Telegram"
              className="social-icon bg-blue-400 text-white hover:bg-blue-500"
            >
              <i className="fab fa-telegram"></i>
            </a>
            <a
              href={socialMedia.instagram}
              aria-label="Instagram"
              className="social-icon bg-gradient-to-r from-purple-500 to-pink-500 text-white hover:from-purple-600 hover:to-pink-600"
            >
              <i className="fab fa-instagram"></i>
            </a>
          </div>
        </div>
      </div>

      <div className="mt-8 pt-6 border-t border-gray-200 md:hidden">
        <p className="text-gray-500 text-center text-sm">
          © <span id="mobile-current-year"></span> MealMap. Все права защищены.
        </p>
      </div>
    </div>
  </footer>
);

export default Footer;
