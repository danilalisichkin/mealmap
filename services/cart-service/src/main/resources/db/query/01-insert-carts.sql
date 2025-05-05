INSERT INTO carts(id, user_id, updated_at, created_at)
VALUES
    (1, '0e3901b5-fb3f-44e4-b691-adac0a735789', '2025-02-28T12:35:00Z', '2025-02-21T10:00:00Z'),
    (2, 'ef38cc33-c3df-4b76-aa8c-de6be555f45c', '2025-01-28T09:13:22Z', '2025-01-29T11:22:00Z');

ALTER SEQUENCE carts_id_seq RESTART WITH 3;
