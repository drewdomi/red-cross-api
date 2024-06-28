CREATE SEQUENCE IF NOT EXISTS emergency_number_seq
    AS INT
    INCREMENT BY 1
    MINVALUE 1
    START WITH 1
    NO CYCLE;

CREATE TABLE emergency
(
    id             UUID PRIMARY KEY,
    number         INT NOT NULL DEFAULT nextval('emergency_number_seq'),
    reason         VARCHAR(255),
    start_register TIMESTAMP,
    obs            VARCHAR(255),
    completed_at   TIMESTAMP,
    created_at     TIMESTAMP,
    updated_at     TIMESTAMP
);

ALTER SEQUENCE emergency_number_seq
    OWNED BY emergency.number;

