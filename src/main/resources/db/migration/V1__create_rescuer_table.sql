CREATE TABLE rescuer
(
    id                 UUID PRIMARY KEY,
    name               VARCHAR(255)        NOT NULL,
    email              VARCHAR(255) UNIQUE NOT NULL,
    password           VARCHAR(255)        NOT NULL,
    num_mechanographic INT UNIQUE          NOT NULL,
    access_type        SMALLINT            NOT NULL DEFAULT 2,
    is_active          BOOLEAN             NOT NULL DEFAULT TRUE,
    first_access       BOOLEAN             NOT NULL DEFAULT TRUE,
    created_at         TIMESTAMP,
    updated_at         TIMESTAMP
);
