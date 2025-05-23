BEGIN;

TRUNCATE TABLE physic_health CASCADE;

ALTER SEQUENCE physic_health_id_seq RESTART WITH 1;

TRUNCATE TABLE physic_health_history;

ALTER SEQUENCE physic_health_history_id_seq RESTART WITH 1;

TRUNCATE TABLE diets;

ALTER SEQUENCE diets_id_seq RESTART WITH 1;

TRUNCATE TABLE allergens;

ALTER SEQUENCE allergens_id_seq RESTART WITH 1;

COMMIT;
