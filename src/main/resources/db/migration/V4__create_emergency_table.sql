CREATE TABLE emergency
(
    id             UUID PRIMARY KEY,
    number         INT,
    reason         VARCHAR(255),
    start_register TIMESTAMP,
    obs            VARCHAR(255),
    created_at     TIMESTAMP,
    updated_at     TIMESTAMP
);
