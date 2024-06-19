CREATE TABLE ambulance
(
    id         UUID PRIMARY KEY,
    brand      VARCHAR(50)        NOT NULL,
    plate      VARCHAR(10) UNIQUE NOT NULL,
    number     VARCHAR(10) UNIQUE NOT NULL,
    status     BOOLEAN            NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);