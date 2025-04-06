INSERT INTO user_physic_health_history(id, weight, physic_health_id, created_at)
VALUES
    (1, 54000, 1, '2025-01-10T10:00:00'),
    (2, 60000, 1, '2025-02-10T11:10:00'),
    (3, 65000, 1, '2025-03-10T12:05:00'),
    (4, 42000, 2, '2025-02-11T06:00:00'),
    (5, 40000, 2, '2025-02-20T08:00:00');

ALTER SEQUENCE physic_health_history_id_seq RESTART WITH 6;
