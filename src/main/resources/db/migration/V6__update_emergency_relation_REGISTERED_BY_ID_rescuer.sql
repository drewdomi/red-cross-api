ALTER TABLE emergency
    ADD COLUMN registered_by_id UUID;

ALTER TABLE emergency
    ADD CONSTRAINT fk_registered_by
        FOREIGN KEY (registered_by_id)
            REFERENCES rescuer (id);