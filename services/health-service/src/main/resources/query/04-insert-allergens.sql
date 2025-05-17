INSERT INTO allergens(id, physic_health_id, allergen_id)
VALUES
    (1, 1, 4),
    (2, 1, 9);

ALTER SEQUENCE allergens_id_seq RESTART WITH 3;
