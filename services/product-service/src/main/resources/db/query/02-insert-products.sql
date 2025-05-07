INSERT INTO products(id, name, image_url, price, weight, nutrients_id, description, created_at, supplier_id)
VALUES
    (1, 'Пицца "Маргарита"', 'products_1.webp', 1500, 470, 1,
     'Пицца Маргарита — это классика итальянской кухни с хрустящим тестом, томатным соусом, свежей моцареллой и ароматным базиликом. Яркие цвета напоминают итальянский флаг, а легкий вкус делает её идеальной для любого повода.',
     NOW(), 2),
    ;

ALTER SEQUENCE products_id_seq RESTART WITH 2;
