CREATE TABLE ambulance
(
    id         UUID PRIMARY KEY,
    brand      VARCHAR(255),
    plate      VARCHAR(255) UNIQUE,
    number     VARCHAR(255) UNIQUE,
    status     BOOLEAN,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

ALTER TABLE ambulance
    ALTER COLUMN status SET DEFAULT TRUE;