INSERT INTO user_roles(
    id, user_id, role)
VALUES
    (1, 'ef38cc33-c3df-4b76-aa8c-de6be555f45c', 1),
    (2, 'ef38cc33-c3df-4b76-aa8c-de6be555f45c', 4),
    (3, 'c7f7e1b5-9f4e-4e6a-9d2d-2e5d562b1f04', 3),
    (4, 'c7f7e1b5-9f4e-4e6a-9d2d-2e5d562b1f04', 4),
    (5, '0e3901b5-fb3f-44e4-b691-adac0a735789', 2);

ALTER SEQUENCE user_roles_id_seq RESTART WITH 6;
