BEGIN;

TRUNCATE TABLE user_preferences CASCADE;

ALTER SEQUENCE user_preferences_id_seq RESTART WITH 1;

TRUNCATE TABLE category_preferences;

ALTER SEQUENCE category_preferences_id_seq RESTART WITH 1;

TRUNCATE TABLE product_preferences;

ALTER SEQUENCE product_preferences_id_seq RESTART WITH 1;

COMMIT;
