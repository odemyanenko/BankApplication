DO $$
    DECLARE
        AtDate TIMESTAMP := CURRENT_TIMESTAMP;
        Manager1 UUID;
        Manager2 UUID;
        Manager3 UUID;
    BEGIN
        SELECT id INTO Manager1 FROM managers WHERE last_name = 'Demyanenko';
        SELECT id INTO Manager2 FROM managers WHERE last_name = 'Petrov';
        SELECT id INTO Manager3 FROM managers WHERE last_name = 'Semenov';

        INSERT INTO clients (id, manager_id, first_name, last_name, tax_code, email, address, phone, status, created_at, updated_at)
        VALUES
            (GEN_RANDOM_UUID(), Manager1, 'Igor', 'Romanov', '3233414287', 'igor.romanov@gmail.com', 'Fuggerstr. 21, 10777, Berlin', '+49302138826', 0, AtDate, AtDate),
            (GEN_RANDOM_UUID(), Manager1, 'Semen', 'Turmanko', '3136215494', 'semen.turmanko@gmail.com', 'Kaiser-Friedrich-Str. 29, 10585 Berlin', '+493034709065', 0, AtDate, AtDate),
            (GEN_RANDOM_UUID(), Manager2, 'Anna', 'Vasileva', '2248000331', 'anna.valileva@gmail.com', 'Wilmersdorfer Str. 93 10629 Berlin', '+49308836664', 0, AtDate, AtDate),
            (GEN_RANDOM_UUID(), Manager3, 'Daria', 'Bubnova', '1100000001', 'daria.bubnova@gmail.com', 'Monbijouplatz 11, 10178 Berlin', '+493026304811', 0, AtDate, AtDate),
            (GEN_RANDOM_UUID(), Manager2, 'Sofia', 'Herrenko', '1234567890', 'sofia.herrenko@gmail.com', 'Pflugstrasse 11, 10115 Berlin', '+493028387765', 0, AtDate, AtDate);
    END $$;