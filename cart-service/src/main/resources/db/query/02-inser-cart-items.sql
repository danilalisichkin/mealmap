INSERT INTO cart_items(id, cart_id, product_id, quantity)
VALUES
    (7, '0e3901b5-fb3f-44e4-b691-adac0a735789', 1, 1),
    (6, '0e3901b5-fb3f-44e4-b691-adac0a735789', 3, 2),
    (5, '0e3901b5-fb3f-44e4-b691-adac0a735789', 8, 1),
    (4, '0e3901b5-fb3f-44e4-b691-adac0a735789', 24, 1),
    (3, '0e3901b5-fb3f-44e4-b691-adac0a735789', 23, 1),
    (2, 'ef38cc33-c3df-4b76-aa8c-de6be555f45c', 3, 2),
    (1, 'ef38cc33-c3df-4b76-aa8c-de6be555f45c', 2, 1);

ALTER SEQUENCE cart_items_id_seq RESTART WITH 8;
