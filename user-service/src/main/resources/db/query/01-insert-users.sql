INSERT INTO users(
    id, phone_number, email, first_name, last_name, organization_id, created_at,
    is_active, is_blocked, is_temporary_blocked, role)
VALUES
    ('0e3901b5-fb3f-44e4-b691-adac0a735789', '375291234567', 'client-employee@company.com', 'John',
     'Pork', 1, '2023-9-20T15:30:00', true, false, true, 2),
    ('ef38cc33-c3df-4b76-aa8c-de6be555f45c', '375296666666', 'admin@admin.com', 'Admin',
     'Admin', 2, '2024-9-20T15:30:00', true, false, false, 5);
