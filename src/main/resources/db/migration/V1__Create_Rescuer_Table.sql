CREATE TABLE rescuer
(
    id                 UUID PRIMARY KEY,
    name               VARCHAR(255),
    email              VARCHAR(255) UNIQUE,
    password           VARCHAR(255),
    num_mechanographic INT UNIQUE,
    access_type        SMALLINT,
    is_active          BOOLEAN,
    first_access       BOOLEAN,
    created_at         TIMESTAMP,
    updated_at         TIMESTAMP
);
