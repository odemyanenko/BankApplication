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

        INSERT INTO products (id, manager_id, name, status, currency_code, interest_rate, limit_amount, created_at, updated_at)
        VALUES
            (GEN_RANDOM_UUID(), Manager1, 'Current Account', 'ACTIVE', 'EUR', 2.0, 1000.0, AtDate, AtDate),
            (GEN_RANDOM_UUID(), Manager2, 'Mortgage', 'ACTIVE', 'EUR', 7.3, 13000.0, AtDate, AtDate),
            (GEN_RANDOM_UUID(), Manager3, 'Credit', 'ACTIVE', 'USD', 18.0, 5000.0, AtDate, AtDate),
            (GEN_RANDOM_UUID(), Manager3, 'Business Credit', 'INACTIVE', 'USD', 18.0, 20000.0, AtDate, AtDate);
    END $$;