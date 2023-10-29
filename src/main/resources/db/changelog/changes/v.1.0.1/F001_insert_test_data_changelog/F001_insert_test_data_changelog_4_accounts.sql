DO $$
    DECLARE
        AtDate TIMESTAMP := CURRENT_TIMESTAMP;
        Client1 UUID;
        Client2 UUID;
        Client3 UUID;
        Client4 UUID;
        Client5 UUID;
    BEGIN
        SELECT id INTO Client1 FROM clients WHERE last_name = 'Romanov';
        SELECT id INTO Client2 FROM clients WHERE last_name = 'Turmanko';
        SELECT id INTO Client3 FROM clients WHERE last_name = 'Vasileva';
        SELECT id INTO Client4 FROM clients WHERE last_name = 'Bubnova';
        SELECT id INTO Client5 FROM clients WHERE last_name = 'Herrenko';

        INSERT INTO accounts (id, client_id, name, type, balance, currency_code, status, created_at, updated_at)
        VALUES
            (GEN_RANDOM_UUID(), Client1, 'DE89370400440532013000', 'DEBIT_CARD', 23000.0, 'EUR', 'ACTIVE', AtDate, AtDate),
            (GEN_RANDOM_UUID(), Client2, 'DE75512108001245126199', 'DEBIT_CARD', 1200.0, 'EUR', 'ACTIVE', AtDate, AtDate),
            (GEN_RANDOM_UUID(), Client3, 'DE02500105170137075030', 'DEBIT_CARD', 32300.0, 'EUR', 'ACTIVE', AtDate, AtDate),
            (GEN_RANDOM_UUID(), Client4, 'DE02120300000000202051', 'CREDIT_CARD', 10000.0, 'EUR', 'ACTIVE', AtDate, AtDate),
            (GEN_RANDOM_UUID(), Client5, 'DE02100100100006820101', 'DEBIT_CARD', 50000.0, 'EUR', 'INACTIVE', AtDate, AtDate);
    END $$;