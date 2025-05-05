INSERT INTO physic_health(id, user_id, weight, height, birth_date, gender)
VALUES
    (1, '0e3901b5-fb3f-44e4-b691-adac0a735789', 65000, 170, '2003-04-21', 1),
    (2, 'ef38cc33-c3df-4b76-aa8c-de6be555f45c', 40000, 164, '2001-07-02', 2);

ALTER SEQUENCE physic_health_id_seq RESTART WITH 3;
