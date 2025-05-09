import React from "react";
import { Link } from "react-router-dom";

const contacts = {
  phone: "+375 (29) 28-84-988",
  email: "mealmap@mealmap.by",
  address: "г. Минск, ул. Леонида Беды, д. 4, к. 1",
};

const socialMedia = {
  telegram: "https://t.me/mealmap",
  instagram: "https://instagram.com/mealmap",
  vk: "https://vk.com/mealmap",
};

const Footer: React.FC = () => {
  return (
    <footer className="bg-white shadow-sm border-t mt-12 py-8 px-4">
      <div className="container mx-auto px-4">
        <div className="grid grid-cols-1 md:grid-cols-4 gap-8">
          <div>
            <div className="flex items-center mb-4">
              <i className="fas fa-utensils text-green-400 text-2xl mr-3"></i>
              <span className="font-bold text-xl text-green-400">MealMap</span>
            </div>
            <p>
              Инновационная платформа для организации корпоративного питания
            </p>
          </div>

          <div>
            <h3 className="text-lg font-semibold mb-4">Компания</h3>
            <ul className="space-y-2">
              <li>
                <Link
                  to="/about"
                  className="text-gray-400 hover:text-green-400 transition"
                >
                  О нас
                </Link>
              </li>
            </ul>
          </div>

          <div>
            <h3 className="text-lg font-semibold mb-4">Ресурсы</h3>
            <ul className="space-y-2">
              <li>
                <Link
                  to="/faq"
                  className="text-gray-400 hover:text-green-400 transition"
                >
                  FAQ
                </Link>
              </li>
              <li>
                <Link
                  to="/privacy-policy"
                  className="text-gray-400 hover:text-green-400 transition"
                >
                  Политика конфиденциальности
                </Link>
              </li>
              <li>
                <Link
                  to="/terms"
                  className="text-gray-400 hover:text-green-400 transition"
                >
                  Условия использования
                </Link>
              </li>
            </ul>
          </div>

          <div>
            <h3 className="text-lg font-semibold mb-4">Связаться с нами</h3>
            <div className="flex space-x-4 mb-4">
              <a
                href={socialMedia.vk}
                target="_blank"
                rel="noopener noreferrer"
                className="w-10 h-10 text-white bg-green-400 hover:bg-green-600 rounded-full flex items-center justify-center transition"
              >
                <i className="fab fa-vk"></i>
              </a>
              <a
                href={socialMedia.telegram}
                target="_blank"
                rel="noopener noreferrer"
                className="w-10 h-10 text-white bg-green-400 hover:bg-green-600 rounded-full flex items-center justify-center transition"
              >
                <i className="fab fa-telegram"></i>
              </a>
              <a
                href={socialMedia.instagram}
                target="_blank"
                rel="noopener noreferrer"
                className="w-10 h-10 text-white bg-green-400 hover:bg-green-600 rounded-full flex items-center justify-center transition"
              >
                <i className="fab fa-instagram"></i>
              </a>
            </div>
            <div className="flex flex-col text-gray-400">
              <a href={`mailto:${contacts.email}`}>{contacts.email}</a>
              <a href={`tel:${contacts.phone}`}>{contacts.phone}</a>
            </div>
          </div>
        </div>

        <div className="border-t border-green-400 mt-8 pt-8 text-center text-gray-400">
          <p>
            © <span id="footer-year">2025</span> MealMap. Все права защищены.
          </p>
        </div>
      </div>
    </footer>
  );
};

export default Footer;
