DO $$
    DECLARE
        AtDate TIMESTAMP := CURRENT_TIMESTAMP;
    BEGIN
        INSERT INTO transactions (id, debit_account_id, credit_account_id, type, amount, status, created_at)
        SELECT
            GEN_RANDOM_UUID(),
            a.id,
            (SELECT id FROM accounts WHERE client_id <> c.id LIMIT 1),
            'PAYMENT',
            100.0,
            'APPROVED',
            AtDate
        FROM
            accounts a
                INNER JOIN
            clients c ON a.client_id = c.id
        WHERE
                c.last_name = 'Romanov'
        UNION ALL
        SELECT
            GEN_RANDOM_UUID(),
            a.id,
            (SELECT id FROM accounts WHERE client_id <> c.id LIMIT 1),
            'PAYMENT',
            200.0,
            'APPROVED',
            AtDate
        FROM
            accounts a
                INNER JOIN
            clients c ON a.client_id = c.id
        WHERE
                c.last_name = 'Turmanko'
        UNION ALL
        SELECT
            GEN_RANDOM_UUID(),
            a.id,
            (SELECT id FROM accounts WHERE client_id <> c.id LIMIT 1),
            'PAYMENT',
            300.0,
            'NEW',
            AtDate
        FROM
            accounts a
                INNER JOIN
            clients c ON a.client_id = c.id
        WHERE
                c.last_name = 'Vasileva'
        UNION ALL
        SELECT
            GEN_RANDOM_UUID(),
            a.id,
            (SELECT id FROM accounts WHERE client_id <> c.id LIMIT 1),
            'PAYMENT',
            400.0,
            'NEW',
            AtDate
        FROM
            accounts a
                INNER JOIN
            clients c ON a.client_id = c.id
        WHERE
                c.last_name = 'Bubnova'
        UNION ALL
        SELECT
            GEN_RANDOM_UUID(),
            a.id,
            (SELECT id FROM accounts WHERE client_id <> c.id LIMIT 1),
            'PAYMENT',
            500.0,
            'APPROVED',
            AtDate
        FROM
            accounts a
                INNER JOIN
            clients c ON a.client_id = c.id
        WHERE
                c.last_name = 'Herrenko';
    END $$;
