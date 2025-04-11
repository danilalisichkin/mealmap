INSERT INTO users(
    id, phone_number, email, first_name, last_name, organization_id, created_at,
    is_active, is_blocked, is_temporary_blocked)
VALUES
    ('ef38cc33-c3df-4b76-aa8c-de6be555f45c', '375296666666', 'operator-admin@admin.com', 'Админов',
     'Администратор', 1, '2024-10-20T15:30:00', true, false, false, 5),
    ('c7f7e1b5-9f4e-4e6a-9d2d-2e5d562b1f04', '375293333333', 'suplier-admin@food.com', 'Пупкин',
     'Василий', 2, '2025-1-10T10:00:00', true, false, false),
    ('0e3901b5-fb3f-44e4-b691-adac0a735789', '375291234567', 'client@company.com', 'Иванов',
    'Иван', 3, '2025-4-10T15:30:00', true, false, false);
