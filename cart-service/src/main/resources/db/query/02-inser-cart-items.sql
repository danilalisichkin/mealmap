INSERT INTO cart_items(id, cart_id, product_id, quantity)
VALUES
    (7, 1, 1, 1),
    (6, 1, 3, 2),
    (5, 1, 8, 1),
    (4, 1, 24, 1),
    (3, 1, 23, 1),
    (2, 2, 3, 2),
    (1, 2, 2, 1);

ALTER SEQUENCE cart_items_id_seq RESTART WITH 8;
