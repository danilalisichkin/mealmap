INSERT INTO category_preferences(id, category_id, user_preference_id, preference_type)
VALUES
    (1, 1, 1, 1),
    (2, 3, 1, 1),
    (3, 4, 1, 2),
    (4, 5, 2, 1);

ALTER SEQUENCE category_preferences_id_seq RESTART WITH 5;
