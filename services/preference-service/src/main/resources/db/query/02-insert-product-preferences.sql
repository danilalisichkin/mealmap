INSERT INTO product_preferences(id, product_id, user_preferences_id, preference_type)
VALUES
    (1, 10, 1, 1),
    (2, 11, 1, 1),
    (3, 12, 1, 2),
    (4, 3, 2, 1),
    (5, 7, 2, 1);

ALTER SEQUENCE product_preferences_id_seq RESTART WITH 6;
