INSERT INTO users(
    id, phone_number, email, first_name, last_name, organization_id, created_at,
                  is_active, is_blocked, is_temporary_blocked, role)
VALUES
    ('11111111-1111-1111-1111-111111111111', '375291234567', 'test@test.com', 'John',
     'Pork', 1, '2023-9-20T15:30:00', true, false, true, 1),
    ('33333333-3333-3333-3333-333333333333', '375296666666', 'admin@admin.com', 'Admin',
     'Admin', 2, '2024-9-20T15:30:00', true, false, false, 5);
