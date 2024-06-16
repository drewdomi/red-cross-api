CREATE TABLE IF NOT EXISTS emergency
(
    id               UUID PRIMARY KEY,
    number           INT,
    reason           VARCHAR(255),
    start_register   TIMESTAMP,
    registered_by_id UUID NOT NULL,
    obs              VARCHAR(255),
    created_at       TIMESTAMP,
    updated_at       TIMESTAMP,
    FOREIGN KEY (registered_by_id) REFERENCES rescuer (id)
);