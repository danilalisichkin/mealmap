BEGIN;

TRUNCATE TABLE users CASCADE;

TRUNCATE TABLE user_roles;

ALTER SEQUENCE user_roles_id_seq RESTART WITH 1;

TRUNCATE TABLE user_status_history;

ALTER SEQUENCE user_status_history_id_seq RESTART WITH 1;

END;
