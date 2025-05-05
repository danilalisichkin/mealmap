INSERT INTO user_status_history(id, user_id, new_status, reason, changed_by, event_at)
VALUES
    (1, '0e3901b5-fb3f-44e4-b691-adac0a735789', 2, 'Учетная запись деактивирована пользователем',
     '0e3901b5-fb3f-44e4-b691-adac0a735789', '2025-4-11T15:30:00'),
    (2, '0e3901b5-fb3f-44e4-b691-adac0a735789', 1, 'Учетная запись активирована пользователем',
     '0e3901b5-fb3f-44e4-b691-adac0a735789', '2025-4-12T15:30:00');

ALTER SEQUENCE user_status_history_id_seq RESTART WITH 3;
