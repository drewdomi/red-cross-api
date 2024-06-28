ALTER TABLE emergency
    ADD COLUMN registered_by UUID;

ALTER TABLE emergency
    ADD CONSTRAINT fk_registered_by
        FOREIGN KEY (registered_by)
            REFERENCES rescuer (id);