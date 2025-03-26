INSERT INTO user_diets(id, user_physic_health_id, diet_type, goal_weight, start_date)
VALUES
    (1, 1, 3, 70000, '2025-01-10'),
    (2, 2, 2, 35000,'2025-02-11');

ALTER SEQUENCE user_diets_id_seq RESTART WITH 3;
