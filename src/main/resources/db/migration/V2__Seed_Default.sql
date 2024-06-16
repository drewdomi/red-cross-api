INSERT INTO rescuer(id,
                    name,
                    email,
                    password,
                    num_mechanographic,
                    access_type,
                    is_active,
                    first_access,
                    created_at,
                    updated_at)

VALUES ('00000000-0000-0000-0000-000000000001',
        'Andrew Domingues',
        'contato@drewdomi.com',
        '$2a$10$EKTfBxUhRzHHWctvfyZFQOAPYi.Nezv.sy3uK8tzlNePFC7pJlOW.',
        143,
        0,
        true,
        true,
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP);

-- Password: drew1234