INSERT INTO allergens(id, name)
VALUES
    (1, 'Орехи'),
    (2, 'Молоко'),
    (3, 'Яйца'),
    (4, 'Рыба'),
    (5, 'Морепродукты'),
    (6, 'Соя'),
    (7, 'Глютен'),
    (8, 'Томаты'),
    (9, 'Шоколад'),
    (10, 'Кунжут');

ALTER SEQUENCE allergens_id_seq RESTART WITH 12;
